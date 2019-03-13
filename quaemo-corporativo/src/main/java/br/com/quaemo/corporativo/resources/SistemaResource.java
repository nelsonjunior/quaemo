package br.com.quaemo.corporativo.resources;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.quaemo.annotation.RestApiController;
import br.com.quaemo.corporativo.service.SistemaService;
import br.com.quaemo.domain.Sistema;
import br.com.quaemo.resources.ResourceSupport;

@RestApiController("/corporativo/sistemas")
public class SistemaResource extends ResourceSupport {
	
	@Autowired
	private SistemaService sistemaService; 
	 
	CacheControl cacheControl = CacheControl.maxAge(5L, TimeUnit.MINUTES);

	@GetMapping("/{codSistema}")
	@ResponseBody
	public ResponseEntity<?> findSistemaById(@PathVariable("codSistema") Integer codSistema) {
		return ok(cacheControl, sistemaService.findById(codSistema)); 
	}

	@PutMapping("/{codSistema}")
	public ResponseEntity<?> saveSistema(@PathVariable("codSistema") Integer codSistema, @RequestBody Sistema sistema) {
		sistemaService.saveSistema(codSistema, sistema);
		return ok(sistemaService.findById(codSistema));
	}

	@GetMapping("/{codSistema}/recursos")
	@ResponseBody
	public ResponseEntity<?> findRecursoSistemaBySistema(@PathVariable("codSistema") Integer codSistema) {
		return ok(cacheControl, sistemaService.findRecursosBySistema(codSistema)); 
	}

	@GetMapping("/{codSistema}/perfis")
	@ResponseBody
	public ResponseEntity<?> findPerfisSistemaBySistema(@PathVariable("codSistema") Integer codSistema) {
		return ok(cacheControl, sistemaService.findAllPerfilBySistema(codSistema));  
	}

	@GetMapping("/{codSistema}/perfis/{codPerfil}")
	@ResponseBody
	public ResponseEntity<?> findPerfilSistemaBySistema(@PathVariable("codSistema") Integer codSistema, @PathVariable("codPerfil") Integer codPerfil) {
		return ok(cacheControl, sistemaService.findPerfilBySistema(codPerfil, codSistema));   
	}

	@GetMapping("/{codSistema}/aplicacao-cliente")
	@ResponseBody
	public ResponseEntity<?> findAplicacaoClienteBySistema(@PathVariable("codSistema") Integer codSistema) {
		return ok(cacheControl, sistemaService.findAplicacaoClienteBySistema(codSistema));   
	}
	  
}
