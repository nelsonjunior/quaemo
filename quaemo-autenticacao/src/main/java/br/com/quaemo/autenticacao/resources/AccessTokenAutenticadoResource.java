package br.com.quaemo.autenticacao.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.quaemo.annotation.RestApiController;
import br.com.quaemo.autenticacao.service.AccessTokenAutenticadoService;
import br.com.quaemo.resources.ResourceSupport;

@RestApiController(value="/token", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
public class AccessTokenAutenticadoResource extends ResourceSupport {
	
	@Autowired
	private AccessTokenAutenticadoService service;

	@RequestMapping("/autenticado")
	@ResponseBody
	public ResponseEntity<?> getToken(@RequestParam(name = "accessToken", required = false) String accessToken
						            , @RequestParam(name = "tokenType",   required = false) String tokenType
						            , @RequestParam(name = "urlRecurso",  required = false) String urlRecurso
						            , @RequestParam(name = "metodo",      required = false) String metodo) {
		return ok(service.findAccessTokenAutenticado(accessToken, tokenType, urlRecurso, metodo));
	}
}
