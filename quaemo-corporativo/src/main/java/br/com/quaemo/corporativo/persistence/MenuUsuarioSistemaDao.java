package br.com.quaemo.corporativo.persistence;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.quaemo.domain.MenuUsuarioSistema;

public interface MenuUsuarioSistemaDao extends JpaRepository<MenuUsuarioSistema, Integer> {
	
	@Query(value = "SELECT o FROM MenuUsuarioSistema o WHERE visivel = true AND codMenuPai IS NULL AND codSistema = ?1 AND codUsuario= ?2")
//	@Query(value = "SELECT * FROM VW_MENU_USUARIO_SISTEMA WHERE IND_VISIVEL = 1 AND COD_MENU_PAI IS NULL AND COD_SISTEMA = ?1 AND COD_USUARIO= ?2", nativeQuery = true)
	List<MenuUsuarioSistema> findMenus(Integer codSistema, Integer codUsuario);

	@Query(value = "SELECT o FROM MenuUsuarioSistema o WHERE visivel = true AND codMenuPai IS NULL AND codSistema = ?1 AND codUsuario= ?2 AND codMenuPai = ?3")
//	@Query(value = "SELECT * FROM VW_MENU_USUARIO_SISTEMA WHERE IND_VISIVEL = 1 AND COD_SISTEMA = ?1 AND COD_USUARIO = ?2 AND COD_MENU_PAI = ?3", nativeQuery = true)
	List<MenuUsuarioSistema> findMenusByMenuPai(Integer codSistema, Integer codUsuario, Integer codMenu);
}
