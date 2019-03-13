package br.com.quaemo.autenticacao.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import br.com.quaemo.domain.Usuario;

public interface UsuarioDao extends JpaRepository<Usuario, Integer> {

	Usuario findByLoginIgnoreCase(String login);
	
	@Query(value = "SELECT o FROM Usuario o WHERE login = :login AND excluido = false")
	Usuario findByLogin(@Param("login") String login); 
		
	@Modifying
	@Transactional
	@Query(value ="UPDATE Usuario SET senha = :senha WHERE UPPER(login) = UPPER(:login)")
	int updateSenha(@Param("login") String login, @Param("senha") String senha);
}
