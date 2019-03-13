package br.com.quaemo.api.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AliasFor;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
 
 
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE) 
 
@Configuration
@ComponentScan(useDefaultFilters = false, includeFilters = @Filter({ Controller.class }))
@EnableWebMvc
public @interface ApiWebMvcConfiguration {

	 @AliasFor(annotation = ComponentScan.class, attribute = "basePackages")
	 String[] value() default ""; 
}
