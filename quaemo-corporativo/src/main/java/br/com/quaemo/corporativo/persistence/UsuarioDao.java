package br.com.quaemo.corporativo.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import br.com.quaemo.domain.Usuario;

public interface UsuarioDao extends JpaRepository<Usuario, Integer>	{
	
	  Usuario findByLoginIgnoreCaseAndExcluido(String login, boolean excluido);
	  
	  Usuario findByCodigoAndExcluido(Integer codigo, boolean excluido);
	  
	  Page<Usuario> findByNomeContainingIgnoreCaseAndExcluido(String nome, boolean excluido, Pageable page);
	  
	  Page<Usuario> findByLoginContainingIgnoreCaseAndExcluido(String login, boolean excluido, Pageable page);
	  
	  Page<Usuario> findByNomeContainingIgnoreCaseAndLoginContainingIgnoreCaseAndExcluido(String nome, String login, boolean excluido, Pageable page);
	  
	  Page<Usuario> findByExcluido(boolean excluido, Pageable page);
	  
	  @Modifying
	  @Transactional
	  @Query(value = "UPDATE Usuario SET foto = ?2 WHERE codigo = ?1 ") 
	  int saveFotoUsuario(Integer codigo, String foto);
	}
