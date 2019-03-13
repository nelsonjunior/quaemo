package br.com.quaemo.api.service;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import br.com.quaemo.api.domain.AccessToken;


@Service
public class RequestService {

	@Autowired
	private TokenService tokenService;
	
	private static final String AUTH = "Authorization";
	private static final String CONTENT = "Content-Type";
	private static final String JSON = "application/json";
	private static final String X_FORM = "application/x-www-form-urlencoded";

	public RequestEntity<Void> get(String uri) {
		return RequestEntity.get(URI.create(uri)).header(CONTENT, X_FORM).build();
	}
	
	public RequestEntity<Void> get(String uri, AccessToken token){	
		return RequestEntity.get(URI.create(uri)).header(CONTENT, X_FORM).header(AUTH, token.getToken_type()+" "+token.getAccess_token()).build();
	}
	
	public RequestEntity<Void> getAutenticado(String uri){		
		return RequestEntity.get(URI.create(uri)).header(CONTENT, X_FORM).header(AUTH, getTokenAutenticaco()).build();
	}
	
	public RequestEntity<Void> post(String uri){		
		return RequestEntity.post(URI.create(uri)).header(CONTENT, JSON).header(AUTH, getTokenAutenticaco()).build();
	}
	
	public RequestEntity<Void> put(String uri){		
		return RequestEntity.put(URI.create(uri)).header(CONTENT, JSON).header(AUTH, getTokenAutenticaco()).build();
	}
	
	public RequestEntity<Void> delete(String uri){		
		return RequestEntity.delete(URI.create(uri)).header(CONTENT, X_FORM).header(AUTH, getTokenAutenticaco()).build();
	}
	
	public HttpEntity<MultiValueMap<String, String>> httpEntityGetAutenticado(){
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<MultiValueMap<String, String>>(map, getHeadersGet());
		return entity;
	}
	
	public HttpEntity<?> httpEntityDeleteAutenticado() {
		return new HttpEntity<Object>(getHeadersPostPutDelete());
	}
	
	public <T> HttpEntity<T> httpEntityPostPutDeleteAutenticado(T t) {
		return new HttpEntity<T>(t,getHeadersPostPutDelete());
	}

	public HttpHeaders getHeadersPostPutDelete() {
		HttpHeaders headers = new HttpHeaders();
		headers.add(CONTENT, JSON);
		headers.add(AUTH, getTokenAutenticaco());
		return headers;
	}
	
	public HttpHeaders getHeadersGet() {
		HttpHeaders headers = new HttpHeaders();
		headers.add(CONTENT, X_FORM);
		headers.add(AUTH, getTokenAutenticaco());
		return headers;
	}
	
	private String getTokenAutenticaco(){
		return tokenService.getToken_type()+" "+tokenService.getAccess_token();
	}
}
