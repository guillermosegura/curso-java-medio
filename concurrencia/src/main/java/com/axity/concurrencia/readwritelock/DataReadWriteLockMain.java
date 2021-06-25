package com.axity.concurrencia.readwritelock;

public class DataReadWriteLockMain
{

  public static void main( String[] args )
  {
    DataReadWriteLock data = new DataReadWriteLock();
    
    new ProducerReadWriteLock( data, "Producer 1");
    new ProducerReadWriteLock( data, "Producer 2" );
    new ConsumerReadWriteLock( data, "Consumer 1" );
    new ConsumerReadWriteLock( data, "Consumer 2" );
    new ConsumerReadWriteLock( data, "Consumer 3" );

  }

}
