package br.com.quaemo.api.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.quaemo.api.vo.PageVO;
import br.com.quaemo.api.vo.UrlServicosVO;
import br.com.quaemo.api.domain.Usuario;
import br.com.quaemo.api.support.ApiUtil;

@Service
public class UsuarioService {

	@Autowired
	private UrlServicosVO urlServicos;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private RequestService requestService;	

	public Usuario findUsuarioByLogin(String login){
		String url = urlServicos.getUrlUsuarioLogin().replace("{login}", login);
		return restTemplate.exchange(requestService.getAutenticado(url), Usuario.class).getBody();
	}
	
	public PageVO<Usuario> findUsuarios(String nome, String login, String page, String size){
		StringBuilder sb = new StringBuilder("?");
		if(ApiUtil.isNotEmpty(nome)){ 
			sb.append("nome=").append(nome).append("&");
		}
		if(ApiUtil.isNotEmpty(login)){ 
			sb.append("login=").append(login).append("&");
		}
		sb.append("page=").append((page != null ? page : "0")).append("&");
		sb.append("size=").append((size != null ? size : "10")).append("&");
		
		String url = urlServicos.getUrlUsuario()+sb.toString(); 
		return restTemplate.exchange(url, HttpMethod.GET, requestService.httpEntityGetAutenticado(),new ParameterizedTypeReference<PageVO<Usuario>>() {}).getBody();
	}

}
