package com.axity.services;

import java.util.List;

public interface LdapService
{

  void authenticate( String username, String password );

  List<String> getRoles( String username );
}
