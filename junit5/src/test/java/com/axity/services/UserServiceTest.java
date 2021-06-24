package com.axity.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

import com.axity.exception.BusinessExcepcion;
import com.axity.exception.BusinessExcepcionCode;
import com.axity.services.impl.UserServiceImpl;
import com.axity.to.UserTO;

class UserServiceTest
{
  UserService userService;
  LdapService ldapService;

  @BeforeEach
  void setUp() throws Exception
  {
    userService = new UserServiceImpl();

    ldapService = Mockito.mock( LdapService.class );
    ReflectionTestUtils.setField( userService, "ldapService", ldapService );

  }

  @Test
  void testValidate()
  {
    UserTO user = new UserTO();
    user.setUsername( "gsegura" );
    user.setPassword( "password" );
    boolean isValid = userService.validate( user );

    assertTrue( isValid );
  }

  @Test
  void testValidate_throwBusinessException_LDAP_USER_INVALID()
  {
    UserTO user = new UserTO();
    user.setUsername( "a" );
    user.setPassword( "a" );

    BusinessExcepcion be = new BusinessExcepcion();
    be.setCode( BusinessExcepcionCode.LDAP_USER_INVALID );
    Mockito.doThrow( be ).when( ldapService ).authenticate( ArgumentMatchers.anyString(),
      ArgumentMatchers.anyString() );

    boolean isValid = userService.validate( user );
    assertFalse( isValid );
  }

  @Test
  void testValidate_throwBusinessException_LDAP_ERROR()
  {
    UserTO user = new UserTO();
    user.setUsername( "a" );
    user.setPassword( "a" );

    BusinessExcepcion be = new BusinessExcepcion();
    be.setCode( BusinessExcepcionCode.LDAP_ERROR );
    Mockito.doThrow( be ).when( ldapService ).authenticate( ArgumentMatchers.anyString(),
      ArgumentMatchers.anyString() );

    BusinessExcepcion result = assertThrows( BusinessExcepcion.class, () -> userService.validate( user ) );
    assertEquals( BusinessExcepcionCode.LDAP_ERROR, result.getCode() );
  }

  @Test
  void testFindUser()
  {
    fail( "Not yet implemented" );
  }

  @Test
  void testUpdateUserRoles()
  {
    fail( "Not yet implemented" );
  }

  @Test
  void testFindRoles()
  {
    fail( "Not yet implemented" );
  }

}
