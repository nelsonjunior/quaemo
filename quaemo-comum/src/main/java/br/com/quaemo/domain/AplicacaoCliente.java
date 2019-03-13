package br.com.quaemo.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "TB_APLICACAO_CLIENTE", schema = Domain.SCHEMA)
public class AplicacaoCliente  extends Domain {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "COD_APLICACAO_CLIENTE")
	private String codigo; 

	@JsonIgnore
	@Column(name = "COD_SISTEMA")
	private Integer codSistema; 

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Column(name = "DES_CLIENT_SECRET")
	private String chave; 
	
	//client_credentials || password  
	@Column(name = "DES_GRANT_TYPE")
	private String grantType;  

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", timezone = "GMT-3")
	@Column(name = "DAT_CADASTRO")
	private Date dataCadastro; 

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Column(name = "NUM_REFRESH_TOKEN")
	private int refreshToken = 15; 

	@JsonIgnore
	@Column(name = "IND_VALIDA_ORIGEM_TOKEN")
	private boolean validaOrigemToken = true; 

	@JsonIgnore
	@Column(name = "IND_ATIVO")
	private boolean ativo = true;

	@JsonIgnore
	@Column(name = "IND_EXCLUIDO")
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
