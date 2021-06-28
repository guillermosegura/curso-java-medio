package com.axity.crud.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.axity.crud.model.ProductDO;

/**
 * The Interface ProductPersistence.
 */
public interface ProductPersistence extends JpaRepository<ProductDO, Long>
{

  /**
   * Gets the Product by sku.
   *
   * @param sku the sku
   * @return the by sku
   */
  @Query("SELECT o FROM ProductDO o WHERE o.sku = :sku")
  Optional<ProductDO> getBySku( @Param("sku") String sku );

  /**
   * Find Products by sku.
   *
   * @param sku the sku
   * @return the list
   */
  @Query("SELECT o FROM ProductDO o WHERE o.sku LIKE %:sku%")
  List<ProductDO> findBySku( @Param("sku") String sku );

  /**
   * Find Products by name.
   *
   * @param name the name
   * @return the list
   */
  @Query("SELECT o FROM ProductDO o WHERE UPPER(o.name) LIKE UPPER(CONCAT('%', :name, '%'))")
  List<ProductDO> findByName( @Param("name") String name );

  /**
   * Find Products by sku and name.
   *
   * @param sku the sku
   * @param name the name
   * @return the list
   */
  @Query("SELECT o FROM ProductDO o WHERE  o.sku LIKE %:sku% AND UPPER(o.name) LIKE UPPER(CONCAT('%', :name, '%'))")
  List<ProductDO> findBySkuAndName( @Param("sku") String sku, @Param("name") String name );
}
