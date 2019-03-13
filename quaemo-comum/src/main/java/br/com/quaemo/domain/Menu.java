package br.com.quaemo.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "TB_MENU", schema = Domain.SCHEMA)
public class Menu  extends Domain {
	private static final long serialVersionUID = 1L;
	
	@Id
	@JsonIgnore
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "COD_MENU")
	private Integer codigo; 

	@JsonIgnore 
	@Column(name = "COD_MENU_PAI")
	private Integer codMenuPai;

	@JsonIgnore
	@Column(name = "COD_SISTEMA")
	private Integer codSistema;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Column(name = "DES_CHAVE_MENU")
	private String chave;  

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Column(name = "NOM_MENU")
	private String nome;  

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Column(name = "URL_PAGINA")
	private String url;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Column(name = "DES_ICONE")
	private String icone;  

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Column(name = "NUM_ORDEM_MENU")
	private int ordem = 1;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Column(name = "IND_VISIVEL")
	private boolean visivel = true;

	@JsonIgnore
	@Column(name = "IND_EXCLUIDO")
	private boolean excluido = false;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "RL_PERFIL_MENU"
	         , joinColumns = {@JoinColumn(name = "COD_MENU") }
	         , inverseJoinColumns = {@JoinColumn(name = "COD_PERFIL") })
	private List<Perfil> listPerfis = new ArrayList<Perfil>();
	
	@Transient
	private List<Menu> listMenus = new ArrayList<Menu>();

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Integer getCodMenuPai() {
		return codMenuPai;
	}

	public void setCodMenuPai(Integer codMenuPai) {
		this.codMenuPai = codMenuPai;
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

	public String getIcone() {
		return icone;
	}

	public void setIcone(String icone) {
		this.icone = icone;
	}

	public int getOrdem() {
		return ordem;
	}

	public void setOrdem(int ordem) {
		this.ordem = ordem;
	}

	public boolean isVisivel() {
		return visivel;
	}

	public void setVisivel(boolean visivel) {
		this.visivel = visivel;
	}

	public boolean isExcluido() {
		return excluido;
	}

	public void setExcluido(boolean excluido) {
		this.excluido = excluido;
	}

	public List<Perfil> getListPerfis() {
		return listPerfis;
	}

	public void setListPerfis(List<Perfil> listPerfis) {
		this.listPerfis = listPerfis;
	}

	public List<Menu> getListMenus() {
		return listMenus;
	}

	public void setListMenus(List<Menu> listMenus) {
		this.listMenus = listMenus;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("{codigo:");
		builder.append(codigo);
		builder.append(", codMenuPai:");
		builder.append(codMenuPai);
		builder.append(", codSistema:");
		builder.append(codSistema);
		builder.append(", chave:");
		builder.append(chave);
		builder.append(", nome:");
		builder.append(nome);
		builder.append(", url:");
		builder.append(url);
		builder.append(", icone:");
		builder.append(icone);
		builder.append(", ordem:");
		builder.append(ordem);
		builder.append(", visivel:");
		builder.append(visivel);
		builder.append(", excluido:");
		builder.append(excluido);
		builder.append(", listPerfis:");
		builder.append(listPerfis);
		builder.append(", listMenus:");
		builder.append(listMenus);
		builder.append("}");
		return builder.toString();
	}
 

}
