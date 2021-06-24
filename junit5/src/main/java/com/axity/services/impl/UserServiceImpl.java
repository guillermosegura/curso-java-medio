package com.axity.services.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.axity.exception.BusinessExcepcion;
import com.axity.exception.BusinessExcepcionCode;
import com.axity.services.LdapService;
import com.axity.services.UserService;
import com.axity.to.RoleTO;
import com.axity.to.UserTO;

public class UserServiceImpl implements UserService
{
  private static final Logger LOG = LoggerFactory.getLogger( UserServiceImpl.class );

  private LdapService ldapService;

  @Override
  public boolean validate( UserTO user )
  {
    boolean isValid = false;
    try
    {
      ldapService.authenticate( user.getUsername(), user.getPassword() );
      isValid = true;
    }
    catch( BusinessExcepcion e )
    {
      if( e.getCode().equals( BusinessExcepcionCode.LDAP_USER_INVALID ) )
      {
        LOG.warn( "Usuario invalido: " + user.getUsername() );
        LOG.warn( "{}", e.getCode() );
      }
      else
      {
        throw e;
      }

    }

    return isValid;
  }

  @Override
  public UserTO findUser( String username )
  {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void updateUserRoles( UserTO user, List<RoleTO> roles )
  {
    // TODO Auto-generated method stub

  }

  @Override
  public List<RoleTO> findRoles( String username )
  {
    // TODO Auto-generated method stub
    return null;
  }

}
