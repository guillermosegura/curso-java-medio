package com.axity.concurrencia.executor;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ExecutorMain
{

  public static void main( String[] args )
  {
    Executor executor = Executors.newCachedThreadPool();
    executor.execute( new RestClient( "http://httpbin.org/get" ) );
    executor.execute( new RestClient( "http://httpbin.org/get" ) );
    executor.execute( new RestClient( "http://httpbin.org/get" ) );
    executor.execute( new RestClient( "http://httpbin.org/get" ) );
    executor.execute( new RestClient( "http://httpbin.org/get" ) );
    executor.execute( new RestClient( "http://httpbin.org/get" ) );
    executor.execute( new RestClient( "http://httpbin.org/get" ) );
    executor.execute( new RestClient( "http://httpbin.org/get" ) );
    executor.execute( new RestClient( "http://httpbin.org/get" ) );
    executor.execute( new RestClient( "http://httpbin.org/get" ) );
    executor.execute( new RestClient( "http://httpbin.org/get" ) );
    
    ThreadPoolExecutor pool = (ThreadPoolExecutor) executor;
    pool.shutdown();
  }

}
