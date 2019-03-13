package br.com.quaemo.corporativo.persistence;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import br.com.quaemo.domain.AccessToken;

public interface AccessTokenDao extends JpaRepository<AccessToken, String> {
	
	@Query("select a from AccessToken a where a.valido = true and a.access_token = ?1 ")
	AccessToken findAccessTokenValido(String access_token);


	@Modifying
	@Transactional 
	@Query(value = "UPDATE AccessToken SET dataAtualizacao = :dataAtualizacao WHERE access_token = :access_token ")
	int update(@Param("access_token") String access_token, @Param("dataAtualizacao") Date dataAtualizacao);

	@Modifying
	@Transactional
	@Query(value = "UPDATE AccessToken SET dataAtualizacao = :dataAtualizacao, valido = false WHERE codUsuario = :codUsuario AND codSistema = :codSistema ")
	int invalidaAccesTokenUsuarioSistema(@Param("codUsuario") Integer codUsuario, @Param("codSistema") Integer codSistema, @Param("dataAtualizacao") Date dataAtualizacao);
	
	
}