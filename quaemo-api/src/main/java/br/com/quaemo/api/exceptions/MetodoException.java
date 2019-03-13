package br.com.quaemo.api.exceptions;

public class MetodoException extends RuntimeException {
	private static final long serialVersionUID = 1l;

	public MetodoException(String mensagem) {
		super(mensagem);
	}

	public MetodoException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}
