package br.com.quaemo.exceptions;

public class DadoInconsistenteException extends RuntimeException {
	private static final long serialVersionUID = 1l;

	public DadoInconsistenteException(String mensagem) {
		super(mensagem);
	}

	public DadoInconsistenteException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}
