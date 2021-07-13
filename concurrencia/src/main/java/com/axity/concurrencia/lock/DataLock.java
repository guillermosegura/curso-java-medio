package com.axity.concurrencia.lock;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataLock
{
  private static final Logger LOG = LoggerFactory.getLogger( DataLock.class );

  private List<Integer> data;
  private final Lock queueLock = new ReentrantLock();

  public DataLock()
  {
    this.data = Collections.synchronizedList( new LinkedList<>() );
  }

  public void produceNumbers( List<Integer> list )
  {
    try
    {
      queueLock.lock();

      LOG.info( "Agregando {} registros", list.size() );
      this.data.addAll( list );
    }
    finally
    {
      queueLock.unlock();
    }
  }

  public int consumeNumber()
  {

    try
    {
      queueLock.lock();

      Integer n = -1;
      if( !this.data.isEmpty() )
      {
        n = this.data.remove( 0 );
      }
      return n;
    }
    finally
    {
      queueLock.unlock();
    }


  }

}
