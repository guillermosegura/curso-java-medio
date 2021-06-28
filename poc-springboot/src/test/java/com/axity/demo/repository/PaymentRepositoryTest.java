package com.axity.demo.repository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import com.axity.demo.repository.projection.PaymentReport;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@DataJpaTest
@Sql(scripts = { "/data/schema_test.sql", "/data/data_test.sql" })
class PaymentRepositoryTest
{
  @Autowired
  private PaymentRepository paymentRepository;

  @Test
  void testFindPaymentsByCustomerNumber() throws JsonProcessingException
  {
    Long customerNumber = 103L;
    List<PaymentReport> report = this.paymentRepository.findPaymentsByCustomerNumber( customerNumber );

    assertNotNull( report );
    assertFalse( report.isEmpty() );

    for( PaymentReport p : report )
    {
      ObjectMapper o = new ObjectMapper();
      log.info( "{}", o.writeValueAsString( p ) );
      // log.info("{} {} {} {}", p.getCustomerNumber(), p.getCustomerName(), p.getContactFirstName(),
      // p.getContactLastName() );
    }

  }

}
