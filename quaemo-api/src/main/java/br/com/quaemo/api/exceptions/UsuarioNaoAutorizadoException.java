package br.com.quaemo.api.exceptions;

public class UsuarioNaoAutorizadoException extends RuntimeException {
	private static final long serialVersionUID = 1l;

	public UsuarioNaoAutorizadoException(String mensagem) {
		super(mensagem);
	}

	public UsuarioNaoAutorizadoException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}
