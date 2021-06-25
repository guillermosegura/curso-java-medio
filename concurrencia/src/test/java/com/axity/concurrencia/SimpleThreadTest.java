package com.axity.concurrencia;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class SimpleThreadTest
{

  @Before
  public void setUp() throws Exception
  {
  }

  @Test
  public void test() throws InterruptedException
  {
    List<Runnable> list = new ArrayList<>();
    for( int i = 0; i < 10; i++ )
    {
      SimpleThread simpleThread = new SimpleThread( "SimpleThread[" + (i+1) + "]" );
      simpleThread.start();
      list.add( simpleThread );
    }

//    list.stream().forEach( t -> t.j );
    Thread.sleep( 2000L );
  }

}
