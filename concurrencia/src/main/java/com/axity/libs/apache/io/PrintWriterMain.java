package com.axity.libs.apache.io;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class PrintWriterMain
{
  public static void main( String[] args ) throws IOException
  {
    FileWriter fichero = null;
    PrintWriter pw = null;
    fichero = new FileWriter( "archivo2.txt" );

    pw = new PrintWriter( fichero );

    for( int i = 0; i < 10; i++ )
    {
      pw.println( "L\u00ednea " + i );
    }

    pw.close();
  }
}
