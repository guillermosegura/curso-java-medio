package com.axity.concurrencia;

public class AnotherThreadMain
{

  public static void main( String[] args )
  {
    for( int i = 0; i < 10; i++ )
    {
      AnotherThread anotherThread = new AnotherThread( "AnotherThread[" + (i+1) + "]" );
      anotherThread.start();
    }

  }

}
