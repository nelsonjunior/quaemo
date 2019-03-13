package br.com.quaemo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "TB_PERFIL", schema = Domain.SCHEMA)
public class Perfil  extends Domain {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "COD_PERFIL")
	private Integer codigo; 

	@JsonIgnore
	@Column(name = "COD_SISTEMA")
	private Integer codSistema;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Column(name = "NOM_PERFIL")
	private String nome; 

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Column(name = "DES_CHAVE_PERFIL")
	private String chave; 

	@JsonIgnore
	@Column(name = "IND_EXCLUIDO")
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
