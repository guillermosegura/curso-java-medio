package com.axity.crud.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.CompareToBuilder;

/**
 * The Class ProductDO.
 */
@Entity
@Table(name = "K_PRODUCT")
public class ProductDO extends AbstractEntity<ProductDO>
{

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = -4980316117501330598L;

  /** The id. */
  @Id
  @Column(name = "ID_PRODUCT")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /** The sku. */
  @Column(name = "DS_SKU", unique = true, length = 20, nullable = false)
  private String sku;

  /** The name. */
  @Column(name = "DS_NAME", length = 100, nullable = false)
  private String name;

  /**
   * Gets the id.
   *
   * @return the id
   */
  public Long getId()
  {
    return id;
  }

  /**
   * Sets the id.
   *
   * @param id the new id
   */
  public void setId( Long id )
  {
    this.id = id;
  }

  /**
   * Gets the sku.
   *
   * @return the sku
   */
  public String getSku()
  {
    return sku;
  }

  /**
   * Sets the sku.
   *
   * @param sku the new sku
   */
  public void setSku( String sku )
  {
    this.sku = sku;
  }

  /**
   * Gets the name.
   *
   * @return the name
   */
  public String getName()
  {
    return name;
  }

  /**
   * Sets the name.
   *
   * @param name the new name
   */
  public void setName( String name )
  {
    this.name = name;
  }

  /**
   * Compare to.
   *
   * @param o the o
   * @return the int
   */
  @Override
  public int compareTo( ProductDO o )
  {
    return new CompareToBuilder().append( this.sku, o.sku ).toComparison();
  }

  /**
   * Equals.
   *
   * @param object the object
   * @return true, if successful
   */
  @Override
  public boolean equals( Object object )
  {
    boolean isEquals;
    if( this == object )
    {
      isEquals = true;
    }
    else if( object != null && object.getClass().equals( this.getClass() ) )
    {
      final ProductDO that = (ProductDO) object;
      isEquals = Objects.equals( this.sku, that.sku );
    }
    else
    {
      isEquals = false;
    }

    return isEquals;
  }

  /**
   * Hash code.
   *
   * @return the int
   */
  @Override
  public int hashCode()
  {
    return Objects.hash( this.sku );
  }

}
