package br.com.quaemo.autenticacao.config;

import javax.sql.DataSource;

import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.orm.hibernate4.HibernateExceptionTranslator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import br.com.quaemo.annotation.ApiConfiguration;
import br.com.quaemo.config.EntityManagerUtil;
 
@ApiConfiguration(component="br.com.quaemo.autenticacao", persistence="br.com.quaemo.autenticacao.persistence")
public class AppConfig {

	private EntityManagerUtil em = new EntityManagerUtil("quaemo-autenticacao")
			                          .dialect(EntityManagerUtil.MYSQL)
                                      .dataSource("java:jboss/datasources/Quaemo_AutenticacaoDS")
                                      .packagesToScan(new String[] { "br.com.quaemo.domain"}) 
                                      .showSql()
                                      ;
	@Bean
	public static PropertySourcesPlaceholderConfigurer placeHolderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		return new JpaTransactionManager(entityManagerFactory().getObject());
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() { 
		return em.getEntityManagerFactory();
	}

	@Bean
	public HibernateExceptionTranslator hibernateExceptionTranslator() {
		return new HibernateExceptionTranslator();
	} 

	@Bean(name = "dataSource")
	public DataSource dataSource() {  
		return em.getDataSource();
	} 

	@Bean
	public CacheManager cacheManager() {
		return new ConcurrentMapCacheManager();
	}
}
