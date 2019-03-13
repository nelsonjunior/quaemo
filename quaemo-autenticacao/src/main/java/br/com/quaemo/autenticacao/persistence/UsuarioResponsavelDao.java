package br.com.quaemo.autenticacao.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.quaemo.domain.UsuarioResponsavel;

public interface UsuarioResponsavelDao extends JpaRepository<UsuarioResponsavel, Integer> {
	 
}
