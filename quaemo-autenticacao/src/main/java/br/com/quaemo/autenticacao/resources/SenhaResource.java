package br.com.quaemo.autenticacao.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.quaemo.annotation.RestApiController;
import br.com.quaemo.autenticacao.service.UsuarioService;
import br.com.quaemo.resources.ResourceSupport;
import br.com.quaemo.vo.SenhaVO;

@RestApiController("/usuarios")
public class SenhaResource extends ResourceSupport {

	@Autowired
	private UsuarioService usuarioService;
	
	@RequestMapping(value="/{login}/senha", method = RequestMethod.PUT)
    public ResponseEntity<Void> senha(@PathVariable("login") String login, @RequestBody SenhaVO senha ){	
		usuarioService.updateSenhaUsuario(login,senha.getSenha(), senha.getConfirmaSenha());
		return ok();
	} 
	
}