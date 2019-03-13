package br.com.quaemo.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "TB_USUARIO", schema = Domain.SCHEMA)
public class Usuario extends Domain {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "COD_USUARIO")
	private Integer codigo;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Column(name = "NOM_USUARIO")
	private String nome;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Column(name = "NOM_LOGIN")
	private String login;

	@JsonIgnore
	@Column(name = "DES_SENHA")
	private String senha;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Column(name = "NUM_CPF")
	private String cpf;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Column(name = "NUM_TELEFONE")
	private String telefone;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Column(name = "NUM_CELULAR")
	private String celular;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Column(name = "DES_EMAIL")
	private String email;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Column(name = "DES_FOTO")
	private String foto;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonFormat(pattern = "dd/MM/yyyy hh:mm", timezone="GMT-3")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DAT_CADASTRO")
	private Date dataCadastro;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@ManyToOne
	@JoinColumn(name = "COD_RESPONSAVEL_CADASTRO")
	private UsuarioResponsavel responsavelCadastro;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonFormat(pattern = "dd/MM/yyyy hh:mm", timezone="GMT-3")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DAT_ATUALIZACAO")
	private Date dataAtualizacao;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@ManyToOne
	@JoinColumn(name = "COD_RESPONSAVEL_ATUALIZACAO")
	private UsuarioResponsavel responsavelAtualizacao;

	@JsonIgnore
	@Column(name = "IND_ATIVO")
	private boolean ativo = true;

	@JsonIgnore
	@Column(name = "IND_EXCLUIDO")
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
