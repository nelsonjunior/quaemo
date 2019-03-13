package br.com.quaemo.api.config;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import br.com.quaemo.api.annotation.ApiWebMvcConfiguration;
import br.com.quaemo.api.domain.AccessToken;
import br.com.quaemo.api.domain.AplicacaoCliente;
import br.com.quaemo.api.domain.Configuracao;
import br.com.quaemo.api.domain.Recurso;
import br.com.quaemo.api.exceptions.OperacaoNaoRealizadaException;
import br.com.quaemo.api.interceptor.TokenInterceptor;
import br.com.quaemo.api.service.ConfiguracaoService;
import br.com.quaemo.api.service.RequestService;
import br.com.quaemo.api.service.TokenService;
import br.com.quaemo.api.service.UsuarioService;
import br.com.quaemo.api.service.UsuarioSistemaService;
import br.com.quaemo.api.vo.UrlServicosVO;
 
@ApiWebMvcConfiguration("br.com.quaemo.api")
public class ApiWebMvcConfigAdapter extends WebMvcConfigurerAdapter{

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer){
		configurer.enable();
	}

	@Bean(name = "messageSource")
	public MessageSource configureMessageSource(){
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:messages");
		messageSource.setCacheSeconds(5);
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}

	@Bean
	public SimpleMappingExceptionResolver simpleMappingExceptionResolver(){
		SimpleMappingExceptionResolver b = new SimpleMappingExceptionResolver();
		Properties mappings = new Properties();
		mappings.put("org.springframework.dao.DataAccessException", "error");
		b.setExceptionMappings(mappings);
		return b;
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**");
	}
	
	@Bean(name = "restTemplate")
	public RestTemplate restTemplate() {
	   return new RestTemplate();
	}

	
	@Bean(name = "configuracoes")
	public List<Configuracao> configuracoes() {
	   return configuracao().getConfiguracaoes();
	}
	
	@Bean(name = "configuracao")
	public ConfiguracaoService configuracao() {
	   return new ConfiguracaoService();
	}
	
	@Bean(name = "usuarioService")
	public UsuarioService usuarioService() {
	   return new UsuarioService();
	}
	
	@Bean(name = "usuarioSistemaService")
	public UsuarioSistemaService usuarioSistemaService() {
	   return new UsuarioSistemaService();
	}
	

	@Bean(name = "tokenService")
	public TokenService tokenService() {
	   return new TokenService();
	}
	
	@Bean(name = "requestService")
	public RequestService requestService() {
	   return new RequestService();
	}
	
	@Bean(name = "aplicacaoCliente")
	public AplicacaoCliente aplicacaoCliente() {
	   return getAplicacaoCliente();
	}
	
	@Bean(name = "tokenInterceptor")
	public TokenInterceptor tokenInterceptor() {
	   return new TokenInterceptor();
	}
	
	public @Override void addInterceptors(InterceptorRegistry registry) {
	   registry.addInterceptor(tokenInterceptor()).addPathPatterns(findRecursos());
	}	

 
	@Bean(name = "urlServicos")
	public UrlServicosVO urlServicos() {
		UrlServicosVO url =	new UrlServicosVO();
		url.setUrlAutenticacao(getValorConfiguracao("url.autenticacao")); 
		
		url.setUrlSistema(getValorConfiguracao("url.sistema"));  
		
		url.setUrlSistemaAplicacaoCliente(atualizaUrlSistema(getValorConfiguracao("url.sistema.aplicacao.cliente")));
		url.setUrlSistemaPerfil(atualizaUrlSistema(getValorConfiguracao("url.sistema.perfil")));
		url.setUrlSistemaRecurso(atualizaUrlSistema(getValorConfiguracao("url.sistema.recurso")));
		url.setUrlSistemaUsuario(atualizaUrlSistema(getValorConfiguracao("url.sistema.usuario")));
		url.setUrlSistemaUsuarioMenus(atualizaUrlSistema(getValorConfiguracao("url.sistema.usuario.menu")));
		url.setUrlSistemaUsuarioStatus(atualizaUrlSistema(getValorConfiguracao("url.sistema.usuario.status")));
		
		url.setUrlUsuario(getValorConfiguracao("url.usuario"));  
		url.setUrlUsuarioFoto(getValorConfiguracao("url.usuario.foto"));  
		url.setUrlUsuarioLogin(getValorConfiguracao("url.usuario.login"));  
		url.setUrlUsuarioSenha(getValorConfiguracao("url.usuario.senha"));  
		
		return url;
	}
	
	private String atualizaUrlSistema(String url){
		return url.replace("{codSistema}", configuracao().getCodSistema().toString());
	}
	
	private void getUrlAutenticacao(AplicacaoCliente aplicacaoCliente){
		String url = getValorConfiguracao("url.autenticacao");
		url=url+"/oauth2/token?grant_type="+aplicacaoCliente.getGrantType()
		       +"&client_id="+aplicacaoCliente.getCodigo()
		       +"&client_secret="+aplicacaoCliente.getChave()
		       +"&username={login}&password={senha}";		
		urlServicos().setUrlAutenticacaoSistema(url);
	}

	private String getValorConfiguracao(String nomeConfiguracao){
		String configuracao="";
		for(Configuracao conf:configuracoes()){
			if(conf.getNome().equalsIgnoreCase(nomeConfiguracao)){
				configuracao=conf.getValor();
				break;
			}
		 }		
		return configuracao;
	}


	private static final String URL_AUTH = "/oauth2/token?grant_type=client_credentials&client_id={client_id}&client_secret={client_secret}";
	private static final String CLIENT_ID = "{client_id}";
	private static final String CLIENT_SECRET = "{client_secret}";
	private static final String ID_CORPORATIVO = "ID_CORPORATIVO_knjwer7834hrf3897fh94f93844hr4398t";
	private static final String SECRET_CORPORATIVO = "SECRET_CORPORATIVO_fejrh43438934fn3j903i23jri4h84";

	private String getUrlAutenticacao() {
		return urlServicos().getUrlAutenticacao() + URL_AUTH.replace(CLIENT_ID, ID_CORPORATIVO).replace(CLIENT_SECRET, SECRET_CORPORATIVO);
	} 
	
	private AccessToken tokenCorporativo() {
		MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
		HttpHeaders headers = new HttpHeaders();
	    headers.set("Content-Type", "application/x-www-form-urlencoded");
	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		ResponseEntity<AccessToken> response = restTemplate().postForEntity(getUrlAutenticacao(), headers, AccessToken.class, map) ;
		AccessToken token= response.getBody();
	   return token;
	}	


	private String[] findRecursos() throws OperacaoNaoRealizadaException {	
		try {
			String url = atualizaUrlSistema(urlServicos().getUrlSistemaRecurso());
			Recurso[] result = restTemplate().exchange(requestService().get(url, tokenCorporativo()), Recurso[].class).getBody();
		 	String[] lista = new String[result.length];
			for(int i = 0; i < result.length; i++) {
				lista[i]= result[i].getUrl().trim()+"/**"; 
			} 
			return lista;
		} catch (HttpClientErrorException e) {
			e.printStackTrace();
			throw new OperacaoNaoRealizadaException("findRecursos", e);
		} 
	}

	private AplicacaoCliente getAplicacaoCliente(){
		String url = atualizaUrlSistema(urlServicos().getUrlSistemaAplicacaoCliente());
		ResponseEntity<AplicacaoCliente> response = restTemplate().exchange(requestService().get(url, tokenCorporativo()), AplicacaoCliente.class);
		AplicacaoCliente apiCliente=response.getBody();
		getUrlAutenticacao(apiCliente);
		return apiCliente;
	}
}
