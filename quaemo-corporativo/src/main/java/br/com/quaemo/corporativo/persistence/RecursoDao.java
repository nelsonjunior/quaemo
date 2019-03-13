package br.com.quaemo.corporativo.persistence;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import br.com.quaemo.domain.Domain;
import br.com.quaemo.domain.Recurso;

public interface RecursoDao extends JpaRepository<Recurso, Integer> {
	
	List<Recurso> findByCodSistemaAndExcluido(Integer codSistema, boolean excluido);

	Recurso findByCodSistemaAndChave(Integer codSistema, String chave);

	@Modifying
	@Transactional
	@Query(value = "UPDATE Recurso SET excluido = true WHERE codSistema = ?1 ")
//	@Query(value = "update TB_RECURSO set IND_EXCLUIDO = 1 where COD_SISTEMA = ?1 ", nativeQuery = true)
	int removeRecursosBySistema(Integer codSistema);

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM "+Domain.SCHEMA+".RL_PERFIL_RECURSO WHERE COD_RECURSO IN (SELECT COD_RECURSO from "+Domain.SCHEMA+".TB_RECURSO WHERE COD_SISTEMA = ?1 )", nativeQuery = true)
	int deleteRecursoPerfilBySistema(Integer codSistema);

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM "+Domain.SCHEMA+".RL_APLICACAO_RECURSO WHERE COD_RECURSO IN (SELECT COD_RECURSO from "+Domain.SCHEMA+".TB_RECURSO WHERE IND_EXCLUIDO = 1 and COD_SISTEMA = ?1 )", nativeQuery = true)
	int deleteRecursoAplicacaoBySistema(Integer codSistema);

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM Recurso WHERE excluido = true and codSistema = ?1")
//	@Query(value = "DELETE FROM TB_RECURSO WHERE IND_EXCLUIDO = 1 and COD_SISTEMA = ?1", nativeQuery = true)
	int deleteRecursoBySistema(Integer codSistema);

	@Modifying
	@Transactional
	@Query(value = "INSERT INTO "+Domain.SCHEMA+".RL_PERFIL_RECURSO(COD_PERFIL,COD_RECURSO,NOM_METODO) VALUES (?1,?2,?3) ", nativeQuery = true)
	int saveRecursoPerfil(Integer codPerfil, Integer codRecurso, String metodo);
}
