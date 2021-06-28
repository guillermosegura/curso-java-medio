package com.axity.crud.controller;

import java.io.Serializable;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.axity.crud.to.ErrorTO;
import com.axity.crud.to.BusinessException;

@Aspect
@Configuration
public class ControllerAspect
{

  @SuppressWarnings("unchecked")
  @Around(value = "within (@com.axity.crud.controller.Intercept *)")
  public ResponseEntity<Serializable> execute( ProceedingJoinPoint joinPoint ) throws Throwable
  {
    ResponseEntity<Serializable> response;
    try
    {
      response = (ResponseEntity<Serializable>) joinPoint.proceed();
    }
    catch( BusinessException e )
    {
      if( e.getError().isBadRequest() )
      {
        response = new ResponseEntity<>( e.getError(), HttpStatus.BAD_REQUEST );
      }
      else
      {
        response = new ResponseEntity<>( e.getError(), HttpStatus.INTERNAL_SERVER_ERROR );
      }
    }
    catch( Throwable e )
    {
      ErrorTO error = new ErrorTO();
      error.setId( 0L );
      error.setName( "Unknown error" );
      error.setDescription( e.getMessage() );
      error.setTrace( ExceptionUtils.getStackTrace( e ) );
      response = new ResponseEntity<>( error, HttpStatus.INTERNAL_SERVER_ERROR );
    }
    return response;
  }
}
