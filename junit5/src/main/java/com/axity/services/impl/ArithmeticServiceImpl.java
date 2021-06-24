package com.axity.services.impl;

import com.axity.exception.BusinessExcepcion;
import com.axity.exception.BusinessExcepcionCode;
import com.axity.services.ArithmeticService;

public class ArithmeticServiceImpl implements ArithmeticService
{

  @Override
  public double add( double a, double b )
  {
    return a + b;
  }

  @Override
  public double substract( double a, double b )
  {
    return a - b;
  }

  @Override
  public double multiply( double a, double b )
  {
    return a * b;
  }

  @Override
  public double divide( double a, double b )
  {
    if( b == 0.0 )
    {
      BusinessExcepcion be = new BusinessExcepcion( "División por 0" );
      be.setCode( BusinessExcepcionCode.DIVISION_BY_ZERO );
      throw be;
    }
    return a / b;
  }

}
