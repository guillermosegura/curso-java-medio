package com.axity.demo.services;

import com.axity.demo.to.Office;
import com.axity.demo.to.PaginatedResponse;

public interface OfficeService
{

  PaginatedResponse<Office> getOffices( int page, int pageSize );

  Office getOffice( String officeCode );

}
