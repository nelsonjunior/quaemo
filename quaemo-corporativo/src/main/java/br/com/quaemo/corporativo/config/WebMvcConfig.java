package br.com.quaemo.corporativo.config;

import java.util.Properties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import br.com.quaemo.corporativo.interceptor.ValidaTokenInterceptor;
import br.com.quaemo.corporativo.service.ConfiguracaoService;

import org.springframework.stereotype.Controller;
import org.springframework.context.annotation.ComponentScan.Filter;

@Configuration
@ComponentScan(basePackages = { "br.com.quaemo.corporativo" }
             , useDefaultFilters = false
             , includeFilters = {@Filter({ Controller.class })}
)
@EnableWebMvc
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	public void addViewControllers(ViewControllerRegistry registry) {
		super.addViewControllers(registry);
		registry.addViewController("home").setViewName("home");
		registry.addViewController("admin").setViewName("admin");
	}

	@Bean
	public ViewResolver resolver() {
		InternalResourceViewResolver url = new InternalResourceViewResolver();
		url.setPrefix("/WEB-INF/views/");
		url.setSuffix(".jsp");
		return url;
	}

	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler(new String[] { "/resources/**" }).addResourceLocations(new String[] { "/resources/" });
	}

	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Bean(name = { "messageSource" })
	public MessageSource configureMessageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:messages");
		messageSource.setCacheSeconds(5);
		return messageSource;
	}

	@Bean
	public SimpleMappingExceptionResolver simpleMappingExceptionResolver() {
		SimpleMappingExceptionResolver b = new SimpleMappingExceptionResolver();
		Properties mappings = new Properties();
		mappings.put("org.springframework.dao.DataAccessException", "error");
		b.setExceptionMappings(mappings);
		return b;
	}

	@Bean(name = { "codSistema" })
	public Integer codSistema() {
		return 2;
	}

	@Bean(name = { "restTemplate" })
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean(name = { "configuracao" })
	public ConfiguracaoService configuracao() {
		return new ConfiguracaoService();
	}

	@Bean(name = { "urlApiAutenticacao" })
	public String urlApiAutenticacao() {
		return configuracao().getValorConfiguracao("url.servicos.api.autenticacao");
	}

	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**");
	}

	@Bean
	public ValidaTokenInterceptor validaTokenInterceptor() {
		return new ValidaTokenInterceptor();
	}

	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(validaTokenInterceptor()).addPathPatterns(configuracao().getRecursos());
	}
}
