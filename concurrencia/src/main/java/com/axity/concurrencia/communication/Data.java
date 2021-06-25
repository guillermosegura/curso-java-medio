package com.axity.concurrencia.communication;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Data
{
  private static final Logger LOG = LoggerFactory.getLogger( Data.class );

  private List<Integer> data;
  boolean flag = false;

  public Data()
  {
    data = Collections.synchronizedList( new LinkedList<>() );
  }

  public synchronized void produceNumbers( List<Integer> list )
  {
    if( flag )
    {
      try
      {
        wait();
      }
      catch( InterruptedException e )
      {
        LOG.error( e.getMessage(), e );
      }
    }
    flag = true;
    LOG.info( "Agregando {} registros", list.size() );
    this.data.addAll( list );
    flag = false;
    notify();

  }

  public synchronized int consumeNumber()
  {
    if( flag )
    {
      try
      {
        wait();
      }
      catch( InterruptedException e )
      {
        LOG.error( e.getMessage(), e );
      }
    }
    flag = true;
    Integer n = -1;
    if( !this.data.isEmpty() )
    {
      n = this.data.remove( 0 );
    }
    flag = false;
    notify();
    return n;

  }

}
