package com.axity.concurrencia.communication;

public class DataMain
{

  public static void main( String[] args ) throws InterruptedException
  {
    Data data = new Data();
    
    new Producer( data );
    Thread.sleep( 500L );
    new Consumer( data, "Consumer 1" );
    new Consumer( data, "Consumer 2" );
    new Consumer( data, "Consumer 3" );

  }

}
