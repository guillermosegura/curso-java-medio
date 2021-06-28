package com.axity.demo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.jdbc.Sql;

import com.axity.demo.model.OfficeDO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@DataJpaTest
@Sql(scripts = { "/data/schema_test.sql", "/data/data_test.sql" })
class OfficeRepositoryTest
{

  @Autowired
  private OfficeRepository officeRepository;

  @Test
  void testFindAllPageable()
  {
    int page = 0;
    int pageSize = 20;

    Page<OfficeDO> pagedResult = officeRepository.findAll( PageRequest.of( page, pageSize, Sort.by( "officeCode" ) ) );

    assertNotNull( pagedResult );
    assertEquals( 0, pagedResult.getNumber() );
    assertEquals( 7, pagedResult.getNumberOfElements() );
    assertEquals( 20, pagedResult.getSize() );
    assertEquals( 7, pagedResult.getTotalElements() );

    assertNotNull( pagedResult.getContent() );
    assertFalse( pagedResult.getContent().isEmpty() );

    for( OfficeDO office : pagedResult.getContent() )
    {
      log.info( "{}", office );
    }
  }

  @Test
  void testFindAllPageable_page_1_and_PageSize_5()
  {
    int page = 1;
    int pageSize = 5;

    Page<OfficeDO> pagedResult = officeRepository.findAll( PageRequest.of( page, pageSize, Sort.by( "officeCode" ) ) );

    assertNotNull( pagedResult );
    assertEquals( 1, pagedResult.getNumber() );
    assertEquals( 2, pagedResult.getNumberOfElements() );
    assertEquals( 5, pagedResult.getSize() );
    assertEquals( 7, pagedResult.getTotalElements() );

    assertNotNull( pagedResult.getContent() );
    assertFalse( pagedResult.getContent().isEmpty() );

    for( OfficeDO office : pagedResult.getContent() )
    {
      log.info( "{}", office );
    }
  }

  @Test
  void testFindById()
  {

    Optional<OfficeDO> op = officeRepository.findById( "1" );
    assertTrue( op.isPresent() );
    assertNotNull( op.get() );
    assertEquals( "1", op.get().getOfficeCode() );
    log.info( "{}", op.get() );
  }

  @Test
  void testFindById_notFound()
  {
    Optional<OfficeDO> op = officeRepository.findById( "9999" );
    assertFalse( op.isPresent() );
  }
}
