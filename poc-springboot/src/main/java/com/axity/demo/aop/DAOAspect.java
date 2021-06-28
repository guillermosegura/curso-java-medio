package com.axity.demo.aop;

import javax.persistence.PersistenceException;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.axity.demo.exception.BusinessExcepcion;
import com.axity.demo.exception.BusinessExcepcionCode;

import lombok.extern.slf4j.Slf4j;

/**
 * Aspecto de la capa DAO
 * 
 * @author guillermo.segura@axity.com
 */
@Aspect
@Component
@Slf4j
public class DAOAspect
{

  @Around("execution (* com.axity.demo.repository.*.*(..))")
  public Object interceptMethodAdvice( ProceedingJoinPoint pjp ) throws Throwable
  {
    Object result = null;
    try
    {
      result = pjp.proceed();
    }
    catch( PersistenceException e )
    {
      String detailMessage = getCause( e );
      log.error( e.getMessage(), e );
      log.error( detailMessage );
      BusinessExcepcion businessExcepcion = new BusinessExcepcion( "Ocurri\u00F3 un error de persistencia ", e );
      businessExcepcion.setDetailMessage( detailMessage );
      businessExcepcion.setCode( BusinessExcepcionCode.DB_ERROR );
      throw businessExcepcion;
    }
    return result;
  }

  private String getCause( Throwable t )
  {
    String message;
    if( t.getCause() != null )
    {
      message = getCause( t.getCause() );
    }
    else
    {
      message = t.getMessage();
    }

    return message;
  }

}
