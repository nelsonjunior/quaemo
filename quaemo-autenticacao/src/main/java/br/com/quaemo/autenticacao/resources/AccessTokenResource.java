package br.com.quaemo.autenticacao.resources;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.quaemo.annotation.RestApiController;
import br.com.quaemo.autenticacao.service.AccessTokenService;
import br.com.quaemo.resources.ResourceSupport;

@RestApiController(value="/oauth2", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
public class AccessTokenResource extends ResourceSupport{
	
	@Autowired
	private AccessTokenService service;

	@RequestMapping("/token")
	@ResponseBody
	public ResponseEntity<?> getToken(@Context HttpServletRequest request) throws Exception {
		return post(service.getAccessToken(request));
	}
}
