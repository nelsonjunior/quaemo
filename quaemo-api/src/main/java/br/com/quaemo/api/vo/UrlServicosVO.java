package br.com.quaemo.api.vo;

public class UrlServicosVO {

	private String urlAutenticacao;
	
	private String urlSistema;
	private String urlSistemaAplicacaoCliente;
	private String urlSistemaPerfil;
	private String urlSistemaRecurso;
	private String urlSistemaUsuario;
	private String urlSistemaUsuarioMenus;
	private String urlSistemaUsuarioStatus;
	
	private String urlUsuario;
	private String urlUsuarioFoto;
	private String urlUsuarioLogin;
	private String urlUsuarioSenha;
	
    
    private String urlAutenticacaoSistema;


	public String getUrlSistemaAplicacaoCliente() {
		return urlSistemaAplicacaoCliente;
	}


	public void setUrlSistemaAplicacaoCliente(String urlSistemaAplicacaoCliente) {
		this.urlSistemaAplicacaoCliente = urlSistemaAplicacaoCliente;
	}


	public String getUrlAutenticacao() {
		return urlAutenticacao;
	}


	public void setUrlAutenticacao(String urlAutenticacao) {
		this.urlAutenticacao = urlAutenticacao;
	}


	public String getUrlSistema() {
		return urlSistema;
	}


	public void setUrlSistema(String urlSistema) {
		this.urlSistema = urlSistema;
	}


	public String getUrlSistemaPerfil() {
		return urlSistemaPerfil;
	}


	public void setUrlSistemaPerfil(String urlSistemaPerfil) {
		this.urlSistemaPerfil = urlSistemaPerfil;
	}


	public String getUrlSistemaRecurso() {
		return urlSistemaRecurso;
	}


	public void setUrlSistemaRecurso(String urlSistemaRecurso) {
		this.urlSistemaRecurso = urlSistemaRecurso;
	}


	public String getUrlSistemaUsuario() {
		return urlSistemaUsuario;
	}


	public void setUrlSistemaUsuario(String urlSistemaUsuario) {
		this.urlSistemaUsuario = urlSistemaUsuario;
	}


	public String getUrlSistemaUsuarioMenus() {
		return urlSistemaUsuarioMenus;
	}


	public void setUrlSistemaUsuarioMenus(String urlSistemaUsuarioMenus) {
		this.urlSistemaUsuarioMenus = urlSistemaUsuarioMenus;
	}


	public String getUrlUsuario() {
		return urlUsuario;
	}


	public void setUrlUsuario(String urlUsuario) {
		this.urlUsuario = urlUsuario;
	}


	public String getUrlUsuarioFoto() {
		return urlUsuarioFoto;
	}


	public void setUrlUsuarioFoto(String urlUsuarioFoto) {
		this.urlUsuarioFoto = urlUsuarioFoto;
	}


	public String getUrlUsuarioLogin() {
		return urlUsuarioLogin;
	}


	public void setUrlUsuarioLogin(String urlUsuarioLogin) {
		this.urlUsuarioLogin = urlUsuarioLogin;
	}


	public String getUrlUsuarioSenha() {
		return urlUsuarioSenha;
	}


	public void setUrlUsuarioSenha(String urlUsuarioSenha) {
		this.urlUsuarioSenha = urlUsuarioSenha;
	} 

	public String getUrlAutenticacaoSistema() {
		return urlAutenticacaoSistema;
	}


	public void setUrlAutenticacaoSistema(String urlAutenticacaoSistema) {
		this.urlAutenticacaoSistema = urlAutenticacaoSistema;
	}


	public String getUrlSistemaUsuarioStatus() {
		return urlSistemaUsuarioStatus;
	}


	public void setUrlSistemaUsuarioStatus(String urlSistemaUsuarioStatus) {
		this.urlSistemaUsuarioStatus = urlSistemaUsuarioStatus;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder("UrlServicosVO: ");
		builder.append("{urlAutenticacao:");
		builder.append(urlAutenticacao);
		builder.append(", urlSistema:");
		builder.append(urlSistema);
		builder.append(", urlSistemaAplicacaoCliente:");
		builder.append(urlSistemaAplicacaoCliente);
		builder.append(", urlSistemaPerfil:");
		builder.append(urlSistemaPerfil);
		builder.append(", urlSistemaRecurso:");
		builder.append(urlSistemaRecurso);
		builder.append(", urlSistemaUsuario:");
		builder.append(urlSistemaUsuario);
		builder.append(", urlSistemaUsuarioMenus:");
		builder.append(urlSistemaUsuarioMenus);
		builder.append(", urlSistemaUsuarioStatus:");
		builder.append(urlSistemaUsuarioStatus);
		builder.append(", urlUsuario:");
		builder.append(urlUsuario);
		builder.append(", urlUsuarioFoto:");
		builder.append(urlUsuarioFoto);
		builder.append(", urlUsuarioLogin:");
		builder.append(urlUsuarioLogin);
		builder.append(", urlUsuarioSenha:");
		builder.append(urlUsuarioSenha);
		builder.append(", urlAutenticacaoSistema:");
		builder.append(urlAutenticacaoSistema);
		builder.append("}");
		return builder.toString();
	}

}
