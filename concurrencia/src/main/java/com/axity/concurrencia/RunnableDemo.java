package com.axity.concurrencia;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RunnableDemo implements Runnable
{
  private static final Logger LOG = LoggerFactory.getLogger( RunnableDemo.class );

  private Thread thread;
  private String threadName;
  boolean suspended = false;

  public RunnableDemo( String threadName )
  {
    this.threadName = threadName;
    LOG.info( "Creating {}", threadName );
  }

  @Override
  public void run()
  {
    LOG.info( "Running {}", this.threadName );

    try
    {
      for( int i = 10; i > 0; i-- )
      {
        LOG.info( "Thread: {}, {} ", this.threadName, i );

        // Let the thread sleep for a while.
        Thread.sleep( 300 );

        synchronized( this )
        {

          while( suspended )
          {
            wait();
          }
        }
      }
    }
    catch( InterruptedException e )
    {
      LOG.info( "Thread {}  interrupted.", this.threadName );
    }
    LOG.info( "Thread {} exiting.", threadName );
  }

  public void start()
  {
    LOG.info( "Starting {}", threadName );

    if( this.thread == null )
    {
      this.thread = new Thread( this, this.threadName );
      this.thread.start();
    }
  }

  public void suspend()
  {
    suspended = true;
  }

  public synchronized void resume()
  {
    suspended = false;
    notify();
  }

  public Thread getThread()
  {
    return thread;
  }

}
