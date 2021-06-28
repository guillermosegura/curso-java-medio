package com.axity.crud.to;

import java.util.Objects;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * The Class ProductTO.
 */
public class ProductTO extends CatalogTO implements Comparable<ProductTO>
{

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1546484031731266743L;
  
  /** The sku. */
  private String sku;

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
   * To string.
   *
   * @return the string
   */
  @Override
  public String toString()
  {
    return new ToStringBuilder( this ).append( "id", this.id ).append( "sku", this.sku ).append( "name", this.name )
        .toString();
  }

  /**
   * Compare to.
   *
   * @param that the that
   * @return the int
   */
  @Override
  public int compareTo( ProductTO that )
  {
    return new CompareToBuilder().append( this.sku, that.sku ).toComparison();
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
      final ProductTO that = (ProductTO) object;
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
