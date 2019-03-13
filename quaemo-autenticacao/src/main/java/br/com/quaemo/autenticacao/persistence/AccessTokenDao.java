package br.com.quaemo.autenticacao.persistence;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import br.com.quaemo.domain.AccessToken;

public interface AccessTokenDao extends JpaRepository<AccessToken, String> {
	
	@Query("SELECT a FROM AccessToken a WHERE a.access_token=?1 ")
	AccessToken findAccessTokenValido(String access_token);

	@Query("SELECT a FROM AccessToken a WHERE a.refresh_token=?1 AND a.codAplicacaoCliente=?2 ")
	AccessToken findRefreshAccessTokenValido(String refresh_token, String codAplicacaoCliente);

	@Modifying
	@Transactional 
	@Query(value = "UPDATE AccessToken SET dataAtualizacao = :dataAtualizacao WHERE access_token = :access_token ")
	int update(@Param("access_token") String access_token, @Param("dataAtualizacao") Date dataAtualizacao);
}
