package br.com.quaemo.api.exceptions;

public class AccesTokenException extends RuntimeException {
	private static final long serialVersionUID = 1l;

	public AccesTokenException(String mensagem) {
		super(mensagem);
	}

	public AccesTokenException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}
