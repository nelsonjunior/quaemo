package br.com.quaemo.exceptions;

public class UsuarioInvalidoException extends RuntimeException {
	private static final long serialVersionUID = 1l;

	public UsuarioInvalidoException(String mensagem) {
		super(mensagem);
	}

	public UsuarioInvalidoException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}
