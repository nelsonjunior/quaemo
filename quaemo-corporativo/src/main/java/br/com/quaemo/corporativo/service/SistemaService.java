package br.com.quaemo.corporativo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.quaemo.corporativo.persistence.AplicacaoClienteDao;
import br.com.quaemo.corporativo.persistence.ConfiguracaoDao;
import br.com.quaemo.corporativo.persistence.MenuDao;
import br.com.quaemo.corporativo.persistence.MenuUsuarioSistemaDao;
import br.com.quaemo.corporativo.persistence.PerfilDao;
import br.com.quaemo.corporativo.persistence.RecursoDao;
import br.com.quaemo.corporativo.persistence.SistemaDao;
import br.com.quaemo.domain.AplicacaoCliente;
import br.com.quaemo.domain.Configuracao;
import br.com.quaemo.domain.Menu;
import br.com.quaemo.domain.MenuUsuarioSistema;
import br.com.quaemo.domain.Perfil;
import br.com.quaemo.domain.PermissaoRecurso;
import br.com.quaemo.domain.Recurso;
import br.com.quaemo.domain.Sistema;
import br.com.quaemo.exceptions.DadoInconsistenteException;
import br.com.quaemo.exceptions.OperacaoNaoRealizadaException;
import br.com.quaemo.exceptions.RecursoNaoEncontradoException;
import br.com.quaemo.support.Util;

@Service
@Transactional
public class SistemaService {
	
	@Autowired
	private RecursoDao recursoDao;
	
	@Autowired
	private AplicacaoClienteDao aplicacaoClienteDao;
	
	@Autowired
	private MenuUsuarioSistemaDao menuUsuarioSistemaDao;
	
	@Autowired
	private PerfilDao perfilDao;
	
	@Autowired
	private SistemaDao sistemaDao;
	
	@Autowired
	private ConfiguracaoDao configuracaoDao;
	
	
	@Autowired
	private MenuDao menuDao;

	public Sistema findById(Integer codSistema) {
		Sistema sistema = (Sistema) sistemaDao.findOne(codSistema);
		if (sistema == null) {
			throw new RecursoNaoEncontradoException("O sistema informado não foi localizado!");
		}  
		return sistema;
	}

	public void saveSistema(Integer codSistema, Sistema sistema) {
		if (Util.isEmpty(codSistema)
				|| Util.isEmpty(sistema.getCodigo()) ) {
			throw new DadoInconsistenteException("O código do sistema é obrigatório");
		} 
		if (!codSistema.equals(sistema.getCodigo())) {
			throw new DadoInconsistenteException("O codigo do sistema informado no arquivo não corresponde ao sistema selecionado!");
		}
		Sistema sistemaBase = (Sistema) sistemaDao.findOne(codSistema);
		sistemaBase.setNome(sistema.getNome());
		sistemaBase.setDescricao(sistema.getDescricao());

		sistema.getListMenus();
		atualizaConfiguracao(codSistema, sistema.getListConfiguracoes());
		atualizaPerfil(codSistema, sistema.getListPerfis());
		atualizaRecursoSistema(codSistema, sistema.getListRecursos());
		atualizaMenuSistema(codSistema, sistema.getListMenus());
		try {
			sistemaDao.saveAndFlush(sistemaBase);
		} catch (Exception e) {
			throw new OperacaoNaoRealizadaException("Ocorreu um erro ao atualizar os dados do sistema!");
		}
	}

	private void atualizaMenuSistema(Integer codSistema, List<Menu> menus) {
		menuDao.removeMenuBySistema(codSistema);
		atualizaSubMenus(menus, null, codSistema);
	}

	private void atualizaSubMenus(List<Menu> menus, Menu menuSuperior, Integer codSistema) {
		for (Menu menu : menus) {
			Menu menuBase = menuDao.findByCodSistemaAndChave(codSistema, menu.getChave());
			if (menuBase != null) {
				menuBase.setNome(menu.getNome());
				menuBase.setIcone(menu.getIcone());
				menuBase.setOrdem(menu.getOrdem());
				menuBase.setUrl(menu.getUrl());
				menuBase.setVisivel(menu.isVisivel());
				menuBase.setCodSistema(codSistema);
				menuBase.setCodMenuPai(menuSuperior == null ? null : menuSuperior.getCodigo());
				menuBase.setExcluido(false);
				menuBase.setListPerfis(getPerfilMenu(codSistema, menu));
				menuDao.saveAndFlush(menuBase);
			} else {
				menu.setCodSistema(codSistema);
				menu.setCodMenuPai(menuSuperior == null ? null : menuSuperior.getCodigo());
				menu.setExcluido(false);
				menu.setListPerfis(getPerfilMenu(codSistema, menu));
				menuBase = (Menu) menuDao.saveAndFlush(menu);
			}
			menuBase.setListMenus(menu.getListMenus());
			atualizaSubMenus(menuBase.getListMenus(), menuBase, codSistema);
		}
	}

	private List<Perfil> getPerfilMenu(Integer codSistema, Menu menu) {
		List<Perfil> lista = new ArrayList<Perfil>();
		for (Perfil perfil : menu.getListPerfis()) {
			Perfil perfilAcesso = perfilDao.findByChaveAndCodSistema(perfil.getChave(), codSistema);
			if (perfilAcesso != null) {
				lista.add(perfilAcesso);
			}
		}
		return lista;
	}

	private void atualizaRecursoSistema(Integer codSistema, List<Recurso> recursos) {
		recursoDao.removeRecursosBySistema(codSistema);
		for (Recurso recurso : recursos) {
			Recurso recursoBase = recursoDao.findByCodSistemaAndChave(codSistema, recurso.getChave());
			if (recursoBase != null) {
				recursoBase.setNome(recurso.getNome());
				recursoBase.setUrl(recurso.getUrl());
				recursoBase.setExcluido(false);
				recursoDao.saveAndFlush(recursoBase);
				recursoBase.getListPermissoes().addAll(recurso.getListPermissoes());
				atualizaPermissoes(codSistema, recursoBase);
			} else {
				recurso.setCodSistema(codSistema);
				recurso.setExcluido(false);
				recursoBase = (Recurso) recursoDao.saveAndFlush(recurso);
				atualizaPermissoes(codSistema, recursoBase);
			}
		}
		try {
			recursoDao.deleteRecursoAplicacaoBySistema(codSistema);
			recursoDao.deleteRecursoBySistema(codSistema);
		} catch (Exception e) {
			throw new OperacaoNaoRealizadaException("Ocorreu um erro ao remover os recursos do sistema!");
		}
	}

	private void atualizaPermissoes(Integer codSistema, Recurso recurso) {
		for (PermissaoRecurso permissao: recurso.getListPermissoes()) {
			if ((permissao.getMetodo().equalsIgnoreCase("GET")) || (permissao.getMetodo().equalsIgnoreCase("POST"))
					|| (permissao.getMetodo().equalsIgnoreCase("PUT"))
						|| (permissao.getMetodo().equalsIgnoreCase("DELETE"))) {
				
				for (Perfil perfil : permissao.getListPerfis()) {
					Perfil perfilBase = perfilDao.findByChaveAndCodSistema(perfil.getChave(), codSistema);
					if (perfilBase != null) {
						recursoDao.saveRecursoPerfil(perfilBase.getCodigo(), recurso.getCodigo(), permissao.getMetodo());
					}
				}
			}
		}
	}

	private void atualizaPerfil(Integer codSistema, List<Perfil> lista) {
		perfilDao.removePerfisBySistema(codSistema);
		for (Perfil perfil : lista) {
			Perfil perfilBase = perfilDao.findByChaveAndCodSistema(perfil.getChave(), codSistema);
			if (perfilBase != null) {
				perfilBase.setNome(perfil.getNome());
				perfilBase.setExcluido(false);
				perfilDao.saveAndFlush(perfilBase);
			} else {
				perfil.setCodSistema(codSistema);
				perfil.setExcluido(false);
				perfilDao.saveAndFlush(perfil);
			}
		}
		try {
			perfilDao.deletePerfilUsuarioBySistema(codSistema);
			recursoDao.deleteRecursoPerfilBySistema(codSistema);
			menuDao.deleteMenuBySistema(codSistema);
			perfilDao.deletePerfisBySistema(codSistema);
		} catch (Exception e) {
			throw new OperacaoNaoRealizadaException("Ocorreu um erro ao remover o perfil acesso!");
		}
	}

	private void atualizaConfiguracao(Integer codSistema, List<Configuracao> configuracoes) {
		configuracaoDao.removeConfiguracaoBySistema(codSistema);
		for (Configuracao configuracao : configuracoes) {
			Configuracao configuracaoBase = configuracaoDao.findByNomeAndCodSistema(configuracao.getNome(), codSistema);
			if (configuracaoBase != null) {
				configuracaoBase.setValor(configuracao.getValor());
				configuracaoBase.setTipo(configuracao.getTipo());
				configuracaoBase.setExcluido(false);
				configuracaoDao.saveAndFlush(configuracaoBase);
			} else {
				configuracao.setCodSistema(codSistema);
				configuracao.setExcluido(false);
				configuracaoDao.saveAndFlush(configuracao);
			}
		}
		try {
			configuracaoDao.removeConfiguracaoBySistema(codSistema);
		} catch (Exception e) {
			throw new OperacaoNaoRealizadaException("Ocorreu um erro ao salvar as Configurações do sistema!");
		}
	}

	public List<Recurso> findRecursosBySistema(Integer codSistema) {
		List<Recurso> lista = recursoDao.findByCodSistemaAndExcluido(codSistema, false);
		if (Util.isEmpty(lista)) {
			throw new RecursoNaoEncontradoException("Não foram localizados recursos para o sistema informado.");
		}
		return lista;
	}

	public AplicacaoCliente findAplicacaoClienteBySistema(Integer codSistema) {
		AplicacaoCliente cliente = aplicacaoClienteDao.findByCodSistema(codSistema);
		if (cliente == null) {
			throw new RecursoNaoEncontradoException("Aplicação cliente API não foi localizada para o sistema informado.");
		}
		return cliente;
	}

	public List<Perfil> findAllPerfilBySistema(Integer codSistema) {
		List<Perfil> lista = null;
		if (AccessTokenAutenticadoService.validaSistema(codSistema)) {
			lista = perfilDao.findByCodSistema(codSistema);
			if (Util.isEmpty(lista)) {
				throw new RecursoNaoEncontradoException("Nenhum perfil foi localizado.");
			}
		}
		return lista;
	}

	public Perfil findPerfilBySistema(Integer codPerfil, Integer codSistema) {
		Perfil perfil = null;
		if (AccessTokenAutenticadoService.validaSistema(codSistema)) {
			perfil = perfilDao.findByCodigoAndCodSistema(codPerfil, codSistema);
			if (perfil == null) {
				throw new RecursoNaoEncontradoException("Nenhum perfil foi localizado.");
			}
		}
		return perfil;
	}

	public List<MenuUsuarioSistema> findMenusBySistema(Integer codSistema) {
		List<MenuUsuarioSistema> lista = new ArrayList<MenuUsuarioSistema>();
		if (AccessTokenAutenticadoService.validaSistema(codSistema)) {
			lista = menuUsuarioSistemaDao.findMenus(codSistema, AccessTokenAutenticadoService.getCodUsuario());
			if (Util.isEmpty(lista)) {
				throw new RecursoNaoEncontradoException("Nenhum menu foi localizado.");
			}
			try {
				adicionaSubMenu(lista, null, codSistema, AccessTokenAutenticadoService.getCodUsuario());
			} catch (Exception e) {
				throw new OperacaoNaoRealizadaException("Não foi possível carregar a lista de menus.");
			}
		}
		return lista;
	}

	private void adicionaSubMenu(List<MenuUsuarioSistema> lista, MenuUsuarioSistema menuSuperior, Integer codSistema, Integer codUsuario) {
		for (MenuUsuarioSistema menu : lista) {
			menu.setSubMenus(menuUsuarioSistemaDao.findMenusByMenuPai(codSistema, codUsuario, menu.getCodigo()));
			adicionaSubMenu(menu.getSubMenus(), menu, codSistema, codUsuario);
		}
	}
}