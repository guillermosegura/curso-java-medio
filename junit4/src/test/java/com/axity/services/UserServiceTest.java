package com.axity.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
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

  private LdapService ldapService;

  @Before
  public void setUp()
  {
    userService = new UserServiceImpl();

    ldapService = mock( LdapService.class );
    ((UserServiceImpl) userService).setLdapService( ldapService );

    when( ldapService.authenticate( "gsegura", "password" ) ).thenReturn( true );
    when( ldapService.authenticate( "jlopez", "password" ) ).thenReturn( true );
    when( ldapService.authenticate( "jcano", "password" ) ).thenReturn( true );

    // when( ldapService.authenticate( ArgumentMatchers.anyString(), ArgumentMatchers.anyString() ) ).thenReturn( false
    // );
  }

  /**
   * Escenario base valida que la contraseña/usuario sean válidos
   */
  @Test
  public void testValidate()
  {
    User user = new User();
    user.setUsername( "gsegura" );
    user.setPassword( "password" );

    boolean isValid = userService.validate( user );
    assertTrue( isValid );
  }

  /**
   * Escenario alterno, se envía una contraseña/usuario sean inválidos
   */
  @Test
  public void testValidate_invalidPassword()
  {
    User user = new User();
    user.setUsername( "gsegura" );
    user.setPassword( "passwordw" );

    when( ldapService.authenticate( ArgumentMatchers.anyString(), ArgumentMatchers.anyString() ) ).thenReturn( false );

    boolean isValid = userService.validate( user );
    assertFalse( isValid );
  }

  /**
   * Escenario alterno, se envía un usuario que no existe
   */
  @Test(expected = BusinessExcepcion.class)
  public void testValidate_userNotFound()
  {
    User user = new User();
    user.setUsername( "mperez" );
    user.setPassword( "password" );

    BusinessExcepcion be = new BusinessExcepcion();
    be.setCode( BusinessExcepcionCode.LDAP_ERROR_USER_NOT_FOUND );
    when( ldapService.authenticate( "mperez", "password" ) ).thenThrow( be );

    try
    {
      userService.validate( user );
      fail( "It should throw a BusinessExcepcion" );
    }
    catch( BusinessExcepcion e )
    {
      assertEquals( BusinessExcepcionCode.LDAP_ERROR_USER_NOT_FOUND, e.getCode() );
      throw e;
    }
  }

  /**
   * Escenario alterno, se valida cuando se obtiene otra excepción de LDAP
   */
  @Test(expected = BusinessExcepcion.class)
  public void testValidate_anotherLdapError()
  {
    User user = new User();
    user.setUsername( "mperez" );
    user.setPassword( "password" );

    BusinessExcepcion be = new BusinessExcepcion();
    be.setCode( BusinessExcepcionCode.UNKOWN_EXCEPTION );
    when( ldapService.authenticate( "mperez", "password" ) ).thenThrow( be );

    try
    {
      userService.validate( user );
      fail( "It should throw a BusinessExcepcion" );
    }
    catch( BusinessExcepcion e )
    {
      assertEquals( BusinessExcepcionCode.UNKOWN_EXCEPTION, e.getCode() );
      throw e;
    }
  }

  /**
   * Escenario alterno, se valida cuando se obtiene una excepción de LDAP_ERROR
   */
  @Test
  public void testValidate_LdapError()
  {
    User user = new User();
    user.setUsername( "mperez" );
    user.setPassword( "password" );

    BusinessExcepcion be = new BusinessExcepcion();
    be.setCode( BusinessExcepcionCode.LDAP_ERROR );
    when( ldapService.authenticate( "mperez", "password" ) ).thenThrow( be );

    boolean isValid = userService.validate( user );
    assertFalse( isValid );

  }

  /**
   * Escenario alterno, se valida que el objeto User no sea nulo
   */
  @Test(expected = BusinessExcepcion.class)
  public void testValidate_null()
  {
    User user = null;
    try
    {
      userService.validate( user );
      fail( "It should throw a BusinessExcepcion" );
    }
    catch( BusinessExcepcion e )
    {
      assertEquals( BusinessExcepcionCode.INVALID_DATA, e.getCode() );
      throw e;
    }
  }

  /**
   * Escenario alterno, se valida que el atributo User.username no sea nulo
   */
  @Test(expected = BusinessExcepcion.class)
  public void testValidate_usernameNull()
  {
    User user = new User();
    try
    {
      userService.validate( user );
      fail( "It should throw a BusinessExcepcion" );
    }
    catch( BusinessExcepcion e )
    {
      assertEquals( BusinessExcepcionCode.INVALID_DATA, e.getCode() );
      throw e;
    }
  }

  /**
   * Escenario alterno, se valida que el atributo User.password no sea nulo
   */
  @Test(expected = BusinessExcepcion.class)
  public void testValidate_passwordNull()
  {
    User user = new User();
    user.setUsername( "hlopez" );
    try
    {
      userService.validate( user );
      fail( "It should throw a BusinessExcepcion" );
    }
    catch( BusinessExcepcion e )
    {
      assertEquals( BusinessExcepcionCode.INVALID_DATA, e.getCode() );
      throw e;
    }
  }

}
