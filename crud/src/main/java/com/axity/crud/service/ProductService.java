package com.axity.crud.service;

import java.util.List;

import com.axity.crud.to.ProductTO;

/**
 * The Interface ProductService.
 */
public interface ProductService
{

  /**
   * Find all.
   *
   * @return the list
   */
  List<ProductTO> findAll();

  /**
   * Find id.
   *
   * @param id the id
   * @return the product TO
   */
  ProductTO findId( Long id );

  /**
   * Find by param.
   *
   * @param name the name
   * @param sku the sku
   * @return the list
   */
  List<ProductTO> findByParam( String name, String sku );

  /**
   * Save.
   *
   * @param product the product
   */
  void save( ProductTO product );

  /**
   * Edits the.
   *
   * @param product the product
   */
  void edit( ProductTO product );

  /**
   * Delete.
   *
   * @param id the id
   */
  void delete( Long id );

}
