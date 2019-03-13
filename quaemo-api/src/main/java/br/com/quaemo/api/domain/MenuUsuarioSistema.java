package br.com.quaemo.api.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MenuUsuarioSistema implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer codigo;

	private String menu; 
	private Integer ordem;
	private String url;
	private String icone; 
	
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

}
