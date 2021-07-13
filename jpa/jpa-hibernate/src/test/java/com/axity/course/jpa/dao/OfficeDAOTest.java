package com.axity.course.jpa.dao;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.axity.course.jpa.dao.impl.OfficeDAOImpl;
import com.axity.course.jpa.model.OfficeDO;

public class OfficeDAOTest
{
  private static final Logger LOG = LoggerFactory.getLogger( OfficeDAOTest.class );
  private OfficeDAO officeDAO;

  @Before
  public void setUp()
  {
    officeDAO = new OfficeDAOImpl();
  }

  @Test
  public void testCreate()
  {
    OfficeDO entity = new OfficeDO();
    entity.setOfficeCode( "11" );
    entity.setCity( "CDMX" );
    entity.setPhone( "52 56581111" );
    entity.setAddressLine1( "Mi calle #1" );
    entity.setState( "CDMX" );
    entity.setCountry( "México" );
    entity.setPostalCode( "11200" );
    entity.setTerritory( "LATAM" );

    officeDAO.create( entity );
  }

  @Test
  public void testEdit()
  {
    OfficeDO office = this.officeDAO.find( "10" );
    office.setAddressLine1( "Mi nueva dirección" );
    this.officeDAO.edit( office );
  }

  @Test
  public void testRemove()
  {
    OfficeDO entity = new OfficeDO();
    entity.setOfficeCode( "9999" );
    entity.setCity( "CDMX" );
    entity.setPhone( "52 56581111" );
    entity.setAddressLine1( "Mi calle #1" );
    entity.setState( "CDMX" );
    entity.setCountry( "México" );
    entity.setPostalCode( "11200" );
    entity.setTerritory( "LATAM" );

    officeDAO.create( entity );
    officeDAO.remove( entity );
  }

  @Test
  public void testFind()
  {
    OfficeDO office = this.officeDAO.find( "1" );
    assertNotNull( office );
    LOG.info( "Oficina: {}", office );

  }

  @Test
  public void testFindAll()
  {
    List<OfficeDO> offices = this.officeDAO.findAll();
    assertNotNull( offices );
    assertFalse( offices.isEmpty() );
    offices.stream().forEach( o -> LOG.info( "Oficina: {}", o ) );
  }

  @Test
  public void testCount()
  {
    long n = this.officeDAO.count();
    assertTrue( n > 0 );
    LOG.info( "Registros: {}", n );
  }
  
  
  @Test
  public void testFindByCountry()
  {
    List<OfficeDO> offices = this.officeDAO.findByCountry( "USA" );
    assertNotNull( offices );
    assertFalse( offices.isEmpty() );
    offices.stream().forEach( o -> LOG.info( "Oficina: {}", o ) );
  }
  
  @Test
  public void testFindByTerritory()
  {
    List<OfficeDO> offices = this.officeDAO.findByTerritory( "EMEA" );
    assertNotNull( offices );
    assertFalse( offices.isEmpty() );
    offices.stream().forEach( o -> LOG.info( "Oficina: {}", o ) );
  }

}
