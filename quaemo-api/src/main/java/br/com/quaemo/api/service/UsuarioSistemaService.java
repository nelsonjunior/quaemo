package br.com.quaemo.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import br.com.quaemo.api.vo.UrlServicosVO;
import br.com.quaemo.api.domain.MenuUsuarioSistema;
import br.com.quaemo.api.domain.UsuarioSistema;
import br.com.quaemo.api.exceptions.AccesTokenException;
import br.com.quaemo.api.exceptions.AccessoNegadoException;
import br.com.quaemo.api.exceptions.DadoInconsistenteException;
import br.com.quaemo.api.exceptions.MetodoException;
import br.com.quaemo.api.exceptions.OperacaoNaoRealizadaException;
import br.com.quaemo.api.support.ApiUtil;
import br.com.quaemo.api.vo.SenhaVO;


@Service
public class UsuarioSistemaService {

	@Autowired
	private UrlServicosVO urlServicos;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private RequestService requestService;
	

	public UsuarioSistema findUsuarioSistemaByLogin(String login) {
		String parametros = "";
		if (ApiUtil.isNotEmpty(login)) {
			parametros = parametros + "login=" + login;
			String uri = urlServicos.getUrlSistemaUsuario() + "?" + parametros;
			ResponseEntity<List<UsuarioSistema>> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, requestService.httpEntityGetAutenticado(), new ParameterizedTypeReference<List<UsuarioSistema>>() {});
			try {
				return responseEntity.getBody().get(0);
			} catch (HttpClientErrorException e) {
				verificaErro(e);
				System.out.println(e.getMessage());
			}
		}
		return null;
	}

	public UsuarioSistema findUsuarioSistemaById(Long codUsuario){
		String uri = urlServicos.getUrlSistemaUsuario()+ codUsuario.toString();
		return restTemplate.exchange(requestService.getAutenticado(uri), UsuarioSistema.class).getBody();
	}

	public List<UsuarioSistema> findUsuariosSistema(String nome,String login, Integer codPerfil){
		String parametros="";
		if (ApiUtil.isNotEmpty(nome)) {
			parametros += "nome="+nome+"&";
		}
		if (ApiUtil.isNotEmpty(login)) {
			parametros += "login="+login+"&";
		}
		if (ApiUtil.isNotEmpty(codPerfil)) { 
			parametros += "codPerfil="+codPerfil+"&";
		} 
		String uri = urlServicos.getUrlSistemaUsuario()+"?"+parametros;
		try {
			return restTemplate.exchange(uri, HttpMethod.GET, requestService.httpEntityGetAutenticado(),new ParameterizedTypeReference<List<UsuarioSistema>>() {}).getBody();
		} catch (HttpClientErrorException e) {
			verificaErro(e);
			System.out.println(e.getMessage());	
	    }
		return null;
	}
	

	
	public List<MenuUsuarioSistema> findMenusUsuarioSistema(){
		try {
			String uri = urlServicos.getUrlSistemaUsuarioMenus();
			return restTemplate.exchange(uri, HttpMethod.GET, requestService.httpEntityGetAutenticado(),new ParameterizedTypeReference<List<MenuUsuarioSistema>>() {}).getBody();
		} catch (HttpClientErrorException e) {
			verificaErro(e);
			System.out.println(e.getMessage());	
	    }
		return null;
	}

	public void alterarSenha(String login,SenhaVO senhaVO){
		try {
			String uri = urlServicos.getUrlUsuarioSenha().replace("{login}", login);
			restTemplate.put(uri, requestService.httpEntityPostPutDeleteAutenticado(senhaVO), SenhaVO.class);
		} catch (HttpClientErrorException e) {
			verificaErro(e);
			System.out.println(e.getMessage());	
		}
	}

	public void reativarUsuarioSistema(UsuarioSistema usuarioSistema){
		if (ApiUtil.isNotEmpty(usuarioSistema.getCodigo())) {
			try {
				String uri = urlServicos.getUrlSistemaUsuarioStatus().replace("{codUsuario}", usuarioSistema.getCodigo().toString())+"?status=ATIVAR";
				restTemplate.put(uri, requestService.httpEntityPostPutDeleteAutenticado(usuarioSistema), UsuarioSistema.class);
			} catch (HttpClientErrorException e) {
				verificaErro(e);
				System.out.println(e.getMessage());	
			}
		}
	}
	
	public void atualizaUsuarioSistema(UsuarioSistema usuarioSistema){
		if (ApiUtil.isNotEmpty(usuarioSistema.getCodigo())) {
			try {
				String uri = urlServicos.getUrlSistemaUsuario() + usuarioSistema.getCodigo().toString();
				restTemplate.put(uri, requestService.httpEntityPostPutDeleteAutenticado(usuarioSistema), UsuarioSistema.class);
			} catch (HttpClientErrorException e) {
				verificaErro(e);
				System.out.println(e.getMessage());	
			}
		}
	} 
	
	public void removeUsuarioSistema(UsuarioSistema usuarioSistema){
		if (ApiUtil.isEmpty(usuarioSistema.getCodigo())) {
			throw new DadoInconsistenteException("Usuário inválido");
		}
		
		try {
			String uri = urlServicos.getUrlSistemaUsuario() + usuarioSistema.getCodigo().toString();
			restTemplate.exchange(uri, HttpMethod.DELETE, requestService.httpEntityDeleteAutenticado(), String.class);
		} catch (HttpClientErrorException exception) {
			verificaErro(exception);
		}
	}
	
	public void criaUsuarioSistema(UsuarioSistema usuarioSistema){
		try {
			String uri = urlServicos.getUrlSistemaUsuario();
			restTemplate.postForObject(uri, requestService.httpEntityPostPutDeleteAutenticado(usuarioSistema), UsuarioSistema.class);		
		} catch (HttpClientErrorException exception) {
			verificaErro(exception);
		}
	}
	
	private void verificaErro(HttpClientErrorException e) {

		String detalhe = e.getResponseBodyAsString().toString();
		String responseErro[] = detalhe.split(":");
		String erro = "";

		if (responseErro != null && responseErro.length > 0) {
			erro = responseErro[3].toString().replace("}", "").replace("\"", "");
		}

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
}
