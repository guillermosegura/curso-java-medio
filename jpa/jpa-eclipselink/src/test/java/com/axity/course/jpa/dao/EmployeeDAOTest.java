package com.axity.course.jpa.dao;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.axity.course.jpa.dao.impl.EmployeeDAOImpl;
import com.axity.course.jpa.model.EmployeeDO;

public class EmployeeDAOTest
{
  private static final Logger LOG = LoggerFactory.getLogger( EmployeeDAOTest.class );

  private EmployeeDAO employeeDAO;

  @Before
  public void setUp()
  {
    employeeDAO = new EmployeeDAOImpl();
  }

  @Test
  public void testFindEmployeesByOfficeCode()
  {
    List<EmployeeDO> employees = this.employeeDAO.findEmployeesByOfficeCode( "1" );
    assertNotNull( employees );
    assertFalse( employees.isEmpty() );
    employees.stream().forEach( e -> LOG.info( "{}", e ) );
  }

  @Test
  public void testFindEmployeesByOfficeCountry()
  {
    List<EmployeeDO> employees = this.employeeDAO.findEmployeesByOfficeCountry( "USA" );
    assertNotNull( employees );
    assertFalse( employees.isEmpty() );
    employees.stream().forEach( e -> LOG.info( "{}", e ) );
  }

  @Test
  public void testFindEmployeeByReportsTo()
  {
    List<EmployeeDO> employees = this.employeeDAO.findEmployeesByReportsTo( 1088L );
    assertNotNull( employees );
    assertFalse( employees.isEmpty() );
    employees.stream().forEach( e -> LOG.info( "{}", e ) );
  }

  @Test
  @Ignore
  public void testCreate()
  {
    fail( "Not yet implemented" );
  }

  @Test
  @Ignore
  public void testEdit()
  {
    fail( "Not yet implemented" );
  }

  @Test
  @Ignore
  public void testRemove()
  {
    fail( "Not yet implemented" );
  }

  @Test
  public void testFind()
  {
    fail( "Not yet implemented" );
  }

  @Test
  public void testFindAll()
  {
    fail( "Not yet implemented" );
  }

  @Test
  public void testCount()
  {
    fail( "Not yet implemented" );
  }

}
