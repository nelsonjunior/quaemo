package br.com.quaemo.autenticacao.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.quaemo.domain.AccesTokenAutenticado;

public interface AccessTokenAutenticadoDao extends JpaRepository<AccesTokenAutenticado, String> {
	
	@Query("select a from AccesTokenAutenticado a where a.access_token = :access_token and UPPER(a.token_type) = :token_type and UPPER(a.urlRecurso) = :urlRecurso and UPPER(a.metodo) = :metodo")
	AccesTokenAutenticado findAccessTokenAutenticado(@Param("access_token") String access_token, @Param("token_type") String token_type, @Param("urlRecurso") String urlRecurso, @Param("metodo") String metodo);
}
