package com.axity.concurrencia.lock;

public class DataLockMain
{

  public static void main( String[] args )
  {
    DataLock data = new DataLock();
    
    new ProducerLock( data, "prod1" );
    new ProducerLock( data, "prod2" );
    new ProducerLock( data, "prod3" );
    new ConsumerLock( data, "Consumer 1" );
    new ConsumerLock( data, "Consumer 2" );
    new ConsumerLock( data, "Consumer 3" );
    new ConsumerLock( data, "Consumer 4" );
    new ConsumerLock( data, "Consumer 5" );
    new ConsumerLock( data, "Consumer 6" );
    

  }

}
