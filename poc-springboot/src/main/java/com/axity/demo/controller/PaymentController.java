package com.axity.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.axity.demo.aop.JsonResponseIntercept;
import com.axity.demo.repository.projection.PaymentReport;
import com.axity.demo.services.PaymentService;

@RestController
@RequestMapping(path = "/api/v1")
@JsonResponseIntercept
public class PaymentController
{

  @Autowired
  private PaymentService paymentService;

  @GetMapping(path = "/payments/{customerNumber}")
  public ResponseEntity<List<PaymentReport>> getPaymentsByCustomerNumber(
      @PathVariable(name = "customerNumber", required = true) Long customerNumber )
  {
    List<PaymentReport> body = paymentService.getPaymentsByCustomerNumber( customerNumber );
    return ResponseEntity.ok( body );
  }
}
