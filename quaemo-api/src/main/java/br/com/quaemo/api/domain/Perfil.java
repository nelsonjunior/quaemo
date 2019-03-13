package br.com.quaemo.api.domain;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Perfil implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer codigo; 

	@JsonIgnore
	private Integer codSistema;

	private String nome; 
	private String chave; 

	@JsonIgnore
	private boolean excluido = false;
	

	public Perfil() { 
	}
	
	public Perfil(Integer codigo) {
		super();
		this.codigo = codigo; 
	}
	
	public Perfil(Integer codigo, String nome, Integer codSistema) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.codSistema = codSistema;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Integer getCodSistema() {
		return codSistema;
	}

	public void setCodSistema(Integer codSistema) {
		this.codSistema = codSistema;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getChave() {
		return chave;
	}

	public void setChave(String chave) {
		this.chave = chave;
	} 

	public boolean isExcluido() {
		return excluido;
	}

	public void setExcluido(boolean excluido) {
		this.excluido = excluido;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("{codigo:");
		builder.append(codigo);
		builder.append(", codSistema:");
		builder.append(codSistema);
		builder.append(", nome:");
		builder.append(nome);
		builder.append(", chave:");
		builder.append(chave);
		builder.append(", excluido:");
		builder.append(excluido);
		builder.append("}");
		return builder.toString();
	}
 

}
