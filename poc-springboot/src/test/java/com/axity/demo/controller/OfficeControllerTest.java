package com.axity.demo.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.axity.demo.exception.BusinessExcepcion;
import com.axity.demo.exception.BusinessExcepcionCode;
import com.axity.demo.services.OfficeService;
import com.axity.demo.to.Office;
import com.axity.demo.to.PaginatedResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class OfficeControllerTest
{

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private OfficeService officeService;

  @Test
  void testGetOffices() throws Exception
  {
    PaginatedResponse<Office> value = new PaginatedResponse<>();
    value.setPage( 0 );
    value.setPageSize( 20 );
    value.setPages( 1 );
    List<Office> response = new ArrayList<>();
    String[] officeCodes = { "1", "2", "3", "4", "5", "6", "7" };
    for( String officeCode : officeCodes )
    {
      Office office = new Office();
      office.setOfficeCode( officeCode );
      response.add( office );
    }
    value.setResponse( response );
    when( officeService.getOffices( 0, 20 ) ).thenReturn( value );

    MvcResult result = mockMvc.perform( MockMvcRequestBuilders.get( "/api/v1/offices" ) ).andExpect( status().isOk() )
        .andExpect( jsonPath( "$.body.page" ).value( "0" ) )
        .andExpect( jsonPath( "$.body.pageSize" ).value( "20" ) )
        .andExpect( jsonPath( "$.body.response" ).isArray() )
        .andExpect( jsonPath( "$.body.response[0].officeCode" ).value( "1" ) ).andReturn();

    assertNotNull( result );

  }

  @Test
  void testGetOffices_page_1_and_PageSize_5() throws Exception
  {
    PaginatedResponse<Office> value = new PaginatedResponse<>();
    value.setPage( 1 );
    value.setPageSize( 5 );
    value.setPages( 2 );
    List<Office> response = new ArrayList<>();
    String[] officeCodes = { "6", "7" };
    for( String officeCode : officeCodes )
    {
      Office office = new Office();
      office.setOfficeCode( officeCode );
      response.add( office );
    }
    value.setResponse( response );
    when( officeService.getOffices( 1, 5 ) ).thenReturn( value );

    MvcResult result = mockMvc.perform( MockMvcRequestBuilders.get( "/api/v1/offices?page=1&pageSize=5" ) )
        .andExpect( status().isOk() )
        .andExpect( jsonPath( "$.body.page" ).value( "1" ) )
        .andExpect( jsonPath( "$.body.pageSize" ).value( "5" ) )
        .andExpect( jsonPath( "$.body.response" ).isArray() )
        .andExpect( jsonPath( "$.body.response[0].officeCode" ).value( "6" ) ).andReturn();

    assertNotNull( result );

  }

  @Test
  void testGetOffice() throws Exception
  {

    Office office = new Office();
    office.setOfficeCode( "1" );
    when( officeService.getOffice( "1" ) ).thenReturn( office );

    MvcResult result = mockMvc.perform( MockMvcRequestBuilders.get( "/api/v1/offices/1" ) ).andExpect( status().isOk() )
        .andExpect( jsonPath( "$.body.officeCode" ).value( "1" ) ).andReturn();

    assertNotNull( result );

  }
  
  @Test
  void testGetOffice_notFound() throws Exception
  {

    BusinessExcepcion be = new BusinessExcepcion( "No se encontr\u00F3 la oficina" );
    be.setCode( BusinessExcepcionCode.OFFICE_NOT_FOUND );
    
    when( officeService.getOffice( "9999" ) ).thenThrow( be );

    MvcResult result = mockMvc.perform( MockMvcRequestBuilders.get( "/api/v1/offices/9999" ) )
        .andExpect( status().isNoContent())
        .andReturn();

    assertNotNull( result );

  }

}
