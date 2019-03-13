package br.com.quaemo.autenticacao.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.quaemo.domain.MenuUsuarioSistema;

public interface MenuUsuarioSistemaDao extends JpaRepository<MenuUsuarioSistema, Integer>{

	@Query(value = "SELECT o FROM MenuUsuarioSistema o WHERE o.visivel = true AND o.codMenuPai IS NULL AND o.codSistema = :codSistema AND o.codUsuario= :codUsuario")
	List<MenuUsuarioSistema> findListaMenu(@Param("codSistema") Integer codSistema, @Param("codUsuario") Integer codUsuario);

	@Query(value = "SELECT o FROM MenuUsuarioSistema o WHERE o.visivel = true AND o.codSistema = :codSistema AND o.codUsuario= :codUsuario AND o.codMenuPai = :codMenu")
	List<MenuUsuarioSistema> findListaSubMenuByMenu(@Param("codSistema") Integer codSistema, @Param("codUsuario") Integer codUsuario, @Param("codMenu") Integer codMenu);
}
