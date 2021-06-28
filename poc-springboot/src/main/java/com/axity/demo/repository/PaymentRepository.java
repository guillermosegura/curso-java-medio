package com.axity.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.axity.demo.model.PaymentDO;
import com.axity.demo.repository.projection.PaymentReport;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentDO, String>
{
 
  @Query("SELECT c.customerNumber as customerNumber, c.customerName as customerName, "
      + " c.contactLastName as contactLastName, c.contactFirstName as contactFirstName, "
      + " c.country as customerCountry,"
      + " e.lastName as salesRepLastName, e.firstName as salesRepFirstName,"
      + " o.officeCode as officeCode, o.city as officeCity, o.country as officeCountry,"
      + " p.id.checkNumber as checkNumber, p.paymentDate, p.amount as amount "
      + " FROM PaymentDO p"
      + " INNER JOIN p.customer c"
      + " INNER JOIN c.salesRepEmployee e"
      + " INNER JOIN e.office o"
      + " WHERE c.customerNumber = :customerNumber")
  List<PaymentReport> findPaymentsByCustomerNumber( @Param("customerNumber") Long customerNumber );
}
