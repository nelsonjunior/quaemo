package br.com.quaemo.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Immutable
@Table(name = "VW_PERMISSAO_RECURSO", schema = Domain.SCHEMA)
public class PermissaoRecurso  extends Domain {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "COD_PERMISSAO")
	private String codigo;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Column(name = "NOM_METODO")
	private String metodo;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "VW_PERMISSAO_RECURSO_PERFIL"
	         , joinColumns = {@JoinColumn(name = "COD_PERMISSAO") }
	         , inverseJoinColumns = {@JoinColumn(name = "COD_PERFIL") })
	private List<Perfil> listPerfis = new ArrayList<Perfil>();

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getMetodo() {
		return metodo;
	}

	public void setMetodo(String metodo) {
		this.metodo = metodo;
	}

	public List<Perfil> getListPerfis() {
		return listPerfis;
	}

	public void setListPerfis(List<Perfil> listPerfis) {
		this.listPerfis = listPerfis;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("{codigo:");
		builder.append(codigo);
		builder.append(", metodo:");
		builder.append(metodo);
		builder.append(", listPerfis:");
		builder.append(listPerfis);
		builder.append("}");
		return builder.toString();
	}

}
