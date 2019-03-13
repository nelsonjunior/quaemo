package br.com.quaemo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.core.annotation.AliasFor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Aplicativo 
 * @Modulo quaemo-corporativo-api
 * @Autor <a href="mailto:jackmaruno@gmail.com">Willian.Maruno</a>
 * @Data 4 de set de 2018 22:45:05
 * @Pacote br.com.quaemo.corporativo.api.resources
 * @Nome RestApiController.java
 * @NomeCompleto br.com.quaemo.corporativo.api.resources.RestApiController.java
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@RestController
@RequestMapping(value = "/api" 
, produces = MediaType.APPLICATION_JSON_VALUE
, consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE, MediaType.APPLICATION_JSON_VALUE }
)
public @interface RestApiController {

	 @AliasFor(annotation = RequestMapping.class, attribute = "value")
	 String[] value() default "/api";
	 
	 @AliasFor(annotation = RequestMapping.class, attribute = "consumes")
	 String[] consumes() default {MediaType.APPLICATION_FORM_URLENCODED_VALUE, MediaType.APPLICATION_JSON_VALUE };
}
