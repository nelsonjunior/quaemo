package br.com.quaemo.api.exceptions;

public class AccessoNegadoException extends RuntimeException {
	private static final long serialVersionUID = 1l;

	public AccessoNegadoException(String mensagem) {
		super(mensagem);
	}

	public AccessoNegadoException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}
