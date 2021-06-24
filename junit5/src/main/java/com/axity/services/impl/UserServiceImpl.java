package com.axity.services.impl;

import java.util.List;

import com.axity.services.LdapService;
import com.axity.services.UserService;
import com.axity.to.RoleTO;
import com.axity.to.UserTO;

public class UserServiceImpl implements UserService
{

  private LdapService ldapService;

  @Override
  public boolean validate( UserTO user )
  {
    // TODO Auto-generated method stub
    return false;
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
