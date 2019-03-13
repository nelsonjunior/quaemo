package br.com.quaemo.api.vo;
 

public class UsuarioVO {
 
	private Integer codigo;
	private String nome;
	private String login;
	private Integer codPerfil;
	private String nomePerfil;
	
	public UsuarioVO() {
		super();
	}

	public UsuarioVO(Integer codUsuario, String nomeUsuario, String login, Integer codPerfil, String nomePerfil) {
		super();
		this.codigo = codUsuario;
		this.nome = nomeUsuario;
		this.login = login;
		this.codPerfil = codPerfil;
		this.nomePerfil = nomePerfil;
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("{codigo:");
		builder.append(codigo);
		builder.append(", nome:");
		builder.append(nome);
		builder.append(", login:");
		builder.append(login);
		builder.append(", codPerfil:");
		builder.append(codPerfil);
		builder.append(", nomePerfil:");
		builder.append(nomePerfil);
		builder.append("}");
		return builder.toString();
	}
	
	
}
