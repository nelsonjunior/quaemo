package br.com.quaemo.domain;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "TB_CONFIGURACAO", schema = Domain.SCHEMA)
public class Configuracao  extends Domain {
	private static final long serialVersionUID = 1L;

	@Id
	@JsonIgnore
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "COD_CONFIGURACAO")
	private Integer codigo;

	@JsonIgnore
	@Column(name = "COD_SISTEMA")
	private Integer codSistema;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Column(name = "NOM_CONFIGURACAO")
	private String nome;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Column(name = "DES_CONFIGURACAO")
	private String valor;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Column(name = "IND_TIPO_CONFIGURACAO")
	private String tipo;

	@JsonIgnore
	@Column(name = "IND_EXCLUIDO")
	private boolean excluido;

	public Configuracao() {
	}

	public Configuracao(ResultSet r) {
		try {
			this.codigo = r.getInt("COD_CONFIGURACAO");
			this.nome =   r.getString("NOM_CONFIGURACAO");
			this.valor =  r.getString("DES_CONFIGURACAO");
		} catch (SQLException e) {
			e.printStackTrace();
		}
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

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Integer getCodSistema() {
		return codSistema;
	}

	public void setCodSistema(Integer codSistema) {
		this.codSistema = codSistema;
	}

	public boolean isExcluido() {
		return excluido;
	}

	public void setExcluido(boolean excluido) {
		this.excluido = excluido;
	}

	public int hashCode() {
		int result = 1;
		result = 31 * result + (this.codigo == null ? 0 : this.codigo.hashCode());
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
		Configuracao other = (Configuracao) obj;
		if (this.codigo == null) {
			if (other.codigo != null) {
				return false;
			}
		} else if (!this.codigo.equals(other.codigo)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("{codigo:");
		builder.append(codigo);
		builder.append(", nome:");
		builder.append(nome);
		builder.append(", valor:");
		builder.append(valor);
		builder.append(", tipo:");
		builder.append(tipo);
		builder.append(", codSistema:");
		builder.append(codSistema);
		builder.append(", excluido:");
		builder.append(excluido);
		builder.append("}");
		return builder.toString();
	}
 
	
	
}
