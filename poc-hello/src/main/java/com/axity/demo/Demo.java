package com.axity.demo;

public class Demo
{
  private int id;
  private String name;

  public Demo()
  {
    super();
  }

  public Demo( int id, String name )
  {
    super();
    this.id = id;
    this.name = name;
  }

  public int getId()
  {
    return id;
  }

  public void setId( int id )
  {
    this.id = id;
  }

  public String getName()
  {
    return name;
  }

  public void setName( String name )
  {
    this.name = name;
  }
}
