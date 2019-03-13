package br.com.quaemo.support;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.context.request.WebRequest;

public class PaginacaoUtils {
	
	public static Pageable paginacao(WebRequest webRequest, boolean ascendente, String campoOrderBy) {
		int pagina = 0;
		if (getInteger(webRequest, "page") != null) {
			pagina = getInteger(webRequest, "page").intValue();
		}
		int quantidade = 10;
		if (getInteger(webRequest, "size") != null) {
			quantidade = getInteger(webRequest, "size").intValue();
		}
		return new PageRequest(pagina, quantidade, ascendente ? Sort.Direction.ASC : Sort.Direction.DESC, new String[] { campoOrderBy });
	}

	public static Pageable getPage(Integer page, Integer size, boolean ascendente, String orderBy) {
		if (page == null) {
			page = 0;
		}
		if (size == null) {
			size = 10;
		}
		if(orderBy == null) {
			return new PageRequest(page, size);
			
		}else {
			return new PageRequest(page, size, ascendente ? Sort.Direction.ASC : Sort.Direction.DESC, new String[] { orderBy });
			
		}
	}
	
	public static Pageable paginacaoQueryNativa(WebRequest webRequest) {
		int pagina = 0;
		if (getInteger(webRequest, "page") != null) {
			pagina = getInteger(webRequest, "page").intValue();
		}
		int quantidade = 10;
		if (getInteger(webRequest, "size") != null) {
			quantidade = getInteger(webRequest, "size").intValue();
		}
		Pageable pageable = new PageRequest(pagina, quantidade);
		return pageable;
	}

	private static Integer getInteger(WebRequest webRequest, String nome) {
		try {
			return Integer.valueOf(Integer.parseInt(getString(webRequest, nome)));
		} catch (Exception e) {
		}
		return null;
	}

	private static String getString(WebRequest webRequest, String nome) {
		try {
			String p = webRequest.getParameter(nome);
			return (p == null) || (p.trim().length() == 0) ? "" : p;
		} catch (Exception e) {
		}
		return null;
	}

	public static Pageable getPaginacao(WebRequest webRequest) {
		int pageNumber = getInteger(webRequest, "pageNumber") == null ? 0
				: getInteger(webRequest, "pageNumber").intValue();
		int pageSize = getInteger(webRequest, "pageSize") == null ? 10 : getInteger(webRequest, "pageSize").intValue();
		Pageable pageable = new PageRequest(pageNumber, pageSize);

		return pageable;
	}
}