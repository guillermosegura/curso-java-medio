package com.axity.demo.services;

import java.util.List;

import com.axity.demo.repository.projection.PaymentReport;

public interface PaymentService
{
  List<PaymentReport> getPaymentsByCustomerNumber( Long customerNumber );
}
