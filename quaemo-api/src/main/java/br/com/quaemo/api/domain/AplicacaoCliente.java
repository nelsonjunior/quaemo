package br.com.quaemo.api.domain;

import java.io.Serializable;
import java.util.Date; 
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

public class AplicacaoCliente implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String codigo; 

	@JsonIgnore
	private Integer codSistema; 

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String chave; 
	
	private String grantType;  

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", timezone = "GMT-3")
	private Date dataCadastro; 

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private int refreshToken = 15; 

	@JsonIgnore
	private boolean validaOrigemToken = true; 

	@JsonIgnore
	private boolean ativo = true;

	@JsonIgnore
	private boolean excluido = false;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Integer getCodSistema() {
		return codSistema;
	}

	public void setCodSistema(Integer codSistema) {
		this.codSistema = codSistema;
	}

	public String getChave() {
		return chave;
	}

	public void setChave(String chave) {
		this.chave = chave;
	} 

	public String getGrantType() {
		return grantType;
	}

	public void setGrantType(String grantType) {
		this.grantType = grantType;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public int getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(int refreshToken) {
		this.refreshToken = refreshToken;
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

	public boolean isValidaOrigemToken() {
		return validaOrigemToken;
	}

	public void setValidaOrigemToken(boolean validaOrigemToken) {
		this.validaOrigemToken = validaOrigemToken;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("{codigo:");
		builder.append(codigo);
		builder.append(", codSistema:");
		builder.append(codSistema);
		builder.append(", chave:");
		builder.append(chave);
		builder.append(", grantType:");
		builder.append(grantType);
		builder.append(", dataCadastro:");
		builder.append(dataCadastro);
		builder.append(", refreshToken:");
		builder.append(refreshToken);
		builder.append(", validaOrigemToken:");
		builder.append(validaOrigemToken);
		builder.append(", ativo:");
		builder.append(ativo);
		builder.append(", excluido:");
		builder.append(excluido);
		builder.append("}");
		return builder.toString();
	}
 
	
	
}
