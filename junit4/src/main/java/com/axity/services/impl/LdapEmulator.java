package com.axity.services.impl;

import java.util.HashMap;
import java.util.Map;

public final class LdapEmulator
{
  private static Map<String, String> users;

  static
  {
    users = new HashMap<>();
    users.put( "gsegura", "password" );
  }

  public static boolean isValid( String user, String password )
  {

    return users.get( user ).equals( password );
  }

  public static boolean exists( String user )
  {
    return users.containsKey( user );
  }
}
