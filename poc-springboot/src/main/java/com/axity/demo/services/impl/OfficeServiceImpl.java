package com.axity.demo.services.impl;

import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.axity.demo.exception.BusinessExcepcion;
import com.axity.demo.exception.BusinessExcepcionCode;
import com.axity.demo.model.OfficeDO;
import com.axity.demo.repository.OfficeRepository;
import com.axity.demo.services.OfficeService;
import com.axity.demo.to.Office;
import com.axity.demo.to.PaginatedResponse;

@Service
public class OfficeServiceImpl implements OfficeService
{

  @Autowired
  private OfficeRepository officeRepository;

  private Supplier<BusinessExcepcion> officeNotFound = () -> {
    BusinessExcepcion be = new BusinessExcepcion( "No se encontr\u00F3 la oficina" );
    be.setCode( BusinessExcepcionCode.OFFICE_NOT_FOUND );
    return be;
  };

  private Function<OfficeDO, Office> officeDOtransformer = entity -> {
    Office office = new Office();
    BeanUtils.copyProperties( entity, office );
    return office;
  };

  @Override
  public PaginatedResponse<Office> getOffices( int page, int pageSize )
  {

    PaginatedResponse<Office> offices = new PaginatedResponse<>();

    Page<OfficeDO> pagedResult = officeRepository.findAll( PageRequest.of( page, pageSize, Sort.by( "officeCode" ) ) );
    offices.setPage( pagedResult.getNumber() );
    offices.setPages( pagedResult.getTotalPages() );
    offices.setPageSize( pagedResult.getSize() );
    offices.setTotalElements( pagedResult.getTotalElements() );

    offices.setResponse( pagedResult.getContent().stream().map( officeDOtransformer ).collect( Collectors.toList() ) );

    return offices;
  }

  @Override
  public Office getOffice( String officeCode )
  {
    OfficeDO entity = this.officeRepository.findById( officeCode ).orElseThrow( officeNotFound );
    return officeDOtransformer.apply( entity );
  }

}
