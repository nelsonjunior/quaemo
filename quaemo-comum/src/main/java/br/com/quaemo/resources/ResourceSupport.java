package br.com.quaemo.resources;

import java.util.Date;

import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

import br.com.quaemo.support.DateUtils;

public abstract class ResourceSupport {

	public static final String JSON = "application/json";
	public static final String XML = "application/xml";
	public static final String URLENCODED = "application/x-www-form-urlencoded";

	protected ResponseEntity<Void> ok() {
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	protected ResponseEntity<Void> post() {
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	protected ResponseEntity<?> post(Object value) {
		return ResponseEntity.status(HttpStatus.CREATED).body(value);
	}

	protected ResponseEntity<?> ok(Object value) {
		return ResponseEntity.status(HttpStatus.OK).body(value);
	}

	protected ResponseEntity<?> ok(CacheControl cacheControl, Object value) {
		return ((ResponseEntity.BodyBuilder) ResponseEntity.status(HttpStatus.OK).cacheControl(cacheControl))
				.body(value);
	}

	protected ResponseEntity<?> notFound(Object value) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(value);
	}

	protected ResponseEntity<?> notFound(CacheControl cacheControl, Object value) {
		return ((ResponseEntity.BodyBuilder) ResponseEntity.status(HttpStatus.NOT_FOUND).cacheControl(cacheControl))
				.body(value);
	}

	protected String getString(WebRequest webRequest, String nome) {
		try {
			String p = webRequest.getParameter(nome);
			return (p == null) || (p.trim().length() == 0) ? "" : p;
		} catch (Exception e) {
		}
		return null;
	}

	protected Integer getInteger(WebRequest webRequest, String nome) {
		try {
			return Integer.valueOf(Integer.parseInt(getString(webRequest, nome)));
		} catch (Exception e) {
		}
		return null;
	}

	protected Long getLong(WebRequest webRequest, String nome) {
		try {
			return Long.valueOf(Long.parseLong(getString(webRequest, nome)));
		} catch (Exception e) {
		}
		return null;
	}

	protected Date getDate(WebRequest webRequest, String nome) {
		try {
			return DateUtils.convertStringDate(getString(webRequest, nome));
		} catch (Exception e) {
		}
		return null;
	}
}
