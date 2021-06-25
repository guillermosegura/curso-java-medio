package com.axity.libs.apache.common;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HttpClientTest
{

  @Test
  public void test() throws Exception
  {
    CloseableHttpClient httpclient = HttpClients.createDefault();
    try
    {
      
      
      HttpGet httpget = new HttpGet( "http://httpbin.org/get" );

      log.info( "Executing request " + httpget.getRequestLine() );

      // Create a custom response handler
      ResponseHandler<String> responseHandler = new ResponseHandler<String>()
      {

        @Override
        public String handleResponse( final HttpResponse response ) throws ClientProtocolException, IOException
        {
          int status = response.getStatusLine().getStatusCode();
          if( status >= 200 && status < 300 )
          {
            HttpEntity entity = response.getEntity();
            return entity != null ? EntityUtils.toString( entity ) : null;
          }
          else
          {
            throw new ClientProtocolException( "Unexpected response status: " + status );
          }
        }

      };
      String responseBody = httpclient.execute( httpget, responseHandler );
      log.info( "----------------------------------------" );
      log.info( responseBody );
    }
    finally
    {
      httpclient.close();
    }
  }

}
