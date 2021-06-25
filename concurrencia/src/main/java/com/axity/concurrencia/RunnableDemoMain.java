package com.axity.concurrencia;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RunnableDemoMain
{
  private static final Logger LOG = LoggerFactory.getLogger( RunnableDemoMain.class );

  public static void main( String[] args )
  {
    RunnableDemo runnableDemo1 = new RunnableDemo( "Thread-1" );
    runnableDemo1.start();

    RunnableDemo runnableDemo2 = new RunnableDemo( "Thread-2" );
    runnableDemo2.start();

    try
    {
      Thread.sleep( 1000 );
      runnableDemo1.suspend();
      LOG.info( "Suspending First Thread" );
      Thread.sleep( 1000 );
      runnableDemo1.resume();
      LOG.info( "Resuming First Thread" );

      runnableDemo2.suspend();
      LOG.info( "Suspending thread Two" );
      Thread.sleep( 1000 );
      runnableDemo2.resume();
      LOG.info( "Resuming thread Two" );
    }
    catch( InterruptedException e )
    {
      LOG.info( "Main thread Interrupted" );
    }
    try
    {
      LOG.info( "Waiting for threads to finish." );
      runnableDemo1.getThread().join();
      runnableDemo2.getThread().join();
    }
    catch( InterruptedException e )
    {
      LOG.info( "Main thread Interrupted" );
    }
    LOG.info( "Main thread exiting." );

  }

}
