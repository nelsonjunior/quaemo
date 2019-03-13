package br.com.quaemo.corporativo.persistence;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.repository.query.Param;

import br.com.quaemo.domain.Domain;
import br.com.quaemo.domain.UsuarioSistema;

public interface UsuarioSistemaDao extends JpaRepository<UsuarioSistema, Integer> {


	UsuarioSistema findByLoginAndCodSistema(String login, Integer codSistema);

	UsuarioSistema findByCodigoAndCodSistema(Integer codigo, Integer codSistema);

	@Query(value = "   SELECT * "
		   	     + "\n FROM "+Domain.SCHEMA+".VW_USUARIO_SISTEMA "
		   	     + "\n WHERE ( '%' = ?1 OR  UPPER(NOM_USUARIO) LIKE UPPER('%'||?1||'%')  ) "
		   	     + "\n AND ( '%' = ?2 OR UPPER(NOM_LOGIN) = UPPER(?2) ) "
		   	     + "\n AND COD_SISTEMA  = ?3 "
		   	     + "\n AND IND_EXCLUIDO = 0 "
		   	     + "\n AND (COD_PERFIL  = ?4 OR 0 = ?4) "
		   	     + "\n ORDER BY NOM_USUARIO ", nativeQuery = true)
	List<UsuarioSistema> findByParametros(String nome, String login, Integer codSistema, Integer codPerfil);
	  
	
	@Modifying
	@Transactional
	@Query(value = "  MERGE INTO "+Domain.SCHEMA+".RL_USUARIO_PERFIL RL ("
			+ "\n     SELECT :codUsuario     AS COD_USUARIO"
			+ "\n          , :codPerfil      AS COD_PERFIL"
			+ "\n          , :codResponsavel AS COD_RESPONSAVEL"
			+ "\n          , :excluido       AS IND_EXCLUIDO "
			+ "\n     FROM DUAL"
			+ "\n) TP  ON (RL.COD_USUARIO = TP.COD_USUARIO AND RL.COD_PERFIL = TP.COD_PERFIL) "
			+ "\n WHEN MATCHED THEN UPDATE "
			+ "\n SET RL.DAT_ATUALIZACAO             = :dataAtualizacao "
			+ "\n   , RL.COD_RESPONSAVEL_ATUALIZACAO = TP.COD_USUARIO"
			+ "\n   , RL.IND_EXCLUIDO                = TP.IND_EXCLUIDO"
			+ "\n   , RL.IND_ATIVO                   = 1 "
			+ "\n WHEN NOT MATCHED THEN "
			+ "\n INSERT (RL.COD_USUARIO, RL.COD_PERFIL, RL.DAT_CADASTRO,  RL.COD_RESPONSAVEL_CADASTRO) "
			+ "\n VALUES (TP.COD_USUARIO, TP.COD_PERFIL, :dataAtualizacao, TP.COD_RESPONSAVEL)", nativeQuery = true)
	int saveUsuarioPerfilMERGE_other(@Param("codUsuario") Integer codUsuario, @Param("codPerfil") Integer codPerfil, @Param("codResponsavel") Integer codResponsavel, @Param("dataAtualizacao") Date dataAtualizacao, @Param("excluido") boolean excluido);
	
	@Modifying
	@Transactional
	@Query(value = " INSERT INTO "+Domain.SCHEMA+".RL_USUARIO_PERFIL (COD_USUARIO, COD_PERFIL, COD_RESPONSAVEL_CADASTRO, DAT_CADASTRO, IND_ATIVO, IND_EXCLUIDO) "
			     + "\n VALUES(:codUsuario, :codPerfil, :codResponsavel, :dataAtualizacao, 1, :excluido)"
			     + "\n ON DUPLICATE KEY UPDATE "
			     + "\n   DAT_ATUALIZACAO             = VALUES(DAT_CADASTRO)"
			     + "\n , COD_RESPONSAVEL_ATUALIZACAO = VALUES(COD_RESPONSAVEL_CADASTRO)"
			     + "\n , IND_EXCLUIDO                = VALUES(IND_EXCLUIDO)"
			     + "\n , IND_ATIVO                   = VALUES(IND_ATIVO)", nativeQuery = true)
	int saveUsuarioPerfil(@Param("codUsuario") Integer codUsuario, @Param("codPerfil") Integer codPerfil, @Param("codResponsavel") Integer codResponsavel, @Param("dataAtualizacao") Date dataAtualizacao, @Param("excluido") boolean excluido);

	@Modifying
	@Transactional
	@Query(value = "   MERGE INTO "+Domain.SCHEMA+".RL_USUARIO_PERFIL RL ("
		   	     + "\n       SELECT USUARIO_PERFIL.* "
		   	     + "\n       FROM "+Domain.SCHEMA+".RL_USUARIO_PERFIL USUARIO_PERFIL "
		   	     + "\n           INNER JOIN "+Domain.SCHEMA+".TB_PERFIL PERFIL ON PERFIL.COD_PERFIL = USUARIO_PERFIL.COD_PERFIL "
		   	     + "\n       WHERE USUARIO_PERFIL.IND_EXCLUIDO = 0 "
		   	     + "\n       AND PERFIL.COD_SISTEMA            = :codSistema "
		   	     + "\n       AND USUARIO_PERFIL.COD_USUARIO    = :codUsuario "
		   	     + "\n) TP  ON (TP.COD_USUARIO = RL.COD_USUARIO AND TP.COD_PERFIL = RL.COD_PERFIL) "
		   	     + "\n WHEN MATCHED THEN UPDATE "
		   	     + "\n SET  RL.DAT_ATUALIZACAO             = :dataAtualizacao "
		   	     + "\n    , RL.COD_RESPONSAVEL_ATUALIZACAO = :codResponsavel"
		   	     + "\n    , RL.IND_EXCLUIDO                = 1 ", nativeQuery = true)
	int removeUsuarioPerfilBySistemaMERGE(@Param("codUsuario") Integer codUsuario, @Param("codSistema") Integer codSistema, @Param("codResponsavel") Integer codResponsavel, @Param("dataAtualizacao") Date dataAtualizacao);
	
	@Modifying
	@Transactional
	@Query(value ="UPDATE "+Domain.SCHEMA+".RL_USUARIO_PERFIL "
				+ "\n SET  DAT_ATUALIZACAO             = :dataAtualizacao "
				+ "\n    , COD_RESPONSAVEL_ATUALIZACAO = :codResponsavel "
				+ "\n    , IND_EXCLUIDO                = 1 "
				+ "\n WHERE COD_USUARIO = :codUsuario"
				+ "\n AND COD_PERFIL IN(SELECT COD_PERFIL FROM TB_PERFIL WHERE COD_SISTEMA = :codSistema)   ", nativeQuery = true)
	int removeUsuarioPerfilBySistema(@Param("codUsuario") Integer codUsuario, @Param("codSistema") Integer codSistema, @Param("codResponsavel") Integer codResponsavel, @Param("dataAtualizacao") Date dataAtualizacao);

	@Modifying
	@Transactional
	@Query(value = "   UPDATE "+Domain.SCHEMA+".RL_USUARIO_PERFIL "
			     + "\n SET IND_ATIVO                   = 0 "
			     + "\n   , DAT_ATUALIZACAO             = :dataAtualizacao "
			     + "\n   , COD_RESPONSAVEL_ATUALIZACAO = :codResponsavel "
			     + "\n WHERE COD_USUARIO = :codUsuario "
			     + "\n AND COD_PERFIL    = :codPerfil", nativeQuery = true)
	int removeUsuarioPerfil(@Param("codUsuario") Integer codUsuario, @Param("codPerfil") Integer codPerfil, @Param("codResponsavel") Integer codResponsavel, @Param("dataAtualizacao") Date dataAtualizacao);
}
