package br.com.quaemo.corporativo.resources;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.quaemo.annotation.RestApiController;
import br.com.quaemo.corporativo.service.UsuarioService;
import br.com.quaemo.domain.UsuarioSistema;
import br.com.quaemo.exceptions.OperacaoNaoRealizadaException;
import br.com.quaemo.resources.ResourceSupport;
import br.com.quaemo.support.Util;

@RestApiController("/corporativo/sistemas/{codSistema}/usuarios")
public class UsuariosSistemaResource extends ResourceSupport {
	
	@Autowired
	private UsuarioService usuarioService;
	
	CacheControl cacheControl = CacheControl.maxAge(5L, TimeUnit.MINUTES);

	
	@GetMapping 
	@ResponseBody
	public ResponseEntity<?> findUsuarios(@PathVariable("codSistema") Integer codSistema
			                            , @RequestParam(name = "nome", required = false) String nome
			                            , @RequestParam(name = "login", required = false) String login
			                            , @RequestParam(name = "codPerfil", required = false) Integer codPerfil) { 
		return ok(usuarioService.findUsuariosBySistema(nome, login, codSistema, codPerfil));
	}

	@GetMapping("/{codUsuario}")
	@ResponseBody
	public ResponseEntity<?> findUsuario(@PathVariable("codSistema") Integer codSistema, @PathVariable("codUsuario") Integer codUsuario) {
		return ok(cacheControl, usuarioService.findByCodigoAndSistema(codUsuario, codSistema));
	}

	@PostMapping
	@ResponseBody
	public ResponseEntity<?> saveUsuario(@PathVariable("codSistema") Integer codSistema, @RequestBody UsuarioSistema usuarioSistema) {
		Integer codUsuario = usuarioService.saveUsuarioSistema(codSistema, usuarioSistema);
		return post(usuarioService.findByCodigoAndSistema(codUsuario, codSistema));
	}

	@PutMapping("/{codUsuario}")
	@ResponseBody
	public ResponseEntity<Void> atualizaUsuario(@PathVariable("codSistema") Integer codSistema
			                                  , @PathVariable("codUsuario") Integer codUsuario
			                                  , @RequestBody UsuarioSistema usuarioSistema) {
		usuarioService.atualizaUsuarioSistema(codSistema, codUsuario, usuarioSistema);
		return ok();
	}

	@PutMapping("/{codUsuario}/status/{status}")
	public ResponseEntity<Void> alteraStatusUsuario(@PathVariable("codSistema") Integer codSistema
			                                      , @PathVariable("codUsuario") Integer codUsuario
			                                      , @PathVariable("status") String status
			                                      , @RequestBody UsuarioSistema usuarioSistema ) {
		if(Util.isEmpty(status)) {
			throw new OperacaoNaoRealizadaException("Status é obrigatório");
		}else if(status.toUpperCase().equals("ATIVAR")) {
			usuarioService.ativarUsuarioSistema(codSistema, codUsuario, usuarioSistema);
			
		}else if(status.toUpperCase().equals("INATIVAR")) {
			usuarioService.inativarUsuarioSistema(codSistema, codUsuario, usuarioSistema);
		}else {
			throw new OperacaoNaoRealizadaException("Status \""+status+"\" não é aceito");
		}
		return ok();
	}

	@DeleteMapping("/{codUsuario}")
	public ResponseEntity<Void> removeUsuario(@PathVariable("codSistema") Integer codSistema, @PathVariable("codUsuario") Integer codUsuario) {
		usuarioService.removeUsuarioSistema(codSistema, codUsuario);
		return ok();
	}
}
