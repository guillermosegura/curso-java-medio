package com.axity.concurrencia.readwritelock;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataReadWriteLock
{
  private static final Logger LOG = LoggerFactory.getLogger( DataReadWriteLock.class );

  private List<Integer> data;
  private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

  public DataReadWriteLock()
  {
    this.data = Collections.synchronizedList( new LinkedList<>() );
  }

  public void produceNumbers( List<Integer> list )
  {
    try
    {
      lock.writeLock().lock();

      LOG.info( "Agregando {} registros", list.size() );
      this.data.addAll( list );
    }
    finally
    {
      lock.writeLock().unlock();
    }
  }

  public int consumeNumber()
  {

    if( lock.isWriteLocked() )
    {
      LOG.info( "Write Lock Present." );
    }
    lock.readLock().lock();

    Integer n = -1;
    if( !this.data.isEmpty() )
    {
      n = this.data.remove( 0 );
    }
    lock.readLock().unlock();

    return n;

  }

}
