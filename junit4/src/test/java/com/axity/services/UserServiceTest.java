package com.axity.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Base64;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.axity.exception.BusinessExcepcion;
import com.axity.exception.BusinessExcepcionCode;
import com.axity.services.impl.UserServiceImpl;
import com.axity.to.User;

/**
 * Test Driven Design - TDD
 * 
 * @author guillermo.segura@axity.com
 */
public class UserServiceTest
{
  private static final Logger LOG = LoggerFactory.getLogger( UserServiceTest.class );
  private UserService userService;
  @Before
  public void setUp()
  {
    this.userService = new UserServiceImpl();
  }

  @Test
  public void testValidate()
  {
    User user = new User();
    user.setUsername( "gsegura" );
    user.setPassword( "password" );

    boolean isValid = userService.validate( user );
    assertTrue( isValid );

  }

  @Test
  public void testValidate_invalidPassword()
  {
    User user = new User();
    user.setUsername( "gsegura" );
    user.setPassword( "passwordInvalid" );

    boolean isValid = userService.validate( user );
    assertFalse( isValid );

  }

  @Test
  public void testValidate_userNotFound()
  {
    User user = new User();
    user.setUsername( "jsmith" );

    boolean isValid = userService.validate( user );
    assertFalse( isValid );

  }

  @Test(expected = BusinessExcepcion.class)
  public void testValidate_null()
  {

    try
    {
      userService.validate( null );
    }
    catch( BusinessExcepcion e )
    {
      assertEquals( BusinessExcepcionCode.INVALID_DATA, e.getCode() );
      throw e;
    }

  }

  @Test(expected = BusinessExcepcion.class)
  public void testValidate_usernameNull()
  {

    try
    {
      User user = new User();
      userService.validate( user );
    }
    catch( BusinessExcepcion e )
    {
      assertEquals( BusinessExcepcionCode.INVALID_DATA, e.getCode() );
      throw e;
    }

  }

  @Test(expected = BusinessExcepcion.class)
  public void testValidate_passwordNull()
  {

    try
    {
      User user = new User();
      user.setUsername( "gsegura" );
      userService.validate( user );
    }
    catch( BusinessExcepcion e )
    {
      LOG.error( "{}", Base64.getEncoder().encodeToString( ExceptionUtils.getStackTrace( e ).getBytes() ) );

      assertEquals( BusinessExcepcionCode.INVALID_DATA, e.getCode() );
      throw e;
    }

  }

}
