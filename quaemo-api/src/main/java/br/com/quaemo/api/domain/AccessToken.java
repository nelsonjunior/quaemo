package br.com.quaemo.api.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List; 
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

public class AccessToken implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String access_token; 

	@JsonIgnore
	private String codAplicacaoCliente;

	@JsonIgnore
	private Integer codSistema; 

	@JsonIgnore
	private Integer codUsuario;  

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Integer expires_in; 

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String refresh_token; 

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String token_type;  

	@JsonIgnore
	private String userAgent;

	@JsonIgnore
	private String browser;

	@JsonIgnore
	private String versaoBrowser;

	@JsonIgnore
	private String sistemaOperacional;

	@JsonIgnore
	private String ip;

	@JsonIgnore
	private Date dataCadastro; 

	@JsonIgnore
	private Date dataAtualizacao;  

	@JsonIgnore
	private boolean valido = true;  
	

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private UsuarioLogado usuario;

	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private List<MenuUsuarioSistema> listMenus = new ArrayList<MenuUsuarioSistema>();
	 
  

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public String getCodAplicacaoCliente() {
		return codAplicacaoCliente;
	}

	public void setCodAplicacaoCliente(String codAplicacaoCliente) {
		this.codAplicacaoCliente = codAplicacaoCliente;
	}

	public Integer getCodSistema() {
		return codSistema;
	}

	public void setCodSistema(Integer codSistema) {
		this.codSistema = codSistema;
	}

	public Integer getCodUsuario() {
		return codUsuario;
	}

	public void setCodUsuario(Integer codUsuario) {
		this.codUsuario = codUsuario;
	}

	public Integer getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(Integer expires_in) {
		this.expires_in = expires_in;
	}

	public String getRefresh_token() {
		return refresh_token;
	}

	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	} 

	public String getToken_type() {
		return token_type;
	}

	public void setToken_type(String token_type) {
		this.token_type = token_type;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

	public String getVersaoBrowser() {
		return versaoBrowser;
	}

	public void setVersaoBrowser(String versaoBrowser) {
		this.versaoBrowser = versaoBrowser;
	}

	public String getSistemaOperacional() {
		return sistemaOperacional;
	}

	public void setSistemaOperacional(String sistemaOperacional) {
		this.sistemaOperacional = sistemaOperacional;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Date getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

	public boolean isValido() {
		return valido;
	}

	public void setValido(boolean valido) {
		this.valido = valido;
	}

	public UsuarioLogado getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioLogado usuario) {
		this.usuario = usuario;
	}
 
	public List<MenuUsuarioSistema> getListMenus() {
		return listMenus;
	}

	public void setListMenus(List<MenuUsuarioSistema> listMenus) {
		this.listMenus = listMenus;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("{access_token:");
		builder.append(access_token);
		builder.append(", codAplicacaoCliente:");
		builder.append(codAplicacaoCliente);
		builder.append(", codSistema:");
		builder.append(codSistema);
		builder.append(", codUsuario:");
		builder.append(codUsuario);
		builder.append(", expires_in:");
		builder.append(expires_in);
		builder.append(", refresh_token:");
		builder.append(refresh_token);
		builder.append(", token_type:");
		builder.append(token_type);
		builder.append(", userAgent:");
		builder.append(userAgent);
		builder.append(", browser:");
		builder.append(browser);
		builder.append(", versaoBrowser:");
		builder.append(versaoBrowser);
		builder.append(", sistemaOperacional:");
		builder.append(sistemaOperacional);
		builder.append(", ip:");
		builder.append(ip);
		builder.append(", dataCadastro:");
		builder.append(dataCadastro);
		builder.append(", dataAtualizacao:");
		builder.append(dataAtualizacao);
		builder.append(", valido:");
		builder.append(valido);
		builder.append(", usuario:");
		builder.append(usuario);
		builder.append("}");
		return builder.toString();
	}

	public int hashCode() {
		int result = 1;
		result = 31 * result + (this.access_token == null ? 0 : this.access_token.hashCode());
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
		AccessToken other = (AccessToken) obj;
		if (this.access_token == null) {
			if (other.access_token != null) {
				return false;
			}
		} else if (!this.access_token.equals(other.access_token)) {
			return false;
		}
		return true;
	}
}