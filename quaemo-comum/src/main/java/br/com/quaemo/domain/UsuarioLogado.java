package br.com.quaemo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "VW_USUARIO_SISTEMA", schema = Domain.SCHEMA)
public class UsuarioLogado  extends Domain {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "COD_USUARIO")
	private Integer codigo;

	@Column(name = "NOM_USUARIO")
	private String nome;

	@Column(name = "NOM_LOGIN")
	private String login;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Column(name = "DES_EMAIL")
	private String email;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Column(name = "NUM_CPF")
	private String cpf;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Column(name = "NUM_TELEFONE")
	private String telefone;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Column(name = "NUM_CELULAR")
	private String celular;

	@JsonIgnore
	@Column(name = "IND_ATIVO")
	private boolean ativo;

	
	@Column(name = "COD_PERFIL")
	private Integer codPerfil;
	
	@Column(name = "NOM_PERFIL")
	private String nomePerfil;

	@JsonIgnore
	@Column(name = "COD_SISTEMA")
	private Integer codSistema;
	
	@Column(name = "NOM_SISTEMA")
	private String nomeSistema;

	@Transient 
	private String foto;
	
	public UsuarioLogado() {}
	
	public UsuarioLogado(Integer codigo, String nome, String login, String email, String cpf, String telefone,
			String celular, boolean ativo) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.login = login;
		this.email = email;
		this.cpf = cpf;
		this.telefone = telefone;
		this.celular = celular;
		this.ativo = ativo;
	}
	
	public UsuarioLogado(Usuario usuario) {
		super();
		this.codigo = usuario.getCodigo();
		this.nome = usuario.getNome();
		this.login = usuario.getLogin();
		this.email = usuario.getEmail();
		this.cpf = usuario.getCpf();
		this.telefone = usuario.getTelefone();
		this.celular = usuario.getCelular();
		this.ativo = usuario.isAtivo();
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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
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

	public Integer getCodSistema() {
		return codSistema;
	}

	public void setCodSistema(Integer codSistema) {
		this.codSistema = codSistema;
	}

	public String getNomeSistema() {
		return nomeSistema;
	}

	public void setNomeSistema(String nomeSistema) {
		this.nomeSistema = nomeSistema;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}
	 

}
