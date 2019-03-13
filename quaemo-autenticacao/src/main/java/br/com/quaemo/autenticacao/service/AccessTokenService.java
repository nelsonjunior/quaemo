package br.com.quaemo.autenticacao.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.apache.oltu.oauth2.as.issuer.MD5Generator;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuer;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuerImpl;
import org.apache.oltu.oauth2.as.request.OAuthTokenRequest;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.message.types.GrantType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.quaemo.autenticacao.persistence.AccessTokenDao;
import br.com.quaemo.autenticacao.persistence.AplicacaoClienteDao;
import br.com.quaemo.autenticacao.persistence.MenuUsuarioSistemaDao;
import br.com.quaemo.autenticacao.persistence.UsuarioDao;
import br.com.quaemo.autenticacao.persistence.UsuarioLogadoDao;
import br.com.quaemo.domain.AccessToken;
import br.com.quaemo.domain.AplicacaoCliente;
import br.com.quaemo.domain.MenuUsuarioSistema;
import br.com.quaemo.domain.Usuario;
import br.com.quaemo.domain.UsuarioLogado;
import br.com.quaemo.exceptions.AccessoNegadoException;
import br.com.quaemo.exceptions.ClienteInvalidoException;
import br.com.quaemo.exceptions.DadoInconsistenteException;
import br.com.quaemo.exceptions.GrantTypeException;
import br.com.quaemo.exceptions.RefreshTokenInvalidoException;
import br.com.quaemo.exceptions.UsuarioInvalidoException;
import br.com.quaemo.support.CryptUtil;
import br.com.quaemo.support.Util;
import eu.bitwalker.useragentutils.UserAgent;

@Service
public class AccessTokenService {

	@Autowired
	private MenuUsuarioSistemaDao menuUsuarioSistemaDao;
	
	@Autowired
	private UsuarioLogadoDao usuarioLogadoDao;
	
	@Autowired
	private UsuarioDao usuarioDao;

	@Autowired
	private AccessTokenDao accessTokenDao;
	
	@Autowired
	private AplicacaoClienteDao aplicacaoClienteDao;

	private OAuthIssuer tokenGenerator = new OAuthIssuerImpl(new MD5Generator());

	private static final String AUTHORIZATION_CODE = "known_authz_code";
	private static final String GRAND_TYPE = "grant_type";
	private static final String CLIENT_ID = "client_id";
	private static final String CLIENT_SECRET = "client_secret";
	private static final String USER_AGENT = "user-agent";
	private static final String CODE = "code";
	
	private String idUserAgent;
	private String browser;
	private String versaoBrowser;
	private String sistemaOperacional;
	private String ip;

	public AccessToken getAccessToken(HttpServletRequest request) throws Exception {
		OAuthTokenRequest oauthRequest = null;
		AccessToken token = new AccessToken();
		try {
			oauthRequest = new OAuthTokenRequest(request);
			if (oauthRequest.getParam(GRAND_TYPE).equals(GrantType.PASSWORD.toString())
					&& (Util.isEmpty(oauthRequest.getUsername()) || Util.isEmpty(oauthRequest.getPassword()))) {
				throw new DadoInconsistenteException("Usuario e senha são obrigatorios.");
			}
			AplicacaoCliente appCliente = aplicacaoClienteDao.findByCodigoAndChave(oauthRequest.getParam(CLIENT_ID), oauthRequest.getParam(CLIENT_SECRET));
			// Verifica cliente valido
			if (appCliente == null) {
				throw new ClienteInvalidoException("cliente não localizado, "+CLIENT_ID+" ou "+CLIENT_SECRET+" estão inválidos.");
			}
			UserAgent userAgente = UserAgent.parseUserAgentString(request.getHeader(USER_AGENT));
			if (userAgente != null) {
				idUserAgent = ""+ userAgente.getId();
				browser = userAgente.getBrowser() != null ? userAgente.getBrowser().toString() : null;
				versaoBrowser = userAgente.getBrowserVersion() != null ? userAgente.getBrowserVersion().toString() : null;
				sistemaOperacional = userAgente.getOperatingSystem() != null ? userAgente.getOperatingSystem().toString() : null;
			}
			ip = request.getHeader("x-forwarded-for");
			if (ip == null) {
				ip = request.getHeader("X_FORWARDED_FOR");
				if (ip == null) {
					ip = request.getRemoteAddr();
				}
			}
			// Verificando diferentes grant types
			if (oauthRequest.getParam(GRAND_TYPE).equals(GrantType.AUTHORIZATION_CODE.toString())) {
				if (!AUTHORIZATION_CODE.equals(oauthRequest.getParam(CODE))) {
					throw new GrantTypeException("O "+GRAND_TYPE+" está inválido.");
				}
			} else if (!oauthRequest.getParam(GRAND_TYPE).equalsIgnoreCase(appCliente.getGrantType())) {
				if (oauthRequest.getParam(GRAND_TYPE).equals(GrantType.REFRESH_TOKEN.toString())) {
					AccessToken refreshToken = accessTokenDao.findRefreshAccessTokenValido(oauthRequest.getRefreshToken(), appCliente.getCodigo());
					if (refreshToken == null) {
						throw new RefreshTokenInvalidoException("O Refresh Token informado está inválido para o "+CLIENT_ID+" e "+CLIENT_SECRET+".");
					}
					token = geraAccessTokenRefreshToken(refreshToken, appCliente);
				} else {
					throw new GrantTypeException("O '"+GRAND_TYPE+"' está inválido.");
				}
			} else if (oauthRequest.getParam(GRAND_TYPE).equals(GrantType.PASSWORD.toString())) {
				token = geraAccessTokenPassword(oauthRequest.getUsername(), oauthRequest.getPassword(), appCliente);
				
			} else if (oauthRequest.getParam(GRAND_TYPE).equals(GrantType.CLIENT_CREDENTIALS.toString())) {
				token = geraAccessTokenClientCredentials(appCliente);
			}
			usuarioDao.findByLogin(oauthRequest.getUsername());
		} catch (OAuthProblemException e) {
			e.printStackTrace();
		}
		return token;
	}

	private AccessToken geraAccessTokenPassword(String login, String senha, AplicacaoCliente appCliente) throws Exception {
		UsuarioLogado usuarioAutenticado = null;
		if (Util.isNotEmpty(appCliente.getCodSistema())) { 
			usuarioAutenticado = usuarioLogadoDao.findByLoginAndSistema(login, appCliente.getCodSistema());
		}
		if ((usuarioAutenticado != null) && !usuarioAutenticado.isAtivo()) {
			throw new AccessoNegadoException("O usuario está inativo para esse sistema.");
		}
		if (usuarioAutenticado == null) {
			Usuario usuario = usuarioDao.findByLogin(login);
			if(usuario != null) {
				usuarioAutenticado = new UsuarioLogado(usuario);
			}
		}
		if ((usuarioAutenticado != null) && !usuarioAutenticado.isAtivo()) {
			throw new UsuarioInvalidoException("Usuario Inativo.");
		}
		if (!usuarioLogadoDao.autenticar(login, CryptUtil.hashEncrypt(senha))) { 
			throw new UsuarioInvalidoException("Usuário ou senha invalidos.");
		}
		AccessToken token = new AccessToken();

		token.setCodAplicacaoCliente(appCliente.getCodigo());
		token.setExpires_in(appCliente.getRefreshToken() * 60);

		token.setAccess_token(tokenGenerator.accessToken());
		token.setRefresh_token(tokenGenerator.refreshToken());
		token.setCodSistema(appCliente.getCodSistema());
        token.setToken_type(OAuth.OAUTH_HEADER_NAME.toString());

		if (usuarioAutenticado != null) {
			token.setUsuario(usuarioAutenticado);  
			token.setCodUsuario(usuarioAutenticado.getCodigo());
			token.setAccess_token(getAccessToken(usuarioAutenticado.getCodigo(), appCliente.getCodigo(), appCliente.getCodSistema()));
			token.setRefresh_token(getRefreshToken(usuarioAutenticado.getCodigo(), appCliente.getChave(), appCliente.getCodSistema()));
	        token.setListMenus(findMenus(appCliente.getCodSistema(), usuarioAutenticado.getCodigo()));
	        
	        Usuario usuario = usuarioDao.findOne(usuarioAutenticado.getCodigo());
	        token.getUsuario().setFoto(usuario.getFoto());
	        
		}
		token.setIp(ip);
		token.setBrowser(browser);
		token.setVersaoBrowser(versaoBrowser);
		token.setUserAgent(idUserAgent);
		token.setSistemaOperacional(sistemaOperacional);
		 
		accessTokenDao.save(token);
		
		return token;
	}

	private AccessToken geraAccessTokenClientCredentials(AplicacaoCliente appCliente) {
		AccessToken token = new AccessToken();
		
		token.setCodAplicacaoCliente(appCliente.getCodigo());
		token.setExpires_in(appCliente.getRefreshToken() * 60);
		token.setAccess_token(getAccessToken(0, appCliente.getCodigo(), 0));
		token.setRefresh_token(getRefreshToken(0, appCliente.getChave(), 0));
		token.setCodSistema(appCliente.getCodSistema());
        token.setToken_type(OAuth.OAUTH_HEADER_NAME.toString());
		token.setIp(ip);
		token.setBrowser(browser);
		token.setVersaoBrowser(versaoBrowser);
		token.setUserAgent(idUserAgent);
		token.setSistemaOperacional(sistemaOperacional);
		
		accessTokenDao.save(token);
		
		token.setCodAplicacaoCliente(null);
		token.setCodUsuario(null);
		token.setCodSistema(null);
		
		return token;
	}

	private AccessToken geraAccessTokenRefreshToken(AccessToken tokenAtual, AplicacaoCliente appCliente) {
		AccessToken token = new AccessToken();

		Integer codUsuario = token.getCodUsuario() == null ? 0 : token.getCodUsuario().intValue();
		Integer codSistema = token.getCodSistema() == null ? 0 : token.getCodSistema().intValue();
		
		token.setCodAplicacaoCliente(tokenAtual.getCodAplicacaoCliente());
		token.setCodUsuario(codUsuario);
		token.setCodSistema(codSistema);
		token.setExpires_in(tokenAtual.getExpires_in());
//	    token.setScope(tokenAtual.getScope()); TODO
		token.setToken_type(tokenAtual.getToken_type());
		token.setAccess_token(getAccessToken(codUsuario, appCliente.getCodigo(), codSistema));
		token.setRefresh_token(getRefreshToken(codUsuario, appCliente.getChave(), codSistema));
		token.setIp(ip);
		token.setBrowser(browser);
		token.setVersaoBrowser(versaoBrowser);
		token.setUserAgent(idUserAgent);
		token.setSistemaOperacional(sistemaOperacional);
		
		accessTokenDao.save(token);
		
		token.setCodAplicacaoCliente(null);
		token.setCodUsuario(null);
		token.setCodSistema(null); 
		
		return token;
	}

	private String getAccessToken(Integer codUsuario, String clienteID, Integer codSistema) {
		SimpleDateFormat hora = new SimpleDateFormat("hhmmss");
		SimpleDateFormat anoMesDia = new SimpleDateFormat("yyMMdd");
		String token = "token_ensi" + anoMesDia.format(new Date()) + codUsuario.toString() + codSistema.toString() + hora.format(new Date());
		return new String(Base64.encodeBase64(token.getBytes()));
	}

	private String getRefreshToken(Integer codUsuario, String clientSecret, Integer codSistema) {
		SimpleDateFormat hora = new SimpleDateFormat("hhmmss");
		SimpleDateFormat anoMesDia = new SimpleDateFormat("yyMMdd");
		String token = "refresh_ensi" + anoMesDia.format(new Date()) + codUsuario.toString() + codSistema.toString() + hora.format(new Date());
		return new String(Base64.encodeBase64(token.getBytes()));
	}
	
	private List<MenuUsuarioSistema> findMenus(Integer codSistema, Integer codUsuario) {
        try{
        	List<MenuUsuarioSistema> listMenus = menuUsuarioSistemaDao.findListaMenu(codSistema, codUsuario);
        	findSubMenu(listMenus, codSistema, codUsuario);
    		return listMenus;
        } catch (Exception e) {
	          return new ArrayList<MenuUsuarioSistema>();
	      }	 
	}

	private void findSubMenu(List<MenuUsuarioSistema> listMenus, Integer codSistema, Integer codUsuario) {
		for (MenuUsuarioSistema menu : listMenus) {
			menu.setSubMenus(menuUsuarioSistemaDao.findListaSubMenuByMenu(codSistema, codUsuario, menu.getCodigo()));
			findSubMenu(menu.getSubMenus(), codSistema, codUsuario);
		}
	}

	
}
