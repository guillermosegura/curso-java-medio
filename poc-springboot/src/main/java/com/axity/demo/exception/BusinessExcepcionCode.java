package com.axity.demo.exception;

import org.springframework.http.HttpStatus;

/**
 * Enumeración de excepciones de negocio
 * 
 * @author guillermo.segura@axity.com
 */
public enum BusinessExcepcionCode
{
  // Errores 204
  OFFICE_NOT_FOUND("001", HttpStatus.NO_CONTENT),
  EMPLOYEE_NOT_FOUND("002", HttpStatus.NO_CONTENT),
  CUSTOMER_NOT_FOUND("003", HttpStatus.NO_CONTENT),
  ORDER_NOT_FOUND("004", HttpStatus.NO_CONTENT),
  ORDER_DETAIL_NOT_FOUND("005", HttpStatus.NO_CONTENT),
  PAYMENT_NOT_FOUND("006", HttpStatus.NO_CONTENT),
  PRODUCT_NOT_FOUND("007", HttpStatus.NO_CONTENT),
  PRODUCT_LINE_NOT_FOUND("008", HttpStatus.NO_CONTENT),
  // Errores 400 (validacion)
  INVALID_DATA("100",HttpStatus.BAD_REQUEST),
  // Errores 500 de sistema
  DB_ERROR("901", HttpStatus.INTERNAL_SERVER_ERROR),
  UNKOWN_EXCEPTION("902", HttpStatus.INTERNAL_SERVER_ERROR);

  private String code;
  private HttpStatus status;

  private BusinessExcepcionCode( String code , HttpStatus status)
  {
    this.code = code;
    this.status = status;
  }

  public String getCode()
  {
    return code;
  }

  public HttpStatus getStatus()
  {
    return status;
  }

 

}
