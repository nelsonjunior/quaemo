package br.com.quaemo.exceptions;

public class RecursoNaoEncontradoException extends RuntimeException {
	private static final long serialVersionUID = 1l;

	public RecursoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}

	public RecursoNaoEncontradoException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}
