package com.axity.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.axity.demo.DemoController.Message;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class DemoIntegration
{
  @Value("${demo.app.location}")
  private String location;

  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  void testGetDemos()
  {
    ResponseEntity<List<Demo>> responseEntity = restTemplate.exchange( "/api/data", HttpMethod.GET, null,
      new ListDemo() );

    assertNotNull( responseEntity );
    assertEquals( HttpStatus.OK, responseEntity.getStatusCode() );
    assertNotNull( responseEntity.getBody() );
    assertFalse( responseEntity.getBody().isEmpty() );
    assertEquals( 1, responseEntity.getBody().get( 0 ).getId() );
    assertEquals( 2, responseEntity.getBody().get( 1 ).getId() );
    assertEquals( 3, responseEntity.getBody().get( 2 ).getId() );
  }

  @Test
  void testGetDemoById()
  {
    ResponseEntity<Demo> responseEntity = restTemplate.exchange( "/api/data/1", HttpMethod.GET, null, Demo.class );

    assertNotNull( responseEntity );
    assertEquals( HttpStatus.OK, responseEntity.getStatusCode() );
    assertNotNull( responseEntity.getBody() );
    assertEquals( 1, responseEntity.getBody().getId() );
  }

  @Test
  void testGetDemoById_notFound()
  {
    ResponseEntity<Demo> responseEntity = restTemplate.exchange( "/api/data/99999", HttpMethod.GET, null, Demo.class );

    assertNotNull( responseEntity );
    assertEquals( HttpStatus.NOT_FOUND, responseEntity.getStatusCode() );
    assertNull( responseEntity.getBody() );
  }

  @Test
  void getDemosByParam_empty()
  {
    ResponseEntity<List<Demo>> responseEntity = restTemplate.exchange( "/api/data/query", HttpMethod.GET, null,
      new ListDemo() );

    assertNotNull( responseEntity );
    assertEquals( HttpStatus.OK, responseEntity.getStatusCode() );
    assertNotNull( responseEntity.getBody() );
    assertFalse( responseEntity.getBody().isEmpty() );
    assertEquals( 1, responseEntity.getBody().get( 0 ).getId() );
    assertEquals( 2, responseEntity.getBody().get( 1 ).getId() );
    assertEquals( 3, responseEntity.getBody().get( 2 ).getId() );
  }

  @Test
  void getDemosByParam_id()
  {
    ResponseEntity<List<Demo>> responseEntity = restTemplate.exchange( "/api/data/query?id=1", HttpMethod.GET, null,
      new ListDemo() );

    assertNotNull( responseEntity );
    assertEquals( HttpStatus.OK, responseEntity.getStatusCode() );
    assertNotNull( responseEntity.getBody() );
    assertFalse( responseEntity.getBody().isEmpty() );
    assertEquals( 1, responseEntity.getBody().get( 0 ).getId() );
  }

  @Test
  void getDemosByParam_name()
  {
    ResponseEntity<List<Demo>> responseEntity = restTemplate.exchange( "/api/data/query?name=uno", HttpMethod.GET, null,
      new ListDemo() );

    assertNotNull( responseEntity );
    assertEquals( HttpStatus.OK, responseEntity.getStatusCode() );
    assertNotNull( responseEntity.getBody() );
    assertFalse( responseEntity.getBody().isEmpty() );
    assertEquals( 1, responseEntity.getBody().get( 0 ).getId() );
  }

  @Test
  void testCreateDemo()
  {

    HttpEntity<Demo> entity = new HttpEntity<Demo>( new Demo( 4, "cuatro" ) );
    ResponseEntity<Message> responseEntity = restTemplate.exchange( "/api/data", HttpMethod.POST, entity,
      Message.class );

    assertNotNull( responseEntity );
    assertEquals( HttpStatus.CREATED, responseEntity.getStatusCode() );
    assertNotNull( responseEntity.getHeaders().get( HttpHeaders.LOCATION ) );

    log.info( "{}", responseEntity.getHeaders().get( HttpHeaders.LOCATION ) );
    assertEquals(  "["+String.format( this.location, 4 )+"]", responseEntity.getHeaders().get( HttpHeaders.LOCATION ).toString() );
  }

  private static class ListDemo extends ParameterizedTypeReference<List<Demo>>
  {
  }
}
