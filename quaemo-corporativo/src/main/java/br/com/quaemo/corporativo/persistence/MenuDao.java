package br.com.quaemo.corporativo.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import br.com.quaemo.domain.Domain;
import br.com.quaemo.domain.Menu;

public interface MenuDao extends JpaRepository<Menu, Integer> {
	
	Menu findByCodSistemaAndChave(Integer codSistema, String chave);

	@Modifying
	@Transactional
	@Query(value = "UPDATE Menu SET excluido = true WHERE codSistema=?1 ")
//	@Query(value = "update TB_MENU set IND_EXCLUIDO = 1 where COD_SISTEMA=?1 ", nativeQuery = true)
	int removeMenuBySistema(Integer codSistema);

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM "+Domain.SCHEMA+".RL_PERFIL_MENU WHERE COD_MENU IN (SELECT COD_MENU FROM "+Domain.SCHEMA+".TB_MENU WHERE COD_SISTEMA = ?1 )", nativeQuery = true)
	int deleteMenuPerfilBySistema(Integer codSistema);

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM Menu WHERE excluido = true and codSistema = ?1")
	int deleteMenuBySistema(Integer codSistema);
}
