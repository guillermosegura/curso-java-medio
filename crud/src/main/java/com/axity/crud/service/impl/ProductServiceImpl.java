package com.axity.crud.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.axity.crud.model.ProductDO;
import com.axity.crud.persistence.ProductPersistence;
import com.axity.crud.service.ProductService;
import com.axity.crud.to.BusinessException;
import com.axity.crud.to.ProductTO;

/**
 * The Class ProductServiceImpl.
 */
@Service
public class ProductServiceImpl implements ProductService
{

  /** The supplier product not found. */
  private final Supplier<BusinessException> supplierProductNotFound = this::productNotFound;

  /** The product persistence. */
  @Autowired
  private ProductPersistence productPersistence;

  /**
   * Find all.
   *
   * @return the list
   */
  @Override
  public List<ProductTO> findAll()
  {
    List<ProductDO> products = productPersistence.findAll();

    List<ProductTO> list = products.stream().map( this::transform ).collect( Collectors.toList() );
    Collections.sort( list );

    return list;
  }

  /**
   * Transform.
   *
   * @param entity the entity
   * @return the product TO
   */
  private ProductTO transform( ProductDO entity )
  {
    ProductTO to = new ProductTO();
    to.setId( entity.getId() );
    to.setName( entity.getName() );
    to.setSku( entity.getSku() );
    return to;
  }

  /**
   * Find id.
   *
   * @param id the id
   * @return the product TO
   */
  @Override
  public ProductTO findId( Long id )
  {
    ProductDO p = this.productPersistence.findById( id ).orElseThrow( supplierProductNotFound );
    return transform( p );
  }

  /**
   * Product not found.
   *
   * @return the internal error exception
   */
  private BusinessException productNotFound()
  {
    BusinessException e = new BusinessException( "Product not found" );
    e.getError().setId( 1L );
    e.getError().setBadRequest( false );
    e.getError().setName( "Product not found" );
    e.getError().setDescription( "Product not found" );
    return e;
  }

  /**
   * Bad request.
   *
   * @param description the description
   * @return the internal error exception
   */
  private BusinessException badRequest( String description )
  {
    BusinessException e = new BusinessException( "Bad request" );
    e.getError().setId( 2L );
    e.getError().setBadRequest( true );
    e.getError().setName( "Bad request" );
    e.getError().setDescription( description );
    return e;
  }

  /**
   * Internal error request.
   *
   * @param description the description
   * @return the internal error exception
   */
  private BusinessException internalErrorRequest( String description )
  {
    BusinessException e = new BusinessException( "Internal error" );
    e.getError().setId( 3L );
    e.getError().setBadRequest( false );
    e.getError().setName( "Internal error" );
    e.getError().setDescription( description );
    return e;
  }

  /**
   * Find by param.
   *
   * @param name the name
   * @param sku the sku
   * @return the list
   */
  @Override
  public List<ProductTO> findByParam( String name, String sku )
  {

    List<ProductDO> products;
    if( StringUtils.isNoneBlank( name ) && StringUtils.isNoneBlank( sku ) )
    {
      products = this.productPersistence.findBySkuAndName( sku, name );
    }
    else if( StringUtils.isNoneBlank( name ) )
    {
      products = this.productPersistence.findByName( name );
    }
    else if( StringUtils.isNoneBlank( sku ) )
    {
      products = this.productPersistence.findBySku( sku );
    }
    else
    {
      throw badRequest( "At least name or sku is required" );
    }

    List<ProductTO> list = products.stream().map( this::transform ).collect( Collectors.toList() );
    Collections.sort( list );

    return list;
  }

  /**
   * Save.
   *
   * @param product the product
   */
  @Override
  public void save( ProductTO product )
  {

    validateName( product );
    validateSku( product );
    validateSkuNotAlreadyRegistered( product );

    ProductDO entity = new ProductDO();
    entity.setName( product.getName() );
    entity.setSku( product.getSku().trim() );
    entity = this.productPersistence.saveAndFlush( entity );

    product.setId( entity.getId() );
  }

  /**
   * Validate sku not already registered.
   *
   * @param product the product
   */
  private void validateSkuNotAlreadyRegistered( ProductTO product )
  {
    Optional<ProductDO> p = this.productPersistence.getBySku( product.getSku().trim() );
    if( p.isPresent() )
    {
      throw internalErrorRequest( "SKU already exists" );
    }
  }

  /**
   * Validate sku.
   *
   * @param product the product
   */
  private void validateSku( ProductTO product )
  {
    if( StringUtils.isBlank( product.getSku() ) )
    {
      throw badRequest( "SKU is required" );
    }
    else if( product.getSku().trim().length() > 20 )
    {
      throw badRequest( "SKU is longer than 20 characters" );
    }
  }

  /**
   * Validate name.
   *
   * @param product the product
   */
  private void validateName( ProductTO product )
  {
    if( StringUtils.isBlank( product.getName() ) )
    {
      throw badRequest( "Name is required" );
    }
    else if( product.getName().length() > 100 )
    {
      throw badRequest( "Name is longer than 100 characters" );
    }
  }

  /**
   * Edits the.
   *
   * @param product the product
   */
  @Override
  public void edit( ProductTO product )
  {
    validateName( product );
    validateSku( product );

    ProductDO entity = this.productPersistence.findById( product.getId() ).orElseThrow( supplierProductNotFound );

    if( !product.getSku().trim().equals( entity.getSku() ) )
    {
      // Cambio de SKU
      validateSkuNotAlreadyRegistered( product );
      entity.setSku( product.getSku().trim() );
    }

    entity.setName( product.getName() );
    this.productPersistence.save( entity );

  }

  /**
   * Delete.
   *
   * @param id the id
   */
  @Override
  public void delete( Long id )
  {
    ProductDO entity = this.productPersistence.findById( id ).orElseThrow( supplierProductNotFound );
    this.productPersistence.delete( entity );
  }

}
