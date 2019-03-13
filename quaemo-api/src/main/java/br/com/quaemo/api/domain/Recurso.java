package br.com.quaemo.api.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List; 
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

public class Recurso implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
	private Integer codigo; 

	@JsonIgnore
	private Integer codSistema;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String nome; 

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String url;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String chave;

	@JsonIgnore
	private Date dataCadastro; 

	@JsonIgnore
	private boolean excluido;

	private List<PermissaoRecurso> listPermissoes = new ArrayList<PermissaoRecurso>();

	public Recurso() {
		excluido = false;
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	} 

	public boolean isExcluido() {
		return excluido;
	}

	public void setExcluido(boolean excluido) {
		this.excluido = excluido;
	}

	public Integer getCodSistema() {
		return codSistema;
	}

	public void setCodSistema(Integer codSistema) {
		this.codSistema = codSistema;
	}

	public List<PermissaoRecurso> getListPermissoes() {
		return listPermissoes;
	}

	public void setListPermissoes(List<PermissaoRecurso> listPermissoes) {
		this.listPermissoes = listPermissoes;
	}

	public String getChave() {
		return chave;
	}

	public void setChave(String chave) {
		this.chave = chave;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("{codigo:");
		builder.append(codigo);
		builder.append(", codSistema:");
		builder.append(codSistema);
		builder.append(", nome:");
		builder.append(nome);
		builder.append(", url:");
		builder.append(url);
		builder.append(", chave:");
		builder.append(chave);
		builder.append(", dataCadastro:");
		builder.append(dataCadastro);
		builder.append(", excluido:");
		builder.append(excluido);
		builder.append(", listPermissoes:");
		builder.append(listPermissoes);
		builder.append("}");
		return builder.toString();
	}
 
	
}
