package com.axity.concurrencia;

import java.lang.Thread.State;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.DoubleAccumulator;
import java.util.concurrent.atomic.DoubleAdder;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AnotherThreadTest
{
  private static final Logger LOG = LoggerFactory.getLogger( AnotherThreadTest.class );
  @Before
  public void setUp() throws Exception
  {

  }

  @Test
  public void test() throws InterruptedException
  {
    for( int i = 0; i < 10; i++ )
    {
      AnotherThread anotherThread = new AnotherThread( "AnotherThread[" + (i + 1) + "]" );
      anotherThread.start();
    }

    Thread.sleep( 1000L );

  }

}
