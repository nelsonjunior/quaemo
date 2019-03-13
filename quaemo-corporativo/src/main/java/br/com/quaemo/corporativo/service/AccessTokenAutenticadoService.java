package br.com.quaemo.corporativo.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.quaemo.corporativo.persistence.AccessTokenAutenticadoDao;
import br.com.quaemo.corporativo.persistence.AccessTokenDao;
import br.com.quaemo.domain.AccesTokenAutenticado;
import br.com.quaemo.exceptions.DadoInconsistenteException;

@Service
public class AccessTokenAutenticadoService { 
	
	@Autowired
	private AccessTokenAutenticadoDao accessTokenAutenticadoDao;
	
	@Autowired
	private AccessTokenDao accessTokenDao;

	
	private static AccesTokenAutenticado tokenAutenticado;
	
	public AccesTokenAutenticado findAccessTokenAutenticado(String accessToken, String urlRecurso, String metodo) {
		tokenAutenticado = this.accessTokenAutenticadoDao.findAccessTokenAutenticado(accessToken, urlRecurso.toUpperCase(), metodo.toUpperCase());
		return tokenAutenticado;
	}

	public void atualizaToken(String accessToken) {
		try {
			this.accessTokenDao.update(accessToken, new Date());
		} catch (Exception e) {
			System.out.println("erro ao atualizar o token");
			e.printStackTrace();
		}
	}

	public static AccesTokenAutenticado getTokenAutenticado() {
		return tokenAutenticado;
	}

	public static Integer getCodUsuario() {
		return getTokenAutenticado().getCodUsuario();
	}


	public static boolean isUsuarioLogado(Integer codigo) {
		return getCodUsuario().equals(codigo);
	}

	public static Integer getCodSistema() {
		return getTokenAutenticado().getCodSistema();
	}

	public static Integer getCodPerfilUsuario() {
		return getTokenAutenticado().getCodPerfil();
	}

	public static String getNomePerfilUsuario() {
		return getTokenAutenticado().getNomePerfil();
	} 

	public static String getAccess_token() {
		return getTokenAutenticado().getAccess_token();
	}   

	public static boolean validaSistema(Integer codSistema) {
		boolean valido = false;
		if (getCodSistema().equals(codSistema)) {
			valido = true;
		} else {
			throw new DadoInconsistenteException("O codigo do Sistema informado não corresponde ao token.");
		}
		return valido;
	}
}