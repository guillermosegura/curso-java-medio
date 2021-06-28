package com.axity.demo;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = { "/api/v1" })
public class HelloController
{

  @GetMapping(path = "/hellojson", produces = MediaType.APPLICATION_JSON_VALUE)
  public Mensaje helloJson()
  {
    return new Mensaje( "Hola mundo 1!!!" );
  }

  @GetMapping(path = "/helloxml", produces = MediaType.APPLICATION_XML_VALUE)
  public Mensaje helloXml()
  {
    return new Mensaje( "Hola mundo 1!!!" );
  }

  @GetMapping(path = "/hello")
  public ResponseEntity<Mensaje> hello(
      @RequestParam(name = "type", required = false, defaultValue = "json") String type )
  {
    ResponseEntity<Mensaje> mensaje = null;
    Mensaje body = new Mensaje( "Hola mundo 1!!!" );
    if( type.equalsIgnoreCase( "xml" ) )
    {
      MultiValueMap<String, String> headers = new HttpHeaders();
      headers.add( HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_XML_VALUE );
      mensaje = new ResponseEntity<Mensaje>( body, headers, HttpStatus.OK );
    }
    else
    {
      MultiValueMap<String, String> headers = new HttpHeaders();
      headers.add( HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE );
      mensaje = new ResponseEntity<Mensaje>( body, headers, HttpStatus.OK );
    }

    return mensaje;
  }
}
