package com.axity.libs.apache.common;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.axity.libs.apache.common.builder.StudentBuilder;

public class StudentTest
{
  private static final Logger LOG = LoggerFactory.getLogger( StudentTest.class );
  @Before
  public void setUp() throws Exception
  {
  }

  @Test
  public void testEquals()
  {
    Student s1 = StudentBuilder.getInstance().name( "Juan" ).lastname( "Lopez" ).age( 20 ).create();

    Student s2 = StudentBuilder.getInstance().name( "Juan" ).lastname( "Lopez" ).age( 20 ).create();
    Student s3 = StudentBuilder.getInstance().name( "Maria" ).lastname( "Lopez" ).age( 20 ).create();

    assertTrue( s1.equals( s1 ) );
    assertTrue( s1.equals( s2 ) );
    assertTrue( s2.equals( s1 ) );
    assertFalse( s3.equals( s1 ) );
    assertFalse( s3.equals( s2 ) );
    assertFalse( s1.equals( null ) );
  }

  @Test
  public void testHashCode()
  {
    Student s1 = StudentBuilder.getInstance().name( "Juan" ).lastname( "Lopez" ).age( 20 ).create();
    Student s2 = StudentBuilder.getInstance().name( "Juan" ).lastname( "Lopez" ).age( 20 ).create();
    Student s3 = StudentBuilder.getInstance().name( "Maria" ).lastname( "Lopez" ).age( 20 ).create();
    assertEquals( s1.hashCode(), s2.hashCode() );
    assertNotEquals( s1.hashCode(), s3.hashCode() );
    
  }

  @Test
  public void testToString()
  {
    Student s1 = StudentBuilder.getInstance().name( "Juan" ).lastname( "Lopez" ).age( 20 ).create();
    debugLogStudent( s1 );

  }

  private void debugLogStudent( Student s1 )
  {
    if (LOG.isDebugEnabled()) {
      LOG.debug( "{}", s1 );
    }
  }

}
