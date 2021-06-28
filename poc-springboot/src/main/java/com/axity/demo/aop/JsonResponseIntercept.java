package com.axity.demo.aop;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Anotación para interceptar métodos de controladores que regresan un objeto JSON
 * 
 * @author guillermo.segura@axity.com
 */
@Retention(RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD })
public @interface JsonResponseIntercept
{

}
