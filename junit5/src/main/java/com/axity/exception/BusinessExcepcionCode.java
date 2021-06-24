package com.axity.exception;

/**
 * Enumeración de excepciones de negocio
 * 
 * @author guillermo.segura@axity.com
 */
public enum BusinessExcepcionCode
{
  INVALID_DATA("001"), DB_INTEGRITY("002"), UNKOWN_EXCEPTION("003"), DIVISION_BY_ZERO("004")
  , LDAP_USER_INVALID("005"),LDAP_USER_WITHOUT_ROLES("006"),
  LDAP_ERROR("007")
  ;

  private String code;

  private BusinessExcepcionCode( String code )
  {
    this.code = code;
  }

  public String getCode()
  {
    return code;
  }

}
