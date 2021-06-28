package com.axity.libs.apache.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileMain
{

  public static void main( String[] args ) throws IOException {
    File fd = new File( "archivo.txt" );
    FileWriter fw = new FileWriter( fd );

    FileReader fr = new FileReader( fd );

    BufferedReader br = new BufferedReader( fr );
    BufferedWriter bw = new BufferedWriter( fw );

    bw.write( "Person of interest" );
    bw.close();

    String line;

    while( (line = br.readLine()) != null ) {
      System.out.println( line );
    }

    fr.close();
  }

}
