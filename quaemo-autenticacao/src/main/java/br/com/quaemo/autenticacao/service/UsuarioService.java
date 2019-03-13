package br.com.quaemo.autenticacao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.quaemo.autenticacao.persistence.UsuarioDao;
import br.com.quaemo.domain.Usuario;
import br.com.quaemo.exceptions.DadoInconsistenteException;
import br.com.quaemo.exceptions.RecursoNaoEncontradoException;
import br.com.quaemo.support.CryptUtil;
import br.com.quaemo.support.PasswordUtil;
import br.com.quaemo.support.Util;

@Transactional
@Service
public class UsuarioService {
	
	@Autowired	
    private	UsuarioDao usuarioDao;

	public void updateSenhaUsuario(String login, String senha, String confirmaSenha) {
		
		if(Util.isEmpty(senha) || Util.isEmpty(confirmaSenha)) {
			throw new DadoInconsistenteException("A senha e a confirmação da senha são obrigatórios!");
		}
		if(!senha.equals(confirmaSenha)){
			throw new DadoInconsistenteException("A confirmação da senha não confere com a senha!");
		}	
	
		//Primeiro consultamos o usuario na base de dados
		Usuario usuario = usuarioDao.findByLoginIgnoreCase(login);
		if(usuario ==null){
			throw new RecursoNaoEncontradoException("Usuario não encontrado na base de dados!");
		}
		
		if (senha!=null && !PasswordUtil.checkPasswordComplexity(senha, usuario.getLogin(), usuario.getNome())){
			throw new DadoInconsistenteException("A senha deve possuir no máximo 8 caracteres e pelo menos uma letra maiúscula, uma letra minúscula e um número e/ou caracter especial!");
		}
		
		//Atualizamos a senha na base
		 usuarioDao.updateSenha(login, CryptUtil.hashEncrypt(senha));

	}
}
