package br.com.quaemo.autenticacao.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.quaemo.autenticacao.persistence.AccessTokenAutenticadoDao;
import br.com.quaemo.autenticacao.persistence.AccessTokenDao;
import br.com.quaemo.domain.AccesTokenAutenticado;
import br.com.quaemo.domain.AccessToken;
import br.com.quaemo.exceptions.AccesTokenException;
import br.com.quaemo.exceptions.AccessoNegadoException;
import br.com.quaemo.exceptions.DadoInconsistenteException;
import br.com.quaemo.support.Util;

@Service
public class AccessTokenAutenticadoService {
	
	@Autowired
	private AccessTokenAutenticadoDao accessTokenAutenticadoDao;
	
	@Autowired
	private AccessTokenDao accessTokenDao;

	public AccesTokenAutenticado findAccessTokenAutenticado(String accessToken, String tokenType, String urlRecurso, String metodo) {
		if (Util.isEmpty(urlRecurso)) {
			throw new DadoInconsistenteException("A URL do recurso não foi informada.");
		} 
		if (Util.isEmpty(accessToken)) {
			throw new DadoInconsistenteException("O accessToken não foi informado.");
		}
		if (Util.isEmpty(tokenType)) {
			throw new DadoInconsistenteException("O token type não foi informado.");
		}
		AccesTokenAutenticado tokenAutenticado = accessTokenAutenticadoDao.findAccessTokenAutenticado(accessToken, tokenType.toUpperCase(), urlRecurso.toUpperCase(), metodo.toUpperCase());
		if (tokenAutenticado == null) {
			throw new AccessoNegadoException("O token informado não possui acesso ao recurso solicitado.");
		}
		if (!tokenAutenticado.isValido()) {
			throw new AccesTokenException("O token informado não está mais valido.");
		}
		try {
			AccessToken a = accessTokenDao.findAccessTokenValido(accessToken);
			a.setDataAtualizacao(new Date());
			accessTokenDao.save(a);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tokenAutenticado;
	}
}
