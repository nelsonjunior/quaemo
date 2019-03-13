package br.com.quaemo.exceptions;

public class OperacaoNaoRealizadaException extends RuntimeException {
	private static final long serialVersionUID = 1l;

	public OperacaoNaoRealizadaException(String mensagem) {
		super(mensagem);
	}

	public OperacaoNaoRealizadaException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}
