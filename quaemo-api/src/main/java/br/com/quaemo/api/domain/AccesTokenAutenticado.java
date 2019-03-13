package br.com.quaemo.api.domain;
 
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

public class AccesTokenAutenticado implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String access_token; 

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String token_type;  

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Integer codRecurso;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String urlRecurso;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String metodo;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String ip;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Integer codUsuario;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String nomeUsuario;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String login;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Integer codPerfil;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String nomePerfil; 

	@JsonIgnore
	private Integer codSistema; 

	@JsonIgnore
	private boolean validaOrigemToken = true;  

	@JsonIgnore
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((access_token == null) ? 0 : access_token.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AccesTokenAutenticado other = (AccesTokenAutenticado) obj;
		if (access_token == null) {
			if (other.access_token != null)
				return false;
		} else if (!access_token.equals(other.access_token))
			return false;
		return true;
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
 
	
	
}
