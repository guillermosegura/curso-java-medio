package com.axity.to;

import java.util.ArrayList;
import java.util.List;

public class UserTO
{
  private String username;
  private String password;
  private List<RoleTO> roles;

  public UserTO()
  {
    roles = new ArrayList<>();
  }

  public String getUsername()
  {
    return username;
  }

  public void setUsername( String username )
  {
    this.username = username;
  }

  public String getPassword()
  {
    return password;
  }

  public void setPassword( String password )
  {
    this.password = password;
  }

  public List<RoleTO> getRoles()
  {
    return roles;
  }

  public void setRoles( List<RoleTO> roles )
  {
    this.roles = roles;
  }

}
