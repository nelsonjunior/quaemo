package br.com.quaemo.autenticacao.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.quaemo.domain.UsuarioLogado;

public interface UsuarioLogadoDao extends JpaRepository<UsuarioLogado, Integer> {
	
	@Query(value = "SELECT o FROM UsuarioLogado o WHERE login = :login AND codSistema = :codSistema")
	UsuarioLogado findByLoginAndSistema(@Param("login") String login, @Param("codSistema") Integer codSistema); 

	@Query(value="SELECT CASE WHEN COUNT(codigo) > 0 THEN true ELSE false END"
			   + "\n FROM Usuario o "
			   + "\n WHERE excluido = false "
			   + "\n AND login = :login"
			   + "\n AND senha =:senha")	
	Boolean autenticar(@Param("login") String login, @Param("senha") String senha); 
}
