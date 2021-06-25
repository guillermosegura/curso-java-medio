package com.axity.to;

import java.util.Objects;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Stock
{
  private String stockId;
  private String name;
  private int quantity;

  public Stock( String stockId, String name, int quantity )
  {
    this.stockId = stockId;
    this.name = name;
    this.quantity = quantity;
  }

  public String getStockId()
  {
    return stockId;
  }

  public void setStockId( String stockId )
  {
    this.stockId = stockId;
  }

  public int getQuantity()
  {
    return quantity;
  }

  public String getTicker()
  {
    return name;
  }

  @Override
  public boolean equals( Object object )
  {
    boolean isEquals = false;
    if( this == object )
    {
      isEquals = true;
    }
    else if( object != null && object.getClass().equals( this.getClass() ) )
    {
      Stock that = (Stock) object;

      isEquals = Objects.equals( this.stockId, that.stockId );
      isEquals &= Objects.equals( this.name, that.name );
      isEquals &= Objects.equals( this.quantity, that.quantity );
    }
    return isEquals;
  }

  @Override
  public int hashCode()
  {
    return Objects.hash( this.stockId, this.name, this.quantity );
  }

  @Override
  public String toString()
  {
    Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

    return gson.toJson( this );
  }
}
