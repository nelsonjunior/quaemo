package br.com.quaemo.api.vo;

public class OrdenacaoVO implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String direction;
	
	private String property;
	
	private boolean ignoreCase;
	
	private String nullHandling;
	
	private boolean ascending;

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public boolean isIgnoreCase() {
		return ignoreCase;
	}

	public void setIgnoreCase(boolean ignoreCase) {
		this.ignoreCase = ignoreCase;
	}

	public String getNullHandling() {
		return nullHandling;
	}

	public void setNullHandling(String nullHandling) {
		this.nullHandling = nullHandling;
	}

	public boolean isAscending() {
		return ascending;
	}

	public void setAscending(boolean ascending) {
		this.ascending = ascending;
	}
	
	
}
