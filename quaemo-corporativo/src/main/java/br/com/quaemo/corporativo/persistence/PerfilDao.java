package br.com.quaemo.corporativo.persistence;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import br.com.quaemo.domain.Domain;
import br.com.quaemo.domain.Perfil;

public interface PerfilDao extends JpaRepository<Perfil, Integer> {

	List<Perfil> findByCodSistema(Integer codSistema);

	Perfil findByCodigoAndCodSistema(Integer codigo, Integer codSistema);
	
	Perfil findByChaveAndCodSistema(String chave, Integer codSistema);

	@Modifying
	@Transactional
	@Query(value = " UPDATE Perfil SET excluido = true WHERE codSistema = ?1")
//	@Query(value = " UPDATE TB_PERFIL SET IND_EXCLUIDO = 1 WHERE COD_SISTEMA = ?1", nativeQuery = true)
	int removePerfisBySistema(Integer codSistema);

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM "+Domain.SCHEMA+".RL_USUARIO_PERFIL WHERE COD_PERFIL IN (SELECT COD_PERFIL FROM "+Domain.SCHEMA+".TB_PERFIL WHERE IND_EXCLUIDO = 1 AND COD_SISTEMA = ?1 )", nativeQuery = true)
	int deletePerfilUsuarioBySistema(Integer codSistema);

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM Perfil WHERE excluido = true AND codSistema = ?1 ")
//	@Query(value = "DELETE FROM TB_PERFIL WHERE IND_EXCLUIDO = 1 AND COD_SISTEMA = ?1 ", nativeQuery = true)
	int deletePerfisBySistema(Integer codSistema);
}
