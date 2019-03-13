package br.com.quaemo.api.exceptions;

public class ClienteInvalidoException extends RuntimeException {
	private static final long serialVersionUID = 1l;

	public ClienteInvalidoException(String mensagem) {
		super(mensagem);
	}

	public ClienteInvalidoException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}
