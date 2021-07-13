package com.axity.course.jpa.to;

public class CustomerReportParamTO extends PaginationTO
{

  private String name;
  private String country;
  private Long employeeNumber;
  private String employeeOfficeCountry;
  private String employeeOfficeTerritory;
  public String getName()
  {
    return name;
  }

  public void setName( String name )
  {
    this.name = name;
  }

  public String getCountry()
  {
    return country;
  }

  public void setCountry( String country )
  {
    this.country = country;
  }

  public Long getEmployeeNumber()
  {
    return employeeNumber;
  }

  public void setEmployeeNumber( Long employeeNumber )
  {
    this.employeeNumber = employeeNumber;
  }

  public String getEmployeeOfficeCountry()
  {
    return employeeOfficeCountry;
  }

  public void setEmployeeOfficeCountry( String employeeOfficeCountry )
  {
    this.employeeOfficeCountry = employeeOfficeCountry;
  }

  public String getEmployeeOfficeTerritory()
  {
    return employeeOfficeTerritory;
  }

  public void setEmployeeOfficeTerritory( String employeeOfficeTerritory )
  {
    this.employeeOfficeTerritory = employeeOfficeTerritory;
  }

}
