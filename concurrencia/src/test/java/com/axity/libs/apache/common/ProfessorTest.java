package com.axity.libs.apache.common;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProfessorTest
{
  // private static final Logger LOG = LoggerFactory.getLogger( ProfessorTest.class );

  @Test
  public void test()
  {
    Professor professor = Professor.builder().employeeNumber( 124 ).name( "Jose" ).lastname( "Zamarripa" ).title( "Dr" )
        .build();

    log.info( "{}", professor );
//    log.info( professor.toString() );
  }

}
