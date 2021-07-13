package com.axity.course.jpa.dao;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.axity.course.jpa.dao.impl.CustomerDAOImpl;
import com.axity.course.jpa.model.CustomerDO;
import com.axity.course.jpa.to.CustomerReportParamTO;
import com.axity.course.jpa.to.CustomerReportTO;

public class CustomerDAOTest
{
  private static final Logger LOG = LoggerFactory.getLogger( CustomerDAOTest.class );

  private CustomerDAO customerDAO;

  @Before
  public void setUp()
  {
    customerDAO = new CustomerDAOImpl();
  }

  @Test
  public void testFindCustomersByCountry()
  {
    List<CustomerDO> customers = this.customerDAO.findCustomersByCountry( "France" );
    assertNotNull( customers );
    assertFalse( customers.isEmpty() );
    customers.stream().forEach( c -> LOG.info( "{}", c ) );
  }

  @Test
  public void testFindCustomersByEmployeeNumber()
  {
    List<CustomerDO> customers = this.customerDAO.findCustomersByEmployeeNumber( 1370L );
    assertNotNull( customers );
    assertFalse( customers.isEmpty() );
    customers.stream().forEach( c -> LOG.info( "{}", c ) );
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
    CustomerDO customer = this.customerDAO.find( 103L );
    assertNotNull( customer );
    LOG.info( "{}", customer );
  }

  @Test
  public void testFindAll()
  {
    List<CustomerDO> customers = this.customerDAO.findAll();
    assertNotNull( customers );
    assertFalse( customers.isEmpty() );
    customers.stream().forEach( c -> LOG.info( "{}", c ) );
  }

  @Test
  public void testCount()
  {
    long n = this.customerDAO.count();
    assertTrue( n > 0 );
    LOG.info( "Registros: {}", n );
  }

  @Test
  public void testFindCustomerReportAll()
  {
    List<CustomerReportTO> customerReports = this.customerDAO.findCustomerReportAll();
    assertNotNull( customerReports );
    assertFalse( customerReports.isEmpty() );
    customerReports.stream().forEach( c -> LOG.info( "{}", c ) );
  }

  @Test
  public void testFindCustomerReportAllPaginated()
  {
    int page = 1;
    int size = 20;
    List<CustomerReportTO> customerReports = this.customerDAO.findCustomerReportAllPaginated( page, size );
    assertNotNull( customerReports );
    assertFalse( customerReports.isEmpty() );
    assertTrue( customerReports.size() <= size );
    customerReports.stream().forEach( c -> LOG.info( "{}", c ) );
  }

  @Test
  public void testFindCustomerReportByParam_null()
  {

    List<CustomerReportTO> customerReports = this.customerDAO.findCustomerReportByParam( null );
    assertNotNull( customerReports );
    assertFalse( customerReports.isEmpty() );

    customerReports.stream().forEach( c -> LOG.info( "{}", c ) );
  }

  @Test
  public void testFindCustomerReportByParam_default()
  {
    CustomerReportParamTO param = new CustomerReportParamTO();
    param.setPage( 0 );
    param.setSize( 10 );
    param.setPaginated( true );
    
    List<CustomerReportTO> customerReports = this.customerDAO.findCustomerReportByParam( param );
    assertNotNull( customerReports );
    assertFalse( customerReports.isEmpty() );
    assertTrue( customerReports.size() <= param.getSize() );
    customerReports.stream().forEach( c -> LOG.info( "{}", c ) );
  }
  
  @Test
  public void testFindCustomerReportByParam_byName()
  {
    CustomerReportParamTO param = new CustomerReportParamTO();
    param.setPage( 0 );
    param.setSize( 10 );
    param.setPaginated( true );
    param.setName( "mini" );
    
    List<CustomerReportTO> customerReports = this.customerDAO.findCustomerReportByParam( param );
    assertNotNull( customerReports );
    assertFalse( customerReports.isEmpty() );
    assertTrue( customerReports.size() <= param.getSize() );
    customerReports.stream().forEach( c -> LOG.info( "{}", c ) );
  }
  
  @Test
  public void testFindCustomerReportByParam_byCountry()
  {
    CustomerReportParamTO param = new CustomerReportParamTO();
    param.setPage( 0 );
    param.setSize( 10 );
    param.setPaginated( true );
    param.setCountry( "France" );
    
    List<CustomerReportTO> customerReports = this.customerDAO.findCustomerReportByParam( param );
    assertNotNull( customerReports );
    assertFalse( customerReports.isEmpty() );
    assertTrue( customerReports.size() <= param.getSize() );
    customerReports.stream().forEach( c -> LOG.info( "{}", c ) );
  }
  
  @Test
  public void testFindCustomerReportByParam_byEmployeeNumber()
  {
    CustomerReportParamTO param = new CustomerReportParamTO();
    param.setPage( 0 );
    param.setSize( 10 );
    param.setPaginated( true );
    param.setEmployeeNumber( 1370L );
    
    List<CustomerReportTO> customerReports = this.customerDAO.findCustomerReportByParam( param );
    assertNotNull( customerReports );
    assertFalse( customerReports.isEmpty() );
    assertTrue( customerReports.size() <= param.getSize() );
    customerReports.stream().forEach( c -> LOG.info( "{}", c ) );
  }
  
  @Test
  public void testFindCustomerReportByParam_byOfficeCountry()
  {
    CustomerReportParamTO param = new CustomerReportParamTO();
    param.setPage( 0 );
    param.setSize( 10 );
    param.setPaginated( true );
    param.setEmployeeOfficeCountry( "Japan" );
    
    List<CustomerReportTO> customerReports = this.customerDAO.findCustomerReportByParam( param );
    assertNotNull( customerReports );
    assertFalse( customerReports.isEmpty() );
    assertTrue( customerReports.size() <= param.getSize() );
    customerReports.stream().forEach( c -> LOG.info( "{}", c ) );
  }
  
  @Test
  public void testFindCustomerReportByParam_byOfficeTerritory()
  {
    CustomerReportParamTO param = new CustomerReportParamTO();
    param.setPage( 0 );
    param.setSize( 10 );
    param.setPaginated( true );
    param.setEmployeeOfficeTerritory( "EMEA" );
    
    List<CustomerReportTO> customerReports = this.customerDAO.findCustomerReportByParam( param );
    assertNotNull( customerReports );
    assertFalse( customerReports.isEmpty() );
    assertTrue( customerReports.size() <= param.getSize() );
    customerReports.stream().forEach( c -> LOG.info( "{}", c ) );
  }
  
  @Test
  public void testFindCustomerReportByParam_many()
  {
    CustomerReportParamTO param = new CustomerReportParamTO();
    param.setPage( 0 );
    param.setSize( 10 );
    param.setPaginated( true );
    param.setEmployeeOfficeTerritory( "EMEA" );
    param.setEmployeeOfficeCountry( "France" );
    param.setCountry( "France" );
    param.setName( "Mini" );
    
    List<CustomerReportTO> customerReports = this.customerDAO.findCustomerReportByParam( param );
    assertNotNull( customerReports );
    assertFalse( customerReports.isEmpty() );
    assertTrue( customerReports.size() <= param.getSize() );
    customerReports.stream().forEach( c -> LOG.info( "{}", c ) );
  }

}
