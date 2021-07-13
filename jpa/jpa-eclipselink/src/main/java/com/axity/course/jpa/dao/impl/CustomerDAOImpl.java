package com.axity.course.jpa.dao.impl;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.axity.course.jpa.dao.CustomerDAO;
import com.axity.course.jpa.model.CustomerDO;
import com.axity.course.jpa.to.CustomerReportParamTO;
import com.axity.course.jpa.to.CustomerReportTO;

public class CustomerDAOImpl extends AbstractBaseDAOImpl<CustomerDO, Long> implements CustomerDAO
{

  /**
   * Constructor default
   */
  public CustomerDAOImpl()
  {
    super( CustomerDO.class );
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<CustomerDO> findCustomersByCountry( String country )
  {
    TypedQuery<CustomerDO> query = getEntityManager().createNamedQuery( "CustomerDO.findCustomersByCountry",
      CustomerDO.class );
    query.setParameter( "country", country );
    return query.getResultList();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<CustomerDO> findCustomersByEmployeeNumber( Long employeeNumber )
  {
    TypedQuery<CustomerDO> query = getEntityManager().createNamedQuery( "CustomerDO.findCustomersByEmployeeNumber",
      CustomerDO.class );
    query.setParameter( "employeeNumber", employeeNumber );
    return query.getResultList();
  }

  @Override
  @SuppressWarnings("unchecked")
  public List<CustomerReportTO> findCustomerReportAll()
  {
    Query query = getEntityManager().createNamedQuery( "CustomerDO.findCustomerReportAll" );
    List<Object[]> data = query.getResultList();
    return data.stream().map( transformToCustomerReportTO ).collect( Collectors.toList() );
  }

  private Function<Object[], CustomerReportTO> transformToCustomerReportTO = data -> {
    CustomerReportTO report = new CustomerReportTO();
    report.setCustomerNumber( (Long) data[0] );
    report.setCustomerName( (String) data[1] );
    report.setContactLastName( (String) data[2] );
    report.setContactFirstName( (String) data[3] );
    report.setPhone( (String) data[4] );
    report.setCountry( (String) data[5] );
    report.setEmployeeNumber( (Long) data[6] );
    report.setEmployeeLastName( (String) data[7] );
    report.setEmployeeFirstName( (String) data[8] );
    report.setEmployeeEmail( (String) data[9] );
    report.setEmployeeExtension( (String) data[10] );
    report.setEmployeeJobTitle( (String) data[11] );
    report.setEmployeeOfficeCode( (String) data[12] );
    report.setEmployeeOfficeCity( (String) data[13] );
    report.setEmployeeOfficePhone( (String) data[14] );
    report.setEmployeeOfficeCountry( (String) data[15] );
    report.setEmployeeOfficeTerritory( (String) data[16] );
    return report;
  };

  @Override
  public List<CustomerReportTO> findCustomerReportAllPaginated( int page, int size )
  {
    Query query = getEntityManager().createNamedQuery( "CustomerDO.findCustomerReportAll" );
    query.setFirstResult( page * size );
    query.setMaxResults( size );
    List<Object[]> data = query.getResultList();
    return data.stream().map( transformToCustomerReportTO ).collect( Collectors.toList() );
  }

  @Override
  @SuppressWarnings("unchecked")
  public List<CustomerReportTO> findCustomerReportByParam( CustomerReportParamTO param )
  {
    Query query = getEntityManager().createNamedQuery( "CustomerDO.findCustomerReportByParam" );
    String name = null;
    String country = null;
    Long employeeNumber = null;
    String employeeOfficeCountry = null;
    String employeeOfficeTerritory = null;

    if( param != null )
    {
      if( param.isPaginated() )
      {
        query.setFirstResult( param.getPage() * param.getSize() );
        query.setMaxResults( param.getSize() );
      }
      name = param.getName();
      country = param.getCountry();
      employeeNumber = param.getEmployeeNumber();
      employeeOfficeCountry = param.getEmployeeOfficeCountry();
      employeeOfficeTerritory = param.getEmployeeOfficeTerritory();
    }

    query.setParameter( "name", name );
    query.setParameter( "country", country );
    query.setParameter( "employeeNumber", employeeNumber );
    query.setParameter( "employeeOfficeCountry", employeeOfficeCountry );
    query.setParameter( "employeeOfficeTerritory", employeeOfficeTerritory );

    List<Object[]> data = query.getResultList();
    return data.stream().map( transformToCustomerReportTO ).collect( Collectors.toList() );
  }

}
