package br.com.quaemo.support;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.springframework.stereotype.Component;

@Component("emailSupport")
public class EmailSupport {
	
	private static final String TEMPLATE_NEW_USER = "<html><head></head><body><h3>Bem vindo!</h3><div> Prezado(a) <b>${nome}</b>, seu cadastro no sistema <b>${sistema}</b> foi realizado com sucesso.<br/> Você já pode acessar o sistema utilizando o login <b>${login}</b>.</div></body></html>";
	private static final String TEMPLATE_NEW_USER_PASSWORD = "<html><head></head><body><h3>Bem vindo!</h3><div> Prezado(a) <b>${nome}</b>, seu cadastro no sistema <b>${sistema}</b> foi realizado com sucesso.<br/> O login é o seu email e sua senha temporária é: <b>${senha}</b></div></body></html>";
	private static final String TEMPLATE_GENERATE_PASSWORD = "<html> <head> </head> <body> <h3>Senha temporária!</h3><div> Prezado(a) <b>${nome}</b>, sua senha foi redefina  com sucesso. <br/><br/> Segue a lista de sistemas que utilizarão a nova senha: <br/> ${sistemas} <br/><br/> Sua senha temporária é: <b>${senha}</b> </div> </body></html>";
	private static final String TEMPLATE_EXISTENT_USER = "<html> <head> </head> <body> <h3>Bem vindo!</h3><div> Prezado(a) <b>${nome}</b>, seu cadastro no sistema <b>${sistema}</b> foi realizado com sucesso.<br/> Você já pode acessar o sistema utilizando a mesma senha cadastrada para o login <b>${login}</b> no Sistema Indústria.</div> </body></html>";
	private static final String TEMPLATE_CHANGE_PASSWORD = "<html><head></head><body><h3>Senha Alterada!</h3><div> A senha foi alterada conforme sua solicitação!</div></body></html>";
	private static final String TEMPLATE_ERRO_AD = "<html> <head> </head> <body> <h3>Bem vindo!</h3><div> Prezado(a) <b>${nome}</b>, seu cadastro no sistema <b>${sistema}</b> foi realizado com sucesso.<br/>  Porem ocorreu um erro ao tentar incluir seu login na base do Active Directory do Sistema Indústria, informe o ocorrido ao administrador do sistema.</div> </body></html>";

	public void sendEmailExistentUser(String login, String email, String nome, String sistema) throws MessagingException {
		enviar(email, getTextEmailExistentUser(login, nome, sistema), "Confirmação de cadastro", null);
	}

	public void sendEmailErroCadastroAD(String login, String email, String nome, String sistema) throws MessagingException {
		enviar(email, getTextEmailtemplateErroCadastroAD(login, nome, sistema), "Confirmação de cadastro", null);
	}

	public void sendEmailNewUserWithPassword(String login, String email, String senha, String sistema, String nome) throws MessagingException {
		enviar(email, getTextEmailNewUserWithPassword(login, senha, sistema, nome), "Senha para acesso ao sistema", null);
	}

	public void sendEmailGeneratedPassword(String login, String email, String nome, String senha, String sistemas) throws MessagingException {
		enviar(email, getTextEmailGeneratedPassword(login, nome, senha, sistemas), "Senha Temporaria", null);
	}

	public void sendEmailNewUser(String login, String email, String nome, String sistema) throws MessagingException {
		enviar(email, getTextEmailNewUser(login, nome, sistema), "Confirmação de cadastro", null);
	}

	public void sendEmailUpdatePassword(String email) throws MessagingException {
		enviar(email, getTextEmailUpdatePassword(email), "Alteração de Senha", null);
	}

	public void sendEmail(String destinatario, String mensagem, String titulo, String sistema) throws MessagingException {
		enviar(destinatario, mensagem, titulo, sistema);
	}

	public String getTextEmailExistentUser(String login, String nome, String sistema) {
		Map<String, String> model = new HashMap<String, String>();
		model.put("login", login);
		model.put("nome", nome);
		model.put("sistema", sistema);
		return montaMensagem(TEMPLATE_EXISTENT_USER, model);
	}

	public String getTextEmailtemplateErroCadastroAD(String login, String nome, String sistema) {
		Map<String, String> model = new HashMap<String, String>();
		model.put("login", login);
		model.put("nome", nome);
		model.put("sistema", sistema);
		return montaMensagem(TEMPLATE_ERRO_AD, model);
	}

	public String getTextEmailNewUserWithPassword(String login, String senha, String sistema, String nome) {
		Map<String, String> model = new HashMap<String, String>();
		model.put("login", login);
		model.put("senha", senha);
		model.put("sistema", sistema);
		model.put("nome", nome);

		return montaMensagem(TEMPLATE_NEW_USER_PASSWORD, model);
	}

	public String getTextEmailNewUser(String login, String nome, String sistema) {
		Map<String, String> model = new HashMap<String, String>();
		model.put("login", login);
		model.put("nome", nome);
		model.put("sistema", sistema);
		return montaMensagem(TEMPLATE_NEW_USER, model);
	}

	public String getTextEmailUpdatePassword(String login) {
		Map<String, String> model = new HashMap<String, String>();
		model.put("login", login);
		return montaMensagem(TEMPLATE_CHANGE_PASSWORD, model);
	}

	public String getTextEmailGeneratedPassword(String login, String nome, String senha, String sistemas) {
		Map<String, String> model = new HashMap<String, String>();
		model.put("login", login);
		model.put("senha", senha);
		model.put("nome", nome);
		model.put("sistemas", sistemas);
		return montaMensagem(TEMPLATE_GENERATE_PASSWORD, model);
	}

	private String montaMensagem(String template, Map<String, String> parametros) {
		String mensagem = null;
		for (Map.Entry<String, String> parametro : parametros.entrySet()) {
			template = template.replace("${" + (String) parametro.getKey() + "}", (CharSequence) parametro.getValue());
		}
		mensagem = template;
		return mensagem;
	}

	private void enviar(String destinatario, String mensagem, String titulo, String sistema) throws MessagingException {
		String remetente = sistema + "@sistemaindustria.org.br";
		Properties props = new Properties();

		props.put("mail.host", "10.16.149.47");

		Session session = Session.getDefaultInstance(props);

		MimeMessage message = new MimeMessage(session);

		Address from = new InternetAddress(remetente);
		Address to = new InternetAddress(destinatario);

		message.setFrom(from);
		message.addRecipient(Message.RecipientType.TO, to);

		message.setSentDate(new Date());
		message.setSubject(titulo);

		String htmlMessage = mensagem;

		Multipart multipart = new MimeMultipart();

		MimeBodyPart attachment0 = new MimeBodyPart();

		attachment0.setContent(htmlMessage, "text/html; charset=UTF-8");

		multipart.addBodyPart(attachment0);
		message.setContent(multipart);

		Transport.send(message);
	}
}