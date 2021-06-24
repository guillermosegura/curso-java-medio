package com.axity.services;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.axity.exception.BusinessExcepcion;
import com.axity.exception.BusinessExcepcionCode;
import com.axity.services.impl.ArithmeticServiceImpl;

/**
 * @author guillermo.segura@axity.com
 */
public class ArithmeticServiceTest
{
  private static final Logger LOG = LoggerFactory.getLogger( ArithmeticServiceTest.class );

  private ArithmeticService arithmeticService;

  /**
   * @throws Exception
   */
  @Before
  public void setUp() throws Exception
  {
    LOG.debug( "setUp" );
    arithmeticService = new ArithmeticServiceImpl();
  }

  // Este preuba caso base
  @Test
  public void testAdd()
  {
    LOG.debug( "testAdd" );
    double a = 1.0;
    double b = 1.0;
    double result = arithmeticService.add( a, b );

    assertEquals( 2.0, result, 0.1 );

  }

  @Test
  public void testSubstract()
  {
    LOG.debug( "testSubstract" );
    double a = 1.0;
    double b = 1.0;
    double result = arithmeticService.substract( a, b );

    assertEquals( 0.0, result, 0.1 );
  }

  @Test
  public void testMultiply()
  {
    LOG.debug( "testMultiply" );
    double a = 2.0;
    double b = 4.0;
    double result = arithmeticService.multiply( a, b );

    assertEquals( 8.0, result, 0.1 );
  }

  @Test
  public void testDivide()
  {
    LOG.debug( "testDivide" );
    double a = 100.0;
    double b = 10.0;
    double result = arithmeticService.divide( a, b );

    assertEquals( 10.0, result, 0.1 );
  }

  @Test
  public void testDivide_divisionByZero()
  {
    LOG.debug( "testDivide_divisionByZero" );
    double a = 100.0;
    double b = 0.0;
    try
    {
      arithmeticService.divide( a, b );
      fail( "It must never ever get here!!!" );
    }
    catch( BusinessExcepcion e )
    {
      LOG.debug( e.getMessage() );
    }

  }

  @Test(expected = BusinessExcepcion.class)
  public void testDivide_divisionByZeroCorrectWay()
  {
    LOG.debug( "testDivide_divisionByZero" );
    double a = 100.0;
    double b = 0.0;
    try
    {
      arithmeticService.divide( a, b );
      fail( "It must never ever get here!!!" );
    }
    catch( BusinessExcepcion e )
    {
      LOG.debug( e.getMessage() );
      assertEquals( BusinessExcepcionCode.DIVISION_BY_ZERO, e.getCode() );
      throw e;
    }

  }

  /*
   * HU-00025 : Caso a
   */
  @Test(expected = BusinessExcepcion.class)
  public void testDivide_divisionByZeroSimpleWay()
  {
    LOG.debug( "testDivide_divisionByZero" );
    double a = 100.0;
    double b = 0.0;

    arithmeticService.divide( a, b );
    fail( "It must never ever get here!!!" );

  }

}
