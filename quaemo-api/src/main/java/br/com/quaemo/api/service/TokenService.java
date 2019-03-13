package br.com.quaemo.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import br.com.quaemo.api.domain.AccesTokenAutenticado;
import br.com.quaemo.api.domain.Perfil;
import br.com.quaemo.api.exceptions.AccesTokenException;
import br.com.quaemo.api.exceptions.AccessoNegadoException;
import br.com.quaemo.api.exceptions.DadoInconsistenteException;
import br.com.quaemo.api.exceptions.MetodoException;
import br.com.quaemo.api.exceptions.OperacaoNaoRealizadaException;
import br.com.quaemo.api.vo.UrlServicosVO;
import br.com.quaemo.api.vo.UsuarioVO;

@Service
public class TokenService {
	
	@Autowired
	private UrlServicosVO urlServicos;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private RequestService requestService;

	private static AccesTokenAutenticado token;
	

	public AccesTokenAutenticado findAccessTokenAutenticado(String accessToken, String tokenType, String urlRecurso, String metodo) {
		try {
			ResponseEntity<AccesTokenAutenticado> response = restTemplate.exchange(requestService.get(montaURI(accessToken, tokenType, urlRecurso, metodo.toUpperCase())), AccesTokenAutenticado.class);
			if (response.getStatusCodeValue() == 200) {
				token = response.getBody();
			}
		} catch (HttpClientErrorException e) {
			String detalhe = e.getResponseBodyAsString().toString();
			String responseErro[] = detalhe.split(":");
			String erro = responseErro[3].toString().replace("}", "").replace("\"", "");
			switch (e.getRawStatusCode()) {
				case 403:
					throw new AccessoNegadoException(erro);
				case 401:
					throw new AccesTokenException(erro);
				case 400:
					throw new DadoInconsistenteException(erro);
				case 405:
					throw new MetodoException(erro);
				default:
					throw new OperacaoNaoRealizadaException("O token não foi validado.");
			}
		}
		return token;
	}

	public AccesTokenAutenticado getToken() {
		return token;
	}

	public Integer getCodUsuario() {
		return token.getCodUsuario();
	}
	
	public UsuarioVO getUsuario() {
		return new UsuarioVO(token.getCodUsuario(), token.getNomeUsuario(), token.getLogin(), token.getCodPerfil(), token.getNomePerfil()); 
	}
	
	public Integer getCodPerfil(){
		return token.getCodPerfil(); 
	}

	public Perfil getPerfil(){
		return new Perfil(getCodPerfil(), token.getNomePerfil(), token.getCodSistema());
	}
	
	public String getAccess_token(){
		return token.getAccess_token();
	}
	
	public String getToken_type(){
		return token.getToken_type();
	}

	private String montaURI(String accessToken, String tokenType, String urlRecurso, String metodo){
	 return urlServicos.getUrlAutenticacao()+"/token/autenticado?accessToken="+accessToken+"&tokenType="+tokenType+"&urlRecurso="+urlRecurso+"&metodo="+metodo;
	}
	
}
