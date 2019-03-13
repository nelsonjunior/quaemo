package br.com.quaemo.api.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PermissaoRecurso implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String codigo;
	private String metodo;
	
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
