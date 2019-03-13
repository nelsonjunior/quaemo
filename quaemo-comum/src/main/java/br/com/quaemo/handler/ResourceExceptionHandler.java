package br.com.quaemo.handler;

import java.io.FileNotFoundException;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.quaemo.exceptions.AccesTokenException;
import br.com.quaemo.exceptions.AccessoNegadoException;
import br.com.quaemo.exceptions.ClienteInvalidoException;
import br.com.quaemo.exceptions.DadoInconsistenteException;
import br.com.quaemo.exceptions.GrantTypeException;
import br.com.quaemo.exceptions.MetodoException;
import br.com.quaemo.exceptions.OperacaoNaoRealizadaException;
import br.com.quaemo.exceptions.RecursoNaoEncontradoException;
import br.com.quaemo.exceptions.RefreshTokenInvalidoException;
import br.com.quaemo.exceptions.TrocaSenhaException;
import br.com.quaemo.exceptions.UsuarioInvalidoException;
import br.com.quaemo.exceptions.UsuarioNaoAutorizadoException;
import br.com.quaemo.vo.DetalhesErroVO;

@ControllerAdvice
public class ResourceExceptionHandler {
	@ExceptionHandler({ RecursoNaoEncontradoException.class })
	public ResponseEntity<DetalhesErroVO> handleLivroNaoEncontradoException(RecursoNaoEncontradoException e, HttpServletRequest request) {
		DetalhesErroVO erro = new DetalhesErroVO();
		erro.setStatus(404);
		erro.setTitulo("O recurso solicitado não foi localizado.");
		erro.setDetalhe(e.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}

	@ExceptionHandler({ FileNotFoundException.class })
	public ResponseEntity<DetalhesErroVO> FileNotFoundException(RecursoNaoEncontradoException e, HttpServletRequest request) {
		DetalhesErroVO erro = new DetalhesErroVO();
		erro.setStatus(404);
		erro.setTitulo("O recurso solicitado não foi localizado.");
		erro.setDetalhe("A URI solicitada não existe");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}

	@ExceptionHandler({ AccesTokenException.class })
	public ResponseEntity<DetalhesErroVO> handleAccesTokenException(AccesTokenException e, HttpServletRequest request) {
		DetalhesErroVO erro = new DetalhesErroVO();
		erro.setStatus(401);
		erro.setTitulo("invalid_token");
		erro.setDetalhe(e.getMessage());
		return ResponseEntity.status(401).body(erro);
	}

	@ExceptionHandler({ AccessoNegadoException.class })
	public ResponseEntity<DetalhesErroVO> handleAccessoNegadoException(AccessoNegadoException e, HttpServletRequest request) {
		DetalhesErroVO erro = new DetalhesErroVO();
		erro.setStatus(403);
		erro.setTitulo("acesso_nao_permitido");
		erro.setDetalhe(e.getMessage());
		return ResponseEntity.status(403).body(erro);
	}

	@ExceptionHandler({ OperacaoNaoRealizadaException.class })
	public ResponseEntity<DetalhesErroVO> handleOperacaoNaoRealizadaException(OperacaoNaoRealizadaException e, HttpServletRequest request) {
		DetalhesErroVO erro = new DetalhesErroVO();
		erro.setStatus(500);
		erro.setTitulo("Operação não realizada.");
		erro.setDetalhe(e.getMessage());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erro);
	}

	@ExceptionHandler({ DadoInconsistenteException.class })
	public ResponseEntity<DetalhesErroVO> handleDadoInconsistenteException(DadoInconsistenteException e, HttpServletRequest request) {
		DetalhesErroVO erro = new DetalhesErroVO();
		erro.setStatus(400);
		erro.setTitulo("Requisição inválida.");
		erro.setDetalhe(e.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}

	@ExceptionHandler({ ClienteInvalidoException.class })
	public ResponseEntity<DetalhesErroVO> handleClienteInvalidoException(ClienteInvalidoException e, HttpServletRequest request) {
		DetalhesErroVO erro = new DetalhesErroVO();
		erro.setStatus(401);
		erro.setTitulo("invalid_client");
		erro.setDetalhe(e.getMessage());
		return ResponseEntity.status(401).body(erro);
	}

	@ExceptionHandler({ GrantTypeException.class })
	public ResponseEntity<DetalhesErroVO> handleGrantTypeException(GrantTypeException e, HttpServletRequest request) {
		DetalhesErroVO erro = new DetalhesErroVO();
		erro.setStatus(400);
		erro.setTitulo("invalid_grant");
		erro.setDetalhe(e.getMessage());
		return ResponseEntity.status(400).body(erro);
	}

	@ExceptionHandler({ UsuarioInvalidoException.class })
	public ResponseEntity<DetalhesErroVO> handleUsuarioInvalidoException(UsuarioInvalidoException e, HttpServletRequest request) {
		DetalhesErroVO erro = new DetalhesErroVO();
		erro.setStatus(400);
		erro.setTitulo("invalid_grant");
		erro.setDetalhe(e.getMessage());
		return ResponseEntity.status(400).body(erro);
	}

	@ExceptionHandler({ UsuarioNaoAutorizadoException.class })
	public ResponseEntity<DetalhesErroVO> handleUsuarioNaoAutorizadoException(UsuarioNaoAutorizadoException e, HttpServletRequest request) {
		DetalhesErroVO erro = new DetalhesErroVO();
		erro.setStatus(401);
		erro.setTitulo("unauthorized_client");
		erro.setDetalhe(e.getMessage());
		return ResponseEntity.status(401).body(erro);
	}

	@ExceptionHandler({ RefreshTokenInvalidoException.class })
	public ResponseEntity<DetalhesErroVO> handleRefreshTokenInvalidoException(RefreshTokenInvalidoException e, HttpServletRequest request) {
		DetalhesErroVO erro = new DetalhesErroVO();
		erro.setStatus(401);
		erro.setTitulo("invalid_Refresh_token");
		erro.setDetalhe(e.getMessage());
		return ResponseEntity.status(401).body(erro);
	}

	@ExceptionHandler({ MetodoException.class })
	public static ResponseEntity<DetalhesErroVO> handleMetodoException(MetodoException e, HttpServletRequest request) {
		DetalhesErroVO erro = new DetalhesErroVO();
		erro.setStatus(405);
		erro.setTitulo("metodo_nao_permitido");
		erro.setDetalhe(e.getMessage());
		return ResponseEntity.status(405).body(erro);
	}

	@ExceptionHandler({ NamingException.class })
	public ResponseEntity<DetalhesErroVO> handleNamingException(NamingException e, HttpServletRequest request) {
		DetalhesErroVO erro = new DetalhesErroVO();
		erro.setStatus(500);
		erro.setTitulo("Operação não realizada.");
		erro.setDetalhe(e.getMessage() + e.getStackTrace().toString());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erro);
	}

	@ExceptionHandler({ TrocaSenhaException.class })
	public ResponseEntity<DetalhesErroVO> handleTrocaSenhaException(TrocaSenhaException e, HttpServletRequest request) {
		DetalhesErroVO erro = new DetalhesErroVO();
		erro.setStatus(302);
		erro.setTitulo("Alteração de Senha Obrigatória.");
		erro.setDetalhe("O usuário deve redefinir sua senha!");
		erro.setUrlAlteracaoSenha(e.getMessage());
		return ResponseEntity.status(HttpStatus.FOUND).body(erro);
	}
	
	@ExceptionHandler({ Exception.class })
	public ResponseEntity<DetalhesErroVO> handleException(Exception e, HttpServletRequest request) {
		DetalhesErroVO erro = new DetalhesErroVO();
		erro.setStatus(400);
		erro.setTitulo("Erro interno.");
		erro.setDetalhe(e.getMessage());
		return ResponseEntity.status(HttpStatus.FOUND).body(erro);
	}
}