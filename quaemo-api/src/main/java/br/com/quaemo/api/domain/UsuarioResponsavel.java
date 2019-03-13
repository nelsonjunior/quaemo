package br.com.quaemo.api.domain;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

public class UsuarioResponsavel implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer codigo;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String nome;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String login;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String email; 

	@JsonIgnore
	private boolean ativo;

	@JsonIgnore
	private boolean excluido;

	public UsuarioResponsavel() {
	}

	public UsuarioResponsavel(Integer codigo) {
		this.codigo = codigo;
	} 
	   
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

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
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
		builder.append(", nome:");
		builder.append(nome);
		builder.append(", login:");
		builder.append(login);
		builder.append(", email:");
		builder.append(email);
		builder.append(", ativo:");
		builder.append(ativo);
		builder.append(", excluido:");
		builder.append(excluido);
		builder.append("}");
		return builder.toString();
	}

	public int hashCode() {
		int result = 1;
		result = 31 * result + (this.codigo == null ? 0 : this.codigo.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		UsuarioResponsavel other = (UsuarioResponsavel) obj;
		if (this.codigo == null) {
			if (other.codigo != null) {
				return false;
			}
		} else if (!this.codigo.equals(other.codigo)) {
			return false;
		}
		return true;
	}
 
}