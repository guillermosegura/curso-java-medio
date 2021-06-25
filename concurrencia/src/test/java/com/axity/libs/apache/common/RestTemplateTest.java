package com.axity.libs.apache.common;

import java.util.Iterator;

import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
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
        log.info( "{}", office );
      }
    }

  }

  @Test
  public void testExchangePOST()
  {
    RestTemplate restTemplate = new RestTemplate();

    String url = "http://localhost:8080/poc/api/office";
    HttpMethod method = HttpMethod.POST;

    Office office = new Office();
    office.setCity( "CDMX" );
    office.setCountry( "Mexico" );
    office.setState( "CDMX" );
    office.setAddressLine1( "addressline1" );
    office.setAddressLine2( "addressline2" );
    office.setOfficeCode( "8" );
    office.setPhone( "5555" );
    office.setPostalCode( "01500" );
    office.setTerritory( "LATAM" );

    MultiValueMap<String, String> headers = new HttpHeaders();
    headers.add( HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE );
    HttpEntity<Office> requestEntity = new HttpEntity<Office>( office, headers );
    ResponseEntity<String> response = restTemplate.exchange( url, method, requestEntity, String.class );

    log.info( "{}", response.getStatusCode() );

    if( response.hasBody() )
    {
      String json = response.getBody();
      log.info( json );
    }
  }

}
