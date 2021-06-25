package com.axity.libs.apache.common;

import java.util.Iterator;

import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RestTemplateTest
{

  @Test
  public void test() throws JsonMappingException, JsonProcessingException
  {
    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity<String> response = restTemplate.getForEntity( "http://localhost:8080/poc/api/office", String.class );
    log.info( "{}", response.getStatusCode() );
    
    if( response.hasBody() )
    {
      String json = response.getBody();
      log.info( json );

      ObjectMapper mapper = new ObjectMapper();
      JsonNode node = mapper.readTree( json );

      JsonNode responseNode = node.get( "body" ).get( "response" );

      Iterator<JsonNode> it = responseNode.elements();

      while( it.hasNext() )
      {
        JsonNode officeNode = it.next();
        String jsonOffice = officeNode.toString();
        Office office = mapper.readValue( jsonOffice, Office.class );
        log.info("{}", office );
      }
    }

  }

}
