package com.axity.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.axity.exception.BusinessExcepcion;
import com.axity.exception.BusinessExcepcionCode;
import com.axity.services.LdapService;
import com.axity.services.UserService;
import com.axity.to.User;

public class UserServiceImpl implements UserService
{
  private static final Logger LOG = LoggerFactory.getLogger( UserServiceImpl.class );

  private LdapService ldapService;

  public UserServiceImpl()
  {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean validate( User user )
  {
    boolean isValid = false;
    try
    {

      validateUserNotNull( user );
      validateUsernameNotNull( user );
      validatePasswordNotNull( user );

      isValid = ldapService.authenticate( user.getUsername(), user.getPassword() );
    }
    catch( BusinessExcepcion e )
    {
      LOG.error( e.getMessage() );
      LOG.error( "{}", e.getCode() );

      if( !e.getCode().equals( BusinessExcepcionCode.LDAP_ERROR ) )
      {
        throw e;
      }
    }

    return isValid;
  }

  private void validatePasswordNotNull( User user )
  {
    if( user.getPassword() == null )
    {
      BusinessExcepcion be = new BusinessExcepcion( "Password nulo" );
      be.setCode( BusinessExcepcionCode.INVALID_DATA );
      throw be;
    }
  }

  private void validateUsernameNotNull( User user )
  {
    if( user.getUsername() == null )
    {
      BusinessExcepcion be = new BusinessExcepcion( "Username nulo" );
      be.setCode( BusinessExcepcionCode.INVALID_DATA );
      throw be;
    }
  }

  private void validateUserNotNull( User user )
  {
    if( user == null )
    {
      BusinessExcepcion be = new BusinessExcepcion( "Usuario nulo" );
      be.setCode( BusinessExcepcionCode.INVALID_DATA );
      throw be;
    }
  }

  public void setLdapService( LdapService ldapService )
  {
    this.ldapService = ldapService;
  }

}
