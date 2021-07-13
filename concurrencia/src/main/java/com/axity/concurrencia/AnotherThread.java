package com.axity.concurrencia;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AnotherThread extends Thread
{
  private static final Logger LOG = LoggerFactory.getLogger( AnotherThread.class );

  private Thread thread;
  private String threadName;

  public AnotherThread( String threadName )
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
      for( int i = 4; i > 0; i-- )
      {
        LOG.info( "Thread: {}, {}", threadName, i );
        // Let the thread sleep for a while.
        Thread.sleep( 50 );
      }
    }
    catch( InterruptedException e )
    {
      LOG.info( "Thread {}  interrupted.", this.threadName );
    }
    LOG.info( "Thread {} exiting.", threadName );
  }
  
  @Override
  public void start()
  {
    LOG.info( "Starting {}", threadName );
    
   

    if( this.thread == null )
    {
      this.thread = new Thread( this, this.threadName );
      this.thread.start();
    }
  }
}
