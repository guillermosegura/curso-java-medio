package com.axity.concurrencia.communication;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Producer implements Runnable
{
  private static final Logger LOG = LoggerFactory.getLogger( Producer.class );
  private Data data;

  public Producer( Data data )
  {
    this.data = data;
    new Thread( this, "Producer" ).start();
  }

  @Override
  public void run()
  {
    Random random = new Random();

    for( int i = 0; i < 5; i++ )
    {
      List<Integer> list = random.ints( 1, 100 ).limit( 20 ).collect( ArrayList::new, ArrayList::add,
        ArrayList::addAll );

      data.produceNumbers( list );
      try
      {
        Thread.sleep( 20L );
      }
      catch( InterruptedException e )
      {
        LOG.error( e.getMessage(), e );
      }
    }
    LOG.info( "Termina producer" );

  }

}
