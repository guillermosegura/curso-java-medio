package com.axity.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api")
public class DemoController
{

  private List<Demo> data;

  @Value("${demo.app.location}")
  private String location;

  @PostConstruct
  public void init()
  {
    data = new ArrayList<>( Arrays.asList( new Demo( 1, "uno" ), new Demo( 2, "dos" ), new Demo( 3, "tres" ) ) );
  }

  @GetMapping(path = "/data")
  public List<Demo> getDemos()
  {
    return this.data;
  }

  @GetMapping(path = "/data/{id}")
  public ResponseEntity<Demo> getDemoById( @PathVariable(name = "id", required = true) int id )
  {
    ResponseEntity<Demo> response = null;
    Demo demo = this.data.stream().filter( d -> d.getId() == id ).findFirst().orElse( null );
    if( demo != null )
    {
      response = ResponseEntity.ok( demo );
    }
    else
    {
      response = new ResponseEntity<>( HttpStatus.NOT_FOUND );
    }

    return response;
  }

  @GetMapping(path = "/data/query")
  public List<Demo> getDemosByParam( @RequestParam(name = "id", required = false) Integer id,
      @RequestParam(name = "name", required = false) String name )
  {
    List<Demo> list = null;
    if( id != null )
    {
      list = this.data.stream().filter( d -> d.getId() == id ).collect( Collectors.toList() );
    }
    else if( name != null )
    {
      list = this.data.stream().filter( d -> d.getName().equalsIgnoreCase( name ) ).collect( Collectors.toList() );
    }
    else
    {
      list = getDemos();
    }
    return list;
  }

  @PostMapping(path = "/data", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Message> createDemo( @RequestBody Demo demo )
  {
    this.data.add( demo );
    Message message = new Message( "Creado" );
    MultiValueMap<String, String> headers = new HttpHeaders();
    String path = String.format( this.location, demo.getId() );
    headers.add( HttpHeaders.LOCATION, path );
    return new ResponseEntity<>( message, headers, HttpStatus.CREATED );
  }

  @PutMapping(path = "/data/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Message> udpateDemo( @PathVariable(name = "id", required = true) int id,
      @RequestBody Demo update )
  {

    Demo demo = this.data.stream().filter( d -> d.getId() == id ).findFirst().orElse( null );
    ResponseEntity<Message> response;
    if( demo != null )
    {
      demo.setName( update.getName() );

      Message message = new Message( "Actualizado" );
      response = ResponseEntity.ok( message );
    }
    else
    {
      Message message = new Message( "No existe" );
      response = new ResponseEntity<>( message, HttpStatus.NOT_FOUND );
    }

    return response;
  }

  @DeleteMapping(path = "/data/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Message> deleteDemo( @PathVariable(name = "id", required = true) int id )
  {

    Demo demo = this.data.stream().filter( d -> d.getId() == id ).findFirst().orElse( null );
    ResponseEntity<Message> response;
    if( demo != null )
    {
      this.data.remove( demo );

      Message message = new Message( "Eliminado" );
      response = ResponseEntity.ok( message );
    }
    else
    {
      Message message = new Message( "No existe" );
      response = new ResponseEntity<>( message, HttpStatus.NOT_FOUND );
    }

    return response;
  }

  public static class Message
  {
    private String message;

    public Message()
    {
      super();
    }

    public Message( String message )
    {
      super();
      this.message = message;
    }

    public String getMessage()
    {
      return message;
    }

    public void setMessage( String message )
    {
      this.message = message;
    }

  }
}
