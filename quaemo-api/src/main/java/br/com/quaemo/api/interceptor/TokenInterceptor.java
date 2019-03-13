package br.com.quaemo.api.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import br.com.quaemo.api.exceptions.AccesTokenException;
import br.com.quaemo.api.exceptions.OperacaoNaoRealizadaException;
import br.com.quaemo.api.service.TokenService;
import br.com.quaemo.api.support.ApiUtil;

public class TokenInterceptor implements HandlerInterceptor  {

	private static final Log logger = LogFactory.getLog(TokenInterceptor.class);
	
	private static final String TIPO_TOKEN = "Bearer";
	
	@Autowired
	private TokenService tokenService;

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		setHeader(response); 
		String header = request.getHeader("Authorization");
			
 		if(ApiUtil.isNotEmpty(header)){
 			
 			String tipoToken = getTipoToken(header.trim());
 			String accesToken = getToken(header.trim());
 			
 			if(tipoToken.equalsIgnoreCase(TIPO_TOKEN)){
 				String urlRecurso = getUrl(request); 
 				tokenService.findAccessTokenAutenticado(accesToken, tipoToken, urlRecurso, request.getMethod());
 				
 			}else{
 				throw new AccesTokenException("Não foi localizado no Header Authorization o tipo '"+TIPO_TOKEN+"'.");
 			}
 			
 		}else{ 
			header = request.getHeader("access-control-request-headers");
			if (header == null || !header.toLowerCase().contains("authorization")) {
				throw new AccesTokenException("Não foi localizado o Header Authorization.");
			}			
 		}	
 		return true;
	}
	
	private String getTipoToken(String header) {
		return header.substring(0, 6);
	}
	
	private String getToken(String header) {
		return header.substring(7, header.length());
	}
	
	private String getUrl(HttpServletRequest request) throws Exception {
		try {
			String url[] = null;
			if(ApiUtil.isNotEmpty(request.getPathInfo()) && request.getPathInfo().split("/").length > 2){
				url = request.getPathInfo().split("/");
				
			}else if(ApiUtil.isNotEmpty(request.getServletPath())){
				url = request.getServletPath().split("/");
			}
			return "/" + url[1] + "/" + url[2];
		} catch (Exception e) { 
			logger.error("A URL não está no padrão modulo/recurso.", e); 
			throw new OperacaoNaoRealizadaException("A URL não está no padrão modulo/recurso.");
		} 	
	}

	private void setHeader(HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE, PATCH");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Authorization, X-requested-with, Content-Type");
	}

}
