package br.com.quaemo.corporativo.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.quaemo.domain.AccesTokenAutenticado;

public interface AccessTokenAutenticadoDao extends JpaRepository<AccesTokenAutenticado, String> {
	
	@Query("select a from AccesTokenAutenticado a where a.access_token=?1 and UPPER(a.urlRecurso)=?2 and UPPER(a.metodo)=?3 ")
	AccesTokenAutenticado findAccessTokenAutenticado(String access_token, String urlRecurso, String metodo);
}
