package com.axity.demo.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.axity.demo.exception.BusinessExcepcion;
import com.axity.demo.exception.BusinessExcepcionCode;
import com.axity.demo.model.OfficeDO;
import com.axity.demo.repository.OfficeRepository;
import com.axity.demo.to.Office;
import com.axity.demo.to.PaginatedResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class OfficeServiceTest
{

  @Autowired
  private OfficeService officeService;

  @MockBean
  private OfficeRepository officeRepository;

  @Test
  void testGetOffices()
  {

    List<OfficeDO> entities = new ArrayList<>();
    String[] officeCodes = { "1", "2", "3", "4", "5", "6", "7" };
    for( String officeCode : officeCodes )
    {
      OfficeDO office = new OfficeDO();
      office.setOfficeCode( officeCode );
      entities.add( office );
    }

    Page<OfficeDO> paged = mock( Page.class );
    when( paged.getNumber() ).thenReturn( 0 );
    when( paged.getNumberOfElements() ).thenReturn( 7 );
    when( paged.getContent() ).thenReturn( entities );
    when( paged.getSize() ).thenReturn( 20 );
    when( paged.getTotalPages() ).thenReturn( 1 );

    when( officeRepository.findAll( ArgumentMatchers.any( Pageable.class ) ) ).thenReturn( paged );

    PaginatedResponse<Office> value = officeService.getOffices( 0, 20 );

    assertNotNull( value );
    assertEquals( 0, value.getPage() );
    assertEquals( 1, value.getPages() );
    assertEquals( 20, value.getPageSize() );
    assertNotNull( value.getResponse() );
    assertFalse( value.getResponse().isEmpty() );

    for( int i = 0; i < officeCodes.length; i++ )
    {
      assertEquals( officeCodes[i], value.getResponse().get( i ).getOfficeCode() );
    }

  }

  @Test
  void testGetOffices_page_1_and_PageSize_5()
  {

    List<OfficeDO> entities = new ArrayList<>();
    String[] officeCodes = { "6", "7" };
    for( String officeCode : officeCodes )
    {
      OfficeDO office = new OfficeDO();
      office.setOfficeCode( officeCode );
      entities.add( office );
    }

    Page<OfficeDO> paged = mock( Page.class );
    when( paged.getNumber() ).thenReturn( 1 );
    when( paged.getNumberOfElements() ).thenReturn( 2 );
    when( paged.getContent() ).thenReturn( entities );
    when( paged.getSize() ).thenReturn( 5 );
    when( paged.getTotalPages() ).thenReturn( 2 );
    when( paged.getTotalElements() ).thenReturn( 7L );

    when( officeRepository.findAll( ArgumentMatchers.any( Pageable.class ) ) ).thenReturn( paged );

    PaginatedResponse<Office> value = officeService.getOffices( 1, 5 );

    assertNotNull( value );
    assertEquals( 1, value.getPage() );
    assertEquals( 2, value.getPages() );
    assertEquals( 5, value.getPageSize() );
    assertNotNull( value.getResponse() );
    assertFalse( value.getResponse().isEmpty() );

    for( int i = 0; i < officeCodes.length; i++ )
    {
      assertEquals( officeCodes[i], value.getResponse().get( i ).getOfficeCode() );
    }

  }

  @Test
  void testGetOffice()
  {

    OfficeDO office = new OfficeDO();
    office.setOfficeCode( "1" );

    Optional<OfficeDO> op = Optional.of( office );

    when( officeRepository.findById( "1" ) ).thenReturn( op );

    Office value = officeService.getOffice( "1" );

    assertNotNull( value );
    assertEquals( "1", value.getOfficeCode() );

  }

  @Test
  void testGetOffice_notFound()
  {

    Optional<OfficeDO> op = Optional.empty();

    when( officeRepository.findById( "9999" ) ).thenReturn( op );

    BusinessExcepcion be = assertThrows( BusinessExcepcion.class, () -> officeService.getOffice( "9999" ) );

    assertEquals( BusinessExcepcionCode.OFFICE_NOT_FOUND, be.getCode() );

  }
}
