package com.axity.concurrencia.executor;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class RestClient extends Thread
{
  private static final Logger LOG = LoggerFactory.getLogger( RestClient.class );
  private String url;
  public RestClient( String url )
  {
    this.url = url;
  }

  @Override
  public void run()
  {

    activeWait();
    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity<String> response = restTemplate.getForEntity( this.url, String.class );
    activeWait();
    if( response.hasBody() )
    {
      String body = response.getBody();
      LOG.info( body );
    }
  }

  private void activeWait()
  {
    Random random = new Random();
    try
    {
      Thread.sleep( Math.abs( random.nextLong() % 1000 ) );
    }
    catch( InterruptedException e )
    {
      LOG.error( e.getMessage(), e );
    }
  }
}
