package br.com.quaemo.corporativo.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import br.com.quaemo.domain.Configuracao;

public interface ConfiguracaoDao extends JpaRepository<Configuracao, Integer> {
	
	Configuracao findByNomeAndCodSistema(String nome, Integer codSistema);

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM Configuracao WHERE excluido = true AND codSistema = ?1")
	int deleteConfiguracaoBySistema(Integer codSistema);

	@Modifying
	@Transactional
	@Query(value = "UPDATE Configuracao SET excluido = false WHERE codSistema = ?1 ")
	int removeConfiguracaoBySistema(Integer codSistema);
}
