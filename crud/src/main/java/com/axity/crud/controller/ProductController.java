package com.axity.crud.controller;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.axity.crud.service.ProductService;
import com.axity.crud.to.ProductTO;

/**
 * The Class ProductController.
 */
@RestController
@RequestMapping("/api/v1")
@Intercept
public class ProductController
{

  /** The product service. */
  @Autowired
  private ProductService productService;

  /**
   * Find all.
   *
   * @return the response entity
   */
  @GetMapping("/products")
  public ResponseEntity<List<ProductTO>> findAll()
  {
    List<ProductTO> products = productService.findAll();
    ResponseEntity<List<ProductTO>> response;
    if( CollectionUtils.isEmpty( products ) )
    {
      response = new ResponseEntity<>( HttpStatus.NO_CONTENT );
    }
    else
    {
      response = new ResponseEntity<>( products, HttpStatus.OK );
    }

    return response;
  }

  /**
   * Gets the by id.
   *
   * @param id the id
   * @return the by id
   */
  @GetMapping("/products/{id}")
  public ResponseEntity<ProductTO> getById( @PathVariable(value = "id") Long id )
  {
    ProductTO product = productService.findId( id );
    ResponseEntity<ProductTO> response;
    if( product == null )
    {
      response = new ResponseEntity<>( HttpStatus.NO_CONTENT );
    }
    else
    {
      response = new ResponseEntity<>( product, HttpStatus.OK );
    }

    return response;
  }

  /**
   * Find by param.
   *
   * @param name the name
   * @param sku the sku
   * @return the response entity
   */
  @GetMapping("/products/query")
  public ResponseEntity<List<ProductTO>> findByParam( @RequestParam(name = "name", required = false) String name,
      @RequestParam(name = "sku", required = false) String sku )
  {
    List<ProductTO> products = productService.findByParam( name, sku );
    ResponseEntity<List<ProductTO>> response;
    if( CollectionUtils.isEmpty( products ) )
    {
      response = new ResponseEntity<>( HttpStatus.NO_CONTENT );
    }
    else
    {
      response = new ResponseEntity<>( products, HttpStatus.OK );
    }

    return response;
  }

  /**
   * Save.
   *
   * @param product the product
   * @return the response entity
   */
  @PostMapping("/products")
  public ResponseEntity<ProductTO> save( @RequestBody ProductTO product )
  {
    productService.save( product );
    return new ResponseEntity<>( product, HttpStatus.CREATED );
  }

  /**
   * Update.
   *
   * @param product the product
   * @param id the id
   * @return the response entity
   */
  @PutMapping("/products/{id}")
  public ResponseEntity<ProductTO> update( @RequestBody ProductTO product, @PathVariable(value = "id") Long id )
  {
    product.setId( id );
    productService.edit( product );
    return ResponseEntity.ok().body( product );
  }

  /**
   * Delete.
   *
   * @param id the id
   * @return the response entity
   */
  @DeleteMapping("/products/{id}")
  public ResponseEntity<Void> delete( @PathVariable(value = "id") Long id )
  {
    productService.delete( id );
    return new ResponseEntity<>( HttpStatus.OK );
  }

 
}
