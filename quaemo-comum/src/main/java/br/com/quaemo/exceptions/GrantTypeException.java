package br.com.quaemo.exceptions;

public class GrantTypeException extends RuntimeException {
	private static final long serialVersionUID = 1l;

	public GrantTypeException(String mensagem) {
		super(mensagem);
	}

	public GrantTypeException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}
