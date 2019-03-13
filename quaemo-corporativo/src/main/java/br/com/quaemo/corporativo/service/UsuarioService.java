package br.com.quaemo.corporativo.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.quaemo.corporativo.persistence.AccessTokenDao;
import br.com.quaemo.corporativo.persistence.PerfilDao;
import br.com.quaemo.corporativo.persistence.UsuarioDao;
import br.com.quaemo.corporativo.persistence.UsuarioSistemaDao;
import br.com.quaemo.domain.Perfil;
import br.com.quaemo.domain.Usuario;
import br.com.quaemo.domain.UsuarioResponsavel;
import br.com.quaemo.domain.UsuarioSistema;
import br.com.quaemo.exceptions.AccessoNegadoException;
import br.com.quaemo.exceptions.DadoInconsistenteException;
import br.com.quaemo.exceptions.OperacaoNaoRealizadaException;
import br.com.quaemo.exceptions.RecursoNaoEncontradoException;
import br.com.quaemo.support.CryptUtil;
import br.com.quaemo.support.EmailValidator;
import br.com.quaemo.support.Util;
import br.com.quaemo.vo.FotoVO;

@Transactional
@Service
public class UsuarioService {

	@Autowired
	private UsuarioDao usuarioDao;

	@Autowired
	private UsuarioSistemaDao usuarioSistemaDao;

	@Autowired
	private PerfilDao perfilDao;

	@Autowired
	private AccessTokenDao accessTokenDao; 

	public UsuarioSistema findByLoginAndSistema(String login, Integer codSistema) {
		UsuarioSistema usuario = null;
		if (AccessTokenAutenticadoService.validaSistema(codSistema)) {
			usuario = usuarioSistemaDao.findByLoginAndCodSistema(login, codSistema); 
			if (usuario == null) {
				throw new RecursoNaoEncontradoException("Usuario não cadastrado para esse sistema.");
			}
		}
		return usuario;
	}

	public UsuarioSistema findByCodigoAndSistema(Integer codigo, Integer codSistema) {
		UsuarioSistema usuario = null;
		if (AccessTokenAutenticadoService.validaSistema(codSistema)) {
			usuario = usuarioSistemaDao.findByCodigoAndCodSistema(codigo, codSistema);  
			if (usuario == null) {
				throw new RecursoNaoEncontradoException("Usuario não cadastrado para esse sistema.");
			}
		}
		usuario.setFoto(findByCodigo(codigo).getFoto());
		return usuario;
	}

	public List<UsuarioSistema> findUsuariosBySistema(String nome, String login, Integer codSistema, Integer codPerfil) {
		List<UsuarioSistema> lista = null;
		if (AccessTokenAutenticadoService.validaSistema(codSistema)) {
			login = Util.getStringSql(login) ;
			nome = Util.getStringSql(nome) ;
			codPerfil = Util.getIntegerSql(codPerfil) ;
			lista = usuarioSistemaDao.findByParametros(nome, login, codSistema, codPerfil);
			if (lista == null || lista.isEmpty()) {
				throw new RecursoNaoEncontradoException("Nenhum usuario foi localizado.");
			}
		}
		return lista;
	} 

	public List<UsuarioSistema> findUsuariosBySistema(Integer codSistema) {
		List<UsuarioSistema> lista = null;
		if (AccessTokenAutenticadoService.validaSistema(codSistema)) { 
			lista = usuarioSistemaDao.findByParametros("%", "%", codSistema, 0);
			if (lista == null || lista.isEmpty()) {
				throw new RecursoNaoEncontradoException("Nenhum usuario foi localizado.");
			}
		}
		return lista;
	}

	public Page<Usuario> findUsuarios(String nome, String login, Pageable paginacao) {
		Page<Usuario> lista = null;
		if (Util.isNotEmpty(nome) && Util.isNotEmpty(login)) {
			lista = usuarioDao.findByNomeContainingIgnoreCaseAndLoginContainingIgnoreCaseAndExcluido(nome, login, false, paginacao);
		} else if (Util.isNotEmpty(nome) && Util.isEmpty(login)) {
			lista = usuarioDao.findByNomeContainingIgnoreCaseAndExcluido(nome, false, paginacao);
		} else if (Util.isEmpty(nome)  && Util.isNotEmpty(login)) {
			lista = usuarioDao.findByLoginContainingIgnoreCaseAndExcluido(login, false, paginacao);
		} else {
			lista = usuarioDao.findByExcluido(false, paginacao);
		}
		if ((lista == null) || (lista.getTotalPages() == 0)) {
			throw new RecursoNaoEncontradoException("Nenhum usuario foi localizado.");
		}
		for(Usuario u:lista) {
			u.setFoto(null); 
		}
		return lista;
	}

	public Usuario findByLogin(String login) {
		Usuario usuario = usuarioDao.findByLoginIgnoreCaseAndExcluido(login, false);
		if (usuario == null) {
			throw new RecursoNaoEncontradoException("Usuario não foi localizado.");
		}
		return usuario;
	}

	public Usuario findByCodigo(Integer codigo) {
		Usuario usuario = usuarioDao.findByCodigoAndExcluido(codigo, false);
		if (usuario == null) {
			throw new RecursoNaoEncontradoException("Usuario não foi localizado.");
		}
		return usuario;
	}

	public void saveFotoUsuario(Integer codigo, FotoVO fotoVO) {
		if (!AccessTokenAutenticadoService.isUsuarioLogado(codigo)) {
			throw new AccessoNegadoException("Somente o próprio usuário pode alterar sua foto.");
		}
		findByCodigo(codigo);
		try {
			usuarioDao.saveFotoUsuario(codigo, fotoVO.getFoto());
		} catch (Exception e) {
			throw new OperacaoNaoRealizadaException(e.toString());
		}
	}
   

	public void removeUsuarioSistema(Integer codSistema, Integer codUsuario) {
		if (AccessTokenAutenticadoService.validaSistema(codSistema)) {
			usuarioSistemaDao.removeUsuarioPerfilBySistema(codUsuario, AccessTokenAutenticadoService.getCodSistema(), AccessTokenAutenticadoService.getCodUsuario(), new Date());
		}
	}

	public void atualizaUsuarioSistema(Integer codSistema, Integer codUsuario, UsuarioSistema usuarioSistema) {
		if (AccessTokenAutenticadoService.validaSistema(codSistema)) {
			if (Util.isEmpty(usuarioSistema.getCodigo()) 
					|| !codUsuario.equals(usuarioSistema.getCodigo())) {
				throw new DadoInconsistenteException("O codigo do usuário não é igual ao codigo do usuario sistema.");
			}
			UsuarioSistema usuarioSistemaCadastrado = usuarioSistemaDao.findByLoginAndCodSistema(usuarioSistema.getLogin(), codSistema);
			if (usuarioSistemaCadastrado == null) {
				throw new DadoInconsistenteException("Esse usuário não está cadastrado para esse sistema.");
			}
			Usuario usuario = usuarioDao.findByLoginIgnoreCaseAndExcluido(usuarioSistema.getLogin(), false);
			usuario = saveUsuario(usuario, usuarioSistema);
			usuarioSistemaDao.removeUsuarioPerfilBySistema(usuario.getCodigo(), AccessTokenAutenticadoService.getCodSistema(), AccessTokenAutenticadoService.getCodUsuario(), new Date());
			usuarioSistemaDao.saveUsuarioPerfil(usuario.getCodigo(), usuarioSistema.getPerfil().getCodigo(), AccessTokenAutenticadoService.getCodUsuario(), new Date(), false);
		}
	}

	public void inativarUsuarioSistema(Integer codSistema, Integer codUsuario, UsuarioSistema usuarioSistema) {
		if (usuarioSistema.getCodigo().equals(AccessTokenAutenticadoService.getCodUsuario())){
			throw new DadoInconsistenteException("Não é permitido inativar o usuário logado.");
		}
		try {
			usuarioSistemaDao.removeUsuarioPerfil(usuarioSistema.getCodigo(), usuarioSistema.getPerfil().getCodigo(), AccessTokenAutenticadoService.getCodUsuario(), new Date());
			accessTokenDao.invalidaAccesTokenUsuarioSistema(usuarioSistema.getCodigo(), codSistema, new Date());
		} catch (Exception e) {
			throw new OperacaoNaoRealizadaException("Ocorreu um erro ao inativar o usuário do sistema.");
		}
	}
	
	public void ativarUsuarioSistema(Integer codSistema, Integer codUsuario, UsuarioSistema usuarioSistema) {
		if (AccessTokenAutenticadoService.validaSistema(codSistema)) {

			if (Util.isEmpty(usuarioSistema.getCodigo())  
					|| !codUsuario.equals(usuarioSistema.getCodigo())) {
				throw new DadoInconsistenteException("O codigo do usuário não é igual ao codigo do usuario sistema.");
			}
			 
			UsuarioSistema usuarioSistemaCadastrado = usuarioSistemaDao.findByCodigoAndCodSistema(codUsuario, codSistema);
			if (usuarioSistemaCadastrado.isAtivo()) {
				throw new DadoInconsistenteException("O usuário informado já está ativo.");
			}
			try {
				usuarioSistemaDao.removeUsuarioPerfilBySistema(usuarioSistemaCadastrado.getCodigo(), codSistema, AccessTokenAutenticadoService.getCodUsuario(), new Date());
				usuarioSistemaDao.saveUsuarioPerfil(usuarioSistemaCadastrado.getCodigo(), usuarioSistemaCadastrado.getPerfil().getCodigo(), AccessTokenAutenticadoService.getCodUsuario(), new Date(), false);
			} catch (Exception e) {
				throw new OperacaoNaoRealizadaException("Ocorreu um erro ao reativar o usuário do sistema.");
			}
		}
	}

	public Integer saveUsuarioSistema(Integer codSistema, UsuarioSistema usuarioSistema) {
		if ((AccessTokenAutenticadoService.validaSistema(codSistema)) && (validaUsuario(usuarioSistema))) {
			UsuarioSistema usuarioSistemaCadastrado = usuarioSistemaDao.findByLoginAndCodSistema(usuarioSistema.getLogin(), codSistema);
			if (usuarioSistemaCadastrado != null) {
				throw new DadoInconsistenteException("Esse usuário já está cadastrado para esse sistema.");
			}
			Usuario usuario = usuarioDao.findByLoginIgnoreCaseAndExcluido(usuarioSistema.getLogin(), false);
			usuario = saveUsuario(usuario, usuarioSistema);
			usuarioSistemaDao.saveUsuarioPerfil(usuario.getCodigo(), usuarioSistema.getPerfil().getCodigo(), AccessTokenAutenticadoService.getCodUsuario(), new Date(), false);
			return usuario.getCodigo();
		}
		return null;
	}
 

	private Usuario saveUsuario(Usuario usuario, UsuarioSistema usuarioSistema) {
		if (usuario != null) {
			usuario = setUsuario(usuario, usuarioSistema);
			usuario.setResponsavelAtualizacao(new UsuarioResponsavel(AccessTokenAutenticadoService.getCodUsuario()));
			usuario.setDataAtualizacao(new Date());
			usuarioDao.save(usuario);
		} else {
			validateLoginExterno(usuarioSistema.getLogin());
			usuario = setUsuario(usuario, usuarioSistema);
			usuario.setResponsavelCadastro(new UsuarioResponsavel(AccessTokenAutenticadoService.getCodUsuario()));
			usuario.setDataCadastro(new Date());
			usuarioDao.save(usuario);
		}
		return usuario;
	}

	private static final String SENHA_PADRAO = "@PSW123456";
	
	private Usuario setUsuario(Usuario usuario, UsuarioSistema usuarioSistema) {
		if (usuario == null) {
			usuario = new Usuario();
			usuario.setSenha(CryptUtil.hashEncrypt(SENHA_PADRAO)); 
			usuario.setLogin(usuarioSistema.getLogin());
			usuario.setAtivo(true);
		}
		usuario.setNome(usuarioSistema.getNome());
		usuario.setEmail(usuarioSistema.getEmail());
		usuario.setCpf(usuarioSistema.getCpf());
		usuario.setTelefone(usuarioSistema.getTelefone());
		usuario.setCelular(usuarioSistema.getCelular());
		return usuario;
	}

	private boolean validaUsuario(UsuarioSistema usuarioSistema) {
		boolean valido = true;
		if (Util.isEmpty(usuarioSistema.getLogin()) 
				|| Util.isEmpty(usuarioSistema.getNome())
					|| Util.isEmpty(usuarioSistema.getEmail())) {
			valido = false;
			throw new DadoInconsistenteException("Os campos nome, login e email são obrigatórios.");
		} 
		if (!EmailValidator.validate(usuarioSistema.getEmail())) {
			throw new DadoInconsistenteException("O campo email deve ser um e-mail válido!.");
		}
		if (usuarioSistema.getPerfil() == null
				|| Util.isEmpty(usuarioSistema.getPerfil().getCodigo())) {
			throw new DadoInconsistenteException("O perfil é obrigatório.");
		}
		Perfil perfilUsuario = perfilDao.findByCodigoAndCodSistema(usuarioSistema.getPerfil().getCodigo(), AccessTokenAutenticadoService.getCodSistema());
		if (perfilUsuario == null) {
			throw new DadoInconsistenteException("O usuário não tem permissão para cadastros com o perfil selecionado.");
		}
		return valido;
	}

	private void validateLoginExterno(String login) {
		if (!EmailValidator.validate(login)) {
			throw new DadoInconsistenteException("O login de um usuário deve ser um e-mail válido!");
		}
	}
 
 

}
