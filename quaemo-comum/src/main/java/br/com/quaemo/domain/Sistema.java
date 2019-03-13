package br.com.quaemo.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "TB_SISTEMA", schema = Domain.SCHEMA)
public class Sistema  extends Domain {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "COD_SISTEMA")
	private Integer codigo;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Column(name = "NOM_SISTEMA")
	private String nome;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Column(name = "DES_SISTEMA")
	private String descricao;

	@JsonIgnore
	@Column(name = "IND_ATIVO")
	private boolean ativo = true;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "COD_SISTEMA")
	private List<Configuracao> listConfiguracoes = new ArrayList<Configuracao>();

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "COD_SISTEMA")
	private List<Perfil> listPerfis = new ArrayList<Perfil>();

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "COD_SISTEMA")
	private List<Recurso> listRecursos = new ArrayList<Recurso>();

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "COD_SISTEMA")
	private List<Menu> listMenus = new ArrayList<Menu>();

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	} 

	public List<Perfil> getListPerfis() {
		return listPerfis;
	}

	public void setListPerfis(List<Perfil> listPerfis) {
		this.listPerfis = listPerfis;
	}

	public List<Recurso> getListRecursos() {
		return listRecursos;
	}

	public void setListRecursos(List<Recurso> listRecursos) {
		this.listRecursos = listRecursos;
	}

	public List<Menu> getListMenus() {
		return listMenus;
	}

	public void setListMenus(List<Menu> listMenus) {
		this.listMenus = listMenus;
	}

	public List<Configuracao> getListConfiguracoes() {
		return listConfiguracoes;
	}

	public void setListConfiguracoes(List<Configuracao> listConfiguracoes) {
		this.listConfiguracoes = listConfiguracoes;
	}
 

}
