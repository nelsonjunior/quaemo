package br.com.quaemo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "VW_ACCES_TOKEN_AUTENTICADO", schema = Domain.SCHEMA)
public class AccesTokenAutenticado  extends Domain {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "DES_ACCES_TOKEN")
	private String access_token; 

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Column(name = "DES_TIPO_TOKEN")
	private String token_type;  

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Column(name = "COD_RECURSO")
	private Integer codRecurso;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Column(name = "URL_RECURSO")
	private String urlRecurso;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Column(name = "NOM_METODO")
	private String metodo;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Column(name = "DES_IP")
	private String ip;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Column(name = "COD_USUARIO")
	private Integer codUsuario;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Column(name = "NOM_USUARIO")
	private String nomeUsuario;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Column(name = "NOM_LOGIN")
	private String login;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Column(name = "COD_PERFIL")
	private Integer codPerfil;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Column(name = "NOM_PERFIL")
	private String nomePerfil; 

	@Column(name = "COD_SISTEMA")
	private Integer codSistema; 

	@Column(name = "IND_VALIDA_ORIGEM_TOKEN")
	private boolean validaOrigemToken = true;  

	@Column(name = "IND_VALIDO")
	private boolean valido;
 
 

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public boolean isValidaOrigemToken() {
		return validaOrigemToken;
	}

	public void setValidaOrigemToken(boolean validaOrigemToken) {
		this.validaOrigemToken = validaOrigemToken;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public Integer getCodRecurso() {
		return codRecurso;
	}

	public void setCodRecurso(Integer codRecurso) {
		this.codRecurso = codRecurso;
	}

	public String getUrlRecurso() {
		return urlRecurso;
	}

	public void setUrlRecurso(String urlRecurso) {
		this.urlRecurso = urlRecurso;
	}

	public String getMetodo() {
		return metodo;
	}

	public void setMetodo(String metodo) {
		this.metodo = metodo;
	}

	public Integer getCodUsuario() {
		return codUsuario;
	}

	public void setCodUsuario(Integer codUsuario) {
		this.codUsuario = codUsuario;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public Integer getCodPerfil() {
		return codPerfil;
	}

	public void setCodPerfil(Integer codPerfil) {
		this.codPerfil = codPerfil;
	}

	public String getNomePerfil() {
		return nomePerfil;
	}

	public void setNomePerfil(String nomePerfil) {
		this.nomePerfil = nomePerfil;
	}

	public boolean isValido() {
		return valido;
	}

	public void setValido(boolean valido) {
		this.valido = valido;
	}
	
	

	public Integer getCodSistema() {
		return codSistema;
	}

	public void setCodSistema(Integer codSistema) {
		this.codSistema = codSistema;
	}

	public String getToken_type() {
		return token_type;
	}

	public void setToken_type(String token_type) {
		this.token_type = token_type;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("{access_token:");
		builder.append(access_token);
		builder.append(", token_type:");
		builder.append(token_type);
		builder.append(", codRecurso:");
		builder.append(codRecurso);
		builder.append(", urlRecurso:");
		builder.append(urlRecurso);
		builder.append(", metodo:");
		builder.append(metodo);
		builder.append(", ip:");
		builder.append(ip);
		builder.append(", codUsuario:");
		builder.append(codUsuario);
		builder.append(", nomeUsuario:");
		builder.append(nomeUsuario);
		builder.append(", login:");
		builder.append(login);
		builder.append(", codPerfil:");
		builder.append(codPerfil);
		builder.append(", nomePerfil:");
		builder.append(nomePerfil);
		builder.append(", codSistema:");
		builder.append(codSistema);
		builder.append(", validaOrigemToken:");
		builder.append(validaOrigemToken);
		builder.append(", valido:");
		builder.append(valido);
		builder.append("}");
		return builder.toString();
	}

	public int hashCode() {
		int result = 1;
		result = 31 * result + (this.access_token == null ? 0 : this.access_token.hashCode());
		result = 31 * result + (this.metodo == null ? 0 : this.metodo.hashCode());
		result = 31 * result + (this.urlRecurso == null ? 0 : this.urlRecurso.hashCode());
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
		AccesTokenAutenticado other = (AccesTokenAutenticado) obj;
		if (this.access_token == null) {
			if (other.access_token != null) {
				return false;
			}
		} else if (!this.access_token.equals(other.access_token)) {
			return false;
		}
		if (this.metodo == null) {
			if (other.metodo != null) {
				return false;
			}
		} else if (!this.metodo.equals(other.metodo)) {
			return false;
		}
		if (this.urlRecurso == null) {
			if (other.urlRecurso != null) {
				return false;
			}
		} else if (!this.urlRecurso.equals(other.urlRecurso)) {
			return false;
		}
		return true;
	}
}
