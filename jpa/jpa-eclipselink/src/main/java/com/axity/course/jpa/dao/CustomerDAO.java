package com.axity.course.jpa.dao;

import java.util.List;

import com.axity.course.jpa.model.CustomerDO;
import com.axity.course.jpa.to.CustomerReportParamTO;
import com.axity.course.jpa.to.CustomerReportTO;

public interface CustomerDAO extends BaseDAO<CustomerDO, Long>
{

  /**
   * Obtiene los clientes por país
   * 
   * @param country
   * @return
   */
  List<CustomerDO> findCustomersByCountry( String country );

  /**
   * Obtiene los clientes por representante de ventas
   * 
   * @param employeeNumber
   * @return
   */
  List<CustomerDO> findCustomersByEmployeeNumber( Long employeeNumber );

  /**
   * @return
   */
  List<CustomerReportTO> findCustomerReportAll();

  List<CustomerReportTO> findCustomerReportAllPaginated( int page, int size );

  List<CustomerReportTO> findCustomerReportByParam( CustomerReportParamTO param );
}
