package br.com.quaemo.api.domain;

import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer codigo;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String nome;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String login;

	@JsonIgnore
	private String senha;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String cpf;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String telefone;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String celular;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String email;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String foto;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonFormat(pattern = "dd/MM/yyyy hh:mm:ss")
	private Date dataCadastro;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private UsuarioResponsavel responsavelCadastro;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonFormat(pattern = "dd/MM/yyyy hh:mm:ss")
	private Date dataAtualizacao;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private UsuarioResponsavel responsavelAtualizacao;

	@JsonIgnore
	private boolean ativo = true;

	@JsonIgnore
	private boolean excluido = false;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public UsuarioResponsavel getResponsavelCadastro() {
		return responsavelCadastro;
	}

	public void setResponsavelCadastro(UsuarioResponsavel responsavelCadastro) {
		this.responsavelCadastro = responsavelCadastro;
	}

	public Date getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

	public UsuarioResponsavel getResponsavelAtualizacao() {
		return responsavelAtualizacao;
	}

	public void setResponsavelAtualizacao(UsuarioResponsavel responsavelAtualizacao) {
		this.responsavelAtualizacao = responsavelAtualizacao;
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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
 
	 
}
