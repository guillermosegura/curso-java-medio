package com.axity.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.axity.demo.aop.JsonResponseIntercept;
import com.axity.demo.services.OfficeService;
import com.axity.demo.to.Office;
import com.axity.demo.to.PaginatedResponse;

@RestController
@RequestMapping(path = "/api/v1")
@JsonResponseIntercept
public class OfficeController
{
  @Autowired
  private OfficeService officeService;

  @GetMapping(path = "/offices")
  public ResponseEntity<PaginatedResponse<Office>> getOffices(
      @RequestParam(name = "page", required = false, defaultValue = "0") int page,
      @RequestParam(name = "pageSize", required = false, defaultValue = "20") int pageSize )
  {
    PaginatedResponse<Office> body = officeService.getOffices( page, pageSize );
    return ResponseEntity.ok( body );
  }

  @GetMapping(path = "/offices/{officeCode}")
  public ResponseEntity<Office> getOffice( @PathVariable(name = "officeCode") String officeCode )
  {
    return ResponseEntity.ok( officeService.getOffice( officeCode ) );
  }
}
