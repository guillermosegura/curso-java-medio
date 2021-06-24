package com.axity.services;

import java.util.List;

import com.axity.to.RoleTO;
import com.axity.to.UserTO;

public interface UserService
{
  boolean validate( UserTO user );

  UserTO findUser( String username );

  void updateUserRoles( UserTO user, List<RoleTO> roles );

  List<RoleTO> findRoles( String username );
}
