package com.axity.course.jpa.dao;

import java.util.List;

import com.axity.course.jpa.model.EmployeeDO;

public interface EmployeeDAO extends BaseDAO<EmployeeDO, Long>
{

  /**
   * Obtiene los emploeados por el código de la oficina
   * 
   * @param officeCode
   * @return
   */
  List<EmployeeDO> findEmployeesByOfficeCode( String officeCode );

  /**
   * Obtiene los empleados por el país de la oficina a la que están asociados
   * 
   * @param country
   * @return
   */
  List<EmployeeDO> findEmployeesByOfficeCountry( String country );

  /**
   * Obtiene los empleados que reportan
   * 
   * @param reportsTo
   * @return
   */
  List<EmployeeDO> findEmployeesByReportsTo( Long reportsTo );
}
