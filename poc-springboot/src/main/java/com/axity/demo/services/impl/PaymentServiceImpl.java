package com.axity.demo.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.axity.demo.repository.PaymentRepository;
import com.axity.demo.repository.projection.PaymentReport;
import com.axity.demo.services.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService
{

  @Autowired
  private PaymentRepository paymentRepository;

  @Override
  public List<PaymentReport> getPaymentsByCustomerNumber( Long customerNumber )
  {
    return paymentRepository.findPaymentsByCustomerNumber( customerNumber );
  }

}
