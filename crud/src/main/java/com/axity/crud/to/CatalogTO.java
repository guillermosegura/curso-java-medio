package com.axity.crud.to;

import java.io.Serializable;

/**
 * The Class CatalogTO.
 */
public class CatalogTO implements Serializable
{

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 4863718221506509821L;

  /** The id. */
  protected Long id;

  /** The name. */
  protected String name;

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

}
