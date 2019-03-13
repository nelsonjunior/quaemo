package br.com.quaemo.vo;

import com.fasterxml.jackson.annotation.JsonInclude; 

public class DetalhesErroVO {
	private String titulo;
	private int status;
	private String detalhe;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String urlAlteracaoSenha;

	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getDetalhe() {
		return this.detalhe;
	}

	public void setDetalhe(String detalhe) {
		this.detalhe = detalhe;
	}

	public String getUrlAlteracaoSenha() {
		return this.urlAlteracaoSenha;
	}

	public void setUrlAlteracaoSenha(String urlAlteracaoSenha) {
		this.urlAlteracaoSenha = urlAlteracaoSenha;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("{titulo:");
		builder.append(titulo);
		builder.append(", status:");
		builder.append(status);
		builder.append(", detalhe:");
		builder.append(detalhe);
		builder.append(", urlAlteracaoSenha:");
		builder.append(urlAlteracaoSenha);
		builder.append("}");
		return builder.toString();
	}
	
	
}
