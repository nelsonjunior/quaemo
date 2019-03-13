package br.com.quaemo.corporativo.resources;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.quaemo.annotation.RestApiController;
import br.com.quaemo.corporativo.service.UsuarioService;
import br.com.quaemo.resources.ResourceSupport;
import br.com.quaemo.support.PaginacaoUtils;
import br.com.quaemo.vo.FotoVO;

@RestApiController("/corporativo/usuarios")
public class UsuarioResource extends ResourceSupport {
	
	@Autowired
	private UsuarioService usuarioService;
	
	CacheControl cacheControl = CacheControl.maxAge(5L, TimeUnit.MINUTES);

	@GetMapping
	@ResponseBody
	public ResponseEntity<?> findUsuarios(@RequestParam(name = "page", required = false)  Integer page
							            , @RequestParam(name = "size", required = false)  Integer size
							            , @RequestParam(name = "nome", required = false)  String nome
							            , @RequestParam(name = "login", required = false) String login) {
		return ok(usuarioService.findUsuarios(nome, login, PaginacaoUtils.getPage(page, size, true, "nome")));
	}

	@GetMapping("/{login}/")
	@ResponseBody
	public ResponseEntity<?> findUsuario(@PathVariable("login") String login) {
		return ok(cacheControl, this.usuarioService.findByLogin(login));
	}

	@GetMapping("/{login}/foto")
	@ResponseBody
	public ResponseEntity<?> findFotoUsuario(@PathVariable("codigo") Integer codigo) {
		return ok(cacheControl, new FotoVO(usuarioService.findByCodigo(codigo).getFoto()));
	}

	@PutMapping("/{codigo}/foto")
	public ResponseEntity<Void> saveFotoUsuario(@PathVariable("codigo") Integer codigo, @RequestBody FotoVO fotoVO) {
		usuarioService.saveFotoUsuario(codigo, fotoVO);
		return ok();
	}
}
