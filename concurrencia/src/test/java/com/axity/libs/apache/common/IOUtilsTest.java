package com.axity.libs.apache.common;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IOUtilsTest
{
  private static final Logger LOG = LoggerFactory.getLogger( IOUtilsTest.class );
  @Test
  public void test()
  {
    File file = new File( "C:\\proyectos\\curso\\curso-java-medio\\concurrencia\\src\\test\\resources\\lorem.txt" );
    InputStream is = null;
    try
    {
      is = new FileInputStream( file );

      // Charset iso88591charset = Charset.forName( "ISO-8859-1" );
      // List<String> lines = IOUtils.readLines( is, iso88591charset );
      InputStreamReader isr = new InputStreamReader( is );
      // BufferedInputStream b = new BufferedInputStream( is );

      BufferedReader br = new BufferedReader( isr );

      String line = br.readLine();
      while( line != null )
      {
        LOG.info( line );
        line = br.readLine();
      }
    }
    catch( IOException e )
    {
      LOG.error( e.getMessage(), e );

    }
    finally
    {
      IOUtils.closeQuietly( is );
    }

  }

}
