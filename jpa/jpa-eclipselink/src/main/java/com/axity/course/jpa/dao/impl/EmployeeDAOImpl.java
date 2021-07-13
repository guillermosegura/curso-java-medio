package com.axity.course.jpa.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import com.axity.course.jpa.dao.EmployeeDAO;
import com.axity.course.jpa.model.EmployeeDO;

public class EmployeeDAOImpl extends AbstractBaseDAOImpl<EmployeeDO, Long> implements EmployeeDAO
{

  /**
   * Constructor default
   */
  public EmployeeDAOImpl()
  {
    super( EmployeeDO.class );
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<EmployeeDO> findEmployeesByOfficeCode( String officeCode )
  {
    TypedQuery<EmployeeDO> query = getEntityManager().createNamedQuery( "EmployeeDO.findEmployeesByOfficeCode",
      EmployeeDO.class );
    query.setParameter( "officeCode", officeCode );
    return query.getResultList();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<EmployeeDO> findEmployeesByOfficeCountry( String country )
  {
    TypedQuery<EmployeeDO> query = getEntityManager().createNamedQuery( "EmployeeDO.findEmployeesByOfficeCountry",
      EmployeeDO.class );
    query.setParameter( "country", country );
    return query.getResultList();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<EmployeeDO> findEmployeesByReportsTo( Long reportsTo )
  {
    TypedQuery<EmployeeDO> query = getEntityManager().createNamedQuery( "EmployeeDO.findEmployeeByReportsTo",
      EmployeeDO.class );
    query.setParameter( "reportsTo", reportsTo );
    return query.getResultList();
  }

}
