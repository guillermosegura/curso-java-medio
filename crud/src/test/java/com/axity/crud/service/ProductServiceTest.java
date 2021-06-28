package com.axity.crud.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.List;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.axity.crud.to.BusinessException;
import com.axity.crud.to.ProductTO;

@RunWith(SpringRunner.class)
@SpringBootTest()
@Transactional
public class ProductServiceTest
{

  private static final Logger LOG = LoggerFactory.getLogger( ProductServiceTest.class );

  @Autowired
  private ProductService productService;

  @Test
  public void testFindAll()
  {
    List<ProductTO> products = productService.findAll();
    assertNotNull( products );
    assertFalse( products.isEmpty() );

    products.stream().forEach( p -> LOG.info( p.toString() ) );
  }

  @Test
  public void testFindId()
  {
    ProductTO product = productService.findId( 1L );
    assertNotNull( product );
  }

  @Test
  public void testFindByParamName()
  {
    String name = "Sauce";
    String sku = "";
    List<ProductTO> products = productService.findByParam( name, sku );

    assertNotNull( products );
    assertFalse( products.isEmpty() );

    products.stream().forEach( p -> LOG.info( p.toString() ) );
  }

  @Test
  public void testFindByParamSku()
  {
    String name = "";
    String sku = "6000809";
    List<ProductTO> products = productService.findByParam( name, sku );

    assertNotNull( products );
    assertFalse( products.isEmpty() );

    products.stream().forEach( p -> LOG.info( p.toString() ) );
  }

  @Test
  public void testFindByParamNameSku()
  {
    String name = "Beans";
    String sku = "6000809";
    List<ProductTO> products = productService.findByParam( name, sku );

    assertNotNull( products );
    assertFalse( products.isEmpty() );

    products.stream().forEach( p -> LOG.info( p.toString() ) );
  }

  @Test(expected = BusinessException.class)
  public void testFindByParamBusinessException()
  {
    String name = "";
    String sku = "";
    List<ProductTO> products = productService.findByParam( name, sku );

    assertNotNull( products );
    assertFalse( products.isEmpty() );

    products.stream().forEach( p -> LOG.info( p.toString() ) );
  }

  @Test
  public void testSave()
  {
    ProductTO product = new ProductTO();
    product.setSku( "999911111234" );
    product.setName( "Dummy product" );
    productService.save( product );

    assertNotNull( product.getId() );
    productService.delete( product.getId() );
  }

  @Test
  public void testEdit()
  {
    ProductTO product = productService.findId( 1L );
    String name = product.getName();
    String sku = product.getSku();
    assertNotNull( product );
    
    product.setName( "qwerty" );
    productService.edit( product );
    
    product.setName( name );
    product.setSku( sku );
    productService.edit( product );
  }
  
  
  @Test
  public void testEdit2()
  {
    ProductTO product = productService.findId( 1L );
    String name = product.getName();
    String sku = product.getSku();
    assertNotNull( product );
    
    product.setSku( "999900001111" );
    productService.edit( product );
    
    product.setName( name );
    product.setSku( sku );
    productService.edit( product );
  }

}
