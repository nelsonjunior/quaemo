package br.com.quaemo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Immutable
@Table(name = "VW_USUARIO_RESPONSAVEL", schema = Domain.SCHEMA)
public class UsuarioResponsavel  extends Domain {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "COD_USUARIO")
	private Integer codigo;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Column(name = "NOM_USUARIO")
	private String nome;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Column(name = "NOM_LOGIN")
	private String login;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Column(name = "DES_EMAIL")
	private String email; 

	@JsonIgnore
	@Column(name = "IND_ATIVO")
	private boolean ativo;

	@JsonIgnore
	@Column(name = "IND_EXCLUIDO")
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