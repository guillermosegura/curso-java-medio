package com.axity.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.axity.exception.BusinessExcepcion;
import com.axity.exception.BusinessExcepcionCode;
import com.axity.services.UserService;
import com.axity.to.User;

public class UserServiceImpl implements UserService
{
  private static final Logger LOG = LoggerFactory.getLogger( UserServiceImpl.class );
  public UserServiceImpl()
  {
  }

  @Override
  public boolean validate( User user )
  {

    isValidUser( user );

    isValidUsername( user );

    boolean isValid = false;
    try
    {
      if( LdapEmulator.exists( user.getUsername() ) )
      {
        isValidPassword( user );

        isValid = LdapEmulator.isValid( user.getUsername(), user.getPassword() );
      }
    }
    catch( BusinessExcepcion e )
    {
      if( e.getCode().equals( BusinessExcepcionCode.LDAP_ERROR ) )
      {
        LOG.error( e.getMessage(), e );
      }
      throw e;
    }

    return isValid;
  }

  private void isValidPassword( User user )
  {
    if( user.getPassword() == null )
    {
      LOG.warn( "Password nulo" );
      BusinessExcepcion be = new BusinessExcepcion( "Password nulo" );
      be.setCode( BusinessExcepcionCode.INVALID_DATA );
      throw be;
    }
  }

  private void isValidUsername( User user )
  {
    if( user.getUsername() == null )
    {
      LOG.warn( "Username nulo" );
      BusinessExcepcion be = new BusinessExcepcion( "Username nulo" );
      be.setCode( BusinessExcepcionCode.INVALID_DATA );
      throw be;
    }
  }

  private void isValidUser( User user )
  {
    if( user == null )
    {
      LOG.warn( "Password nulo" );
      BusinessExcepcion be = new BusinessExcepcion( "Password nulo" );
      be.setCode( BusinessExcepcionCode.INVALID_DATA );
      throw be;
    }
  }

}
