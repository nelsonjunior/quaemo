package br.com.quaemo.api.vo;


import java.util.ArrayList;
import java.util.List;

public class PageVO <T> implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private int totalPages;
	
	private int totalElements;
	
	private boolean last;
	
	private int number;
	
	private int size;
	
	private int numberOfElements;
	
	private boolean first;
	
	private List<T> content = new ArrayList<T>();	
		
	private List<OrdenacaoVO> sort = new ArrayList<OrdenacaoVO>();

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(int totalElements) {
		this.totalElements = totalElements;
	}

	public boolean isLast() {
		return last;
	}

	public void setLast(boolean last) {
		this.last = last;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getNumberOfElements() {
		return numberOfElements;
	}

	public void setNumberOfElements(int numberOfElements) {
		this.numberOfElements = numberOfElements;
	}

	public boolean isFirst() {
		return first;
	}

	public void setFirst(boolean first) {
		this.first = first;
	}

	public List<T> getContent() {
		return content;
	}

	public void setContent(List<T> content) {
		this.content = content;
	}

	public List<OrdenacaoVO> getSort() {
		return sort;
	}

	public void setSort(List<OrdenacaoVO> sort) {
		this.sort = sort;
	}
}
