package br.com.quaemo.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "VW_MENU_USUARIO_SISTEMA", schema = Domain.SCHEMA)
public class MenuUsuarioSistema  extends Domain {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "COD_MENU")
	private Integer codigo;
	
	@JsonIgnore
	@Column(name = "COD_MENU_PAI")
	private Integer codMenuPai;
	
	@JsonIgnore
	@Column(name = "COD_SISTEMA")
	private Integer codSistema; 
	
	@JsonIgnore
	@Column(name = "COD_USUARIO")
	private Integer codUsuario; 

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Column(name = "NOM_MENU")
	private String menu; 

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Column(name = "NUM_ORDEM_MENU")
	private Integer ordem;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Column(name = "URL_PAGINA")
	private String url;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Column(name = "DES_ICONE")
	private String icone; 

	@JsonIgnore
	@Column(name = "IND_VISIVEL")
	private boolean visivel = true; 
	
	@Transient
	private List<MenuUsuarioSistema> subMenus = new ArrayList<MenuUsuarioSistema>();

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}

	public Integer getOrdem() {
		return ordem;
	}

	public void setOrdem(Integer ordem) {
		this.ordem = ordem;
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

	public List<MenuUsuarioSistema> getSubMenus() {
		return subMenus;
	}

	public void setSubMenus(List<MenuUsuarioSistema> subMenus) {
		this.subMenus = subMenus;
	}

	public boolean isVisivel() {
		return visivel;
	}

	public void setVisivel(boolean visivel) {
		this.visivel = visivel;
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

	public Integer getCodUsuario() {
		return codUsuario;
	}

	public void setCodUsuario(Integer codUsuario) {
		this.codUsuario = codUsuario;
	}

}
