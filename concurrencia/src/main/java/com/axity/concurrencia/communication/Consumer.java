package com.axity.concurrencia.communication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Consumer implements Runnable
{
  private static final Logger LOG = LoggerFactory.getLogger( Consumer.class );
  private Data data;
  private String name;

  public Consumer( Data data, String name )
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

    }

  }

}
