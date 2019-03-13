/*
 * Direitos Autorais 2018 Willian Ferreira Maruno
 * 
 * Este sistema e confidencial e de propriedade do desenvolvedor Willian Ferreira Maruno.
 * A divulgacao das informacoes contidas aqui somente serao aceitas
 * mediante acordo previo estabelecido para com o desenvolvedor.
 */
package br.com.quaemo.domain;

import java.io.Serializable;

/**
 * @Aplicativo 
 * @Modulo quaemo-comum
 * @Autor <a href="mailto:jackmaruno@gmail.com">Willian.Maruno</a>
 * @Data 13 de set de 2018 22:34:23
 * @Pacote br.com.quaemo.domain
 * @Nome Domain.java
 * @NomeCompleto br.com.quaemo.domain.Domain.java
 */
public abstract class Domain implements Serializable  {
	
	private static final long serialVersionUID = 1L;
	
	public static final String SCHEMA = "quaemoco_corporativo";

}
