package com.axity.concurrencia.lock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConsumerLock implements Runnable
{
  private static final Logger LOG = LoggerFactory.getLogger( ConsumerLock.class );
  private DataLock data;
  private String name;

  public ConsumerLock( DataLock data, String name )
  {
    this.data = data;
    this.name = name;
    new Thread( this, name ).start();
  }

  @Override
  public void run()
  {
    int count = 0;
    while( true )
    {

      int n = this.data.consumeNumber();
      if( n < 0 )
      {
        count++;
      }
      else
      {
        LOG.info( "Se consumió n={} [{}]", n, this.name );
      }

      if( count == 5 )
      {
        LOG.info( "Termina Consumer {}", this.name );
        break;
      }

      try
      {
        Thread.sleep( 50L );
      }
      catch( InterruptedException e )
      {
        LOG.error( e.getMessage(), e );
      }
    }

  }

}
