package com.axity.demo.repository.projection;

import java.math.BigDecimal;
import java.util.Date;

public interface PaymentReport
{
  Long getCustomerNumber();

  String getCustomerName();

  String getContactLastName();

  String getContactFirstName();

  String getCustomerCountry();

  String getSalesRepLastName();

  String getSalesRepFirstName();

  String getOfficeCode();

  String getOfficeCity();

  String getOfficeCountry();

  String getCheckNumber();

  Date PaymentDate();

  BigDecimal getAmount();

}
