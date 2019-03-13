package br.com.quaemo.exceptions;

public class TrocaSenhaException extends RuntimeException {
	private static final long serialVersionUID = 1l;

	public TrocaSenhaException(String mensagem) {
		super(mensagem);
	}

	public TrocaSenhaException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}
