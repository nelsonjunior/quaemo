package br.com.quaemo.exceptions;

public class RefreshTokenInvalidoException extends RuntimeException {
	private static final long serialVersionUID = 1l;

	public RefreshTokenInvalidoException(String mensagem) {
		super(mensagem);
	}

	public RefreshTokenInvalidoException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}
