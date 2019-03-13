package br.com.quaemo.api.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.annotation.AliasFor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
 
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE) 

@Configuration
@ComponentScan
@EnableScheduling
@EnableAspectJAutoProxy
@EnableCaching
@EnableAsync
@EnableJpaRepositories
@EnableTransactionManagement
public @interface ApiConfiguration {

	 @AliasFor(annotation = ComponentScan.class, attribute = "basePackages")
	 String[] component() default "";
	 
	 @AliasFor(annotation = EnableJpaRepositories.class, attribute = "basePackages")
	 String[] persistence() default "";
}
