package br.com.quaemo.autenticacao.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.quaemo.domain.AplicacaoCliente;

public interface AplicacaoClienteDao extends JpaRepository<AplicacaoCliente, String> {
	
	AplicacaoCliente findByCodigoAndChave(String codigo, String chave);
}
