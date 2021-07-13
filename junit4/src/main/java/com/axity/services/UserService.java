package com.axity.services;

import com.axity.to.User;

public interface UserService
{
  /**
   * Valida un usuario por medio de su contraseña ante un LDAP
   * 
   * @param user
   * @return
   */
  boolean validate( User user );
}
