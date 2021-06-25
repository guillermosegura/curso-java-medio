package com.axity.concurrencia;

public class SimpleThreadMain
{

  public static void main( String[] args )
  {
    for( int i = 0; i < 10; i++ )
    {
      SimpleThread simpleThread = new SimpleThread( "SimpleThread[" + (i+1) + "]" );
      simpleThread.start();
    }

  }

}
