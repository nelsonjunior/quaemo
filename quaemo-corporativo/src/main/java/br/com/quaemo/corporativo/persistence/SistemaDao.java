package br.com.quaemo.corporativo.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.quaemo.domain.Sistema;

public interface SistemaDao extends JpaRepository<Sistema, Integer>{

}
