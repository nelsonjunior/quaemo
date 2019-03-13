package br.com.quaemo.corporativo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import br.com.quaemo.corporativo.service.AccessTokenAutenticadoService;
import br.com.quaemo.domain.AccesTokenAutenticado;
import br.com.quaemo.exceptions.AccesTokenException;
import br.com.quaemo.exceptions.AccessoNegadoException;

@Component
public class ValidaTokenInterceptor implements HandlerInterceptor {
	
	@Autowired
	private AccessTokenAutenticadoService accessTokenAutenticadoService;

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String token = "";
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "Authorization, X-requested-with, Content-Type");

		String header = request.getHeader("Authorization");
		if ((header != null) && (!header.isEmpty())) {
			header = header.trim();
			String tipoToken = header.substring(0, 6);
			String accesToken = header.substring(7, header.length());
			if (tipoToken.equalsIgnoreCase("Bearer")) {
				String[] url = request.getServletPath() != null ? request.getServletPath().split("/")
						: (request.getPathInfo() != null) && (request.getPathInfo().split("/").length > 2)
								? request.getPathInfo().split("/")
								: null;
				String urlRecurso = null;
				try {
					urlRecurso = "/" + url[1] + "/" + url[2];
				} catch (Exception e) {
					e.printStackTrace();
					throw new AccesTokenException("A URL não está no padrão modulo/recurso.");
				}
				AccesTokenAutenticado accesTokenAutenticado = this.accessTokenAutenticadoService.findAccessTokenAutenticado(accesToken, urlRecurso, request.getMethod());
				if (accesTokenAutenticado == null) {
					throw new AccessoNegadoException("O token informado não possui acesso ao recurso solicitado.");
				}
				if (!accesTokenAutenticado.isValido()) {
					throw new AccesTokenException("O token informado não está mais valido.");
				}
				if (accesTokenAutenticado.isValidaOrigemToken()) {
					String ip = request.getHeader("x-forwarded-for");
					if (ip == null) {
						ip = request.getHeader("X_FORWARDED_FOR");
						if (ip == null) {
							ip = request.getRemoteAddr();
						}
					}
					if (!accesTokenAutenticado.getIp().equalsIgnoreCase(ip)) {
						throw new AccesTokenException("Os dados do token não conferem com sua geração.");
					}
				}
				token = accesTokenAutenticado.getAccess_token();
			} else {
				throw new AccesTokenException("Não foi localizado no Header Authorization o tipo Bearer.");
			}
		} else {
			header = request.getHeader("access-control-request-headers");
			if ((header == null) || (!header.toLowerCase().contains("authorization"))) {
				throw new AccesTokenException("Não foi localizado o Header Authorization.");
			}
		}
		try {
			this.accessTokenAutenticadoService.atualizaToken(token);
		} catch (Exception e) {
			System.out.println("erro ao atualizar o token");
			e.printStackTrace();
		}
		return true;
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
	}
}