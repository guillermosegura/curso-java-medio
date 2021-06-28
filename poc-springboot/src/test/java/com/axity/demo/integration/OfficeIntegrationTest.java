package com.axity.demo.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import com.axity.demo.exception.BusinessExcepcionCode;
import com.axity.demo.to.GenericResponse;
import com.axity.demo.to.Office;
import com.axity.demo.to.PaginatedResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Sql(scripts = { "/data/schema_test.sql", "/data/data_test.sql" })
class OfficeIntegrationTest
{

  @Autowired
  private TestRestTemplate template;

  @Test
  void testGetOffices()
  {
    ResponseEntity<GenericResponse<PaginatedResponse<Office>>> response = template.exchange( "/api/v1/offices",
      HttpMethod.GET, null, new GenericPageOffice() );

    assertNotNull( response );
    assertEquals( HttpStatus.OK, response.getStatusCode() );
    assertNotNull( response.getBody() );
    GenericResponse<PaginatedResponse<Office>> generic = response.getBody();

    assertEquals( "OK", generic.getHeader().getMessage() );
    assertEquals( "000", generic.getHeader().getStatus() );
    assertEquals( 0, generic.getBody().getPage() );
    assertEquals( 20, generic.getBody().getPageSize() );
    assertNotNull( generic.getBody().getResponse() );
    assertFalse( generic.getBody().getResponse().isEmpty() );

    for( Office office : generic.getBody().getResponse() )
    {
      log.info( "{}", office );
    }
  }

  @Test
  void testGetOffices_page_1_and_PagseSize_5()
  {

    ResponseEntity<GenericResponse<PaginatedResponse<Office>>> response = template
        .exchange( "/api/v1/offices?page=1&pageSize=5", HttpMethod.GET, null, new GenericPageOffice() );

    GenericResponse<PaginatedResponse<Office>> generic = response.getBody();

    assertEquals( "OK", generic.getHeader().getMessage() );
    assertEquals( "000", generic.getHeader().getStatus() );

    assertNotNull( response );
    assertEquals( HttpStatus.OK, response.getStatusCode() );
    assertNotNull( response.getBody() );
    assertEquals( 1, generic.getBody().getPage() );
    assertEquals( 5, generic.getBody().getPageSize() );
    assertNotNull( generic.getBody().getResponse() );
    assertFalse( generic.getBody().getResponse().isEmpty() );

    for( Office office : generic.getBody().getResponse() )
    {
      log.info( "{}", office );
    }
  }

  @Test
  void testGetOffice()
  {
    ResponseEntity<GenericResponse<Office>> response = template.exchange( "/api/v1/offices/1", HttpMethod.GET, null,
      new GenericOffice() );

    assertNotNull( response );
    assertEquals( HttpStatus.OK, response.getStatusCode() );

    GenericResponse<Office> generic = response.getBody();

    assertEquals( "OK", generic.getHeader().getMessage() );
    assertEquals( "000", generic.getHeader().getStatus() );

    assertNotNull( generic.getBody() );
    assertEquals( "1", generic.getBody().getOfficeCode() );
  }

  @Test
  void testGetOffice_notFound()
  {
    ResponseEntity<GenericResponse<Office>> response = template.exchange( "/api/v1/offices/9999", HttpMethod.GET, null,
      new GenericOffice() );

    System.out.println( response );
    assertNotNull( response );
    assertEquals( HttpStatus.NOT_FOUND, response.getStatusCode() );
    assertNotNull( response.getBody() );
    assertNotNull( response.getBody().getHeader() );
    assertEquals( BusinessExcepcionCode.OFFICE_NOT_FOUND.getCode(), response.getBody().getHeader().getStatus() );
  }

  private static class GenericPageOffice extends ParameterizedTypeReference<GenericResponse<PaginatedResponse<Office>>>
  {
  }

  private static class GenericOffice extends ParameterizedTypeReference<GenericResponse<Office>>
  {
  }
}
