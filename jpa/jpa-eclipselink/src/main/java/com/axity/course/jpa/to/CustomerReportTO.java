package com.axity.course.jpa.to;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class CustomerReportTO implements Serializable
{

  private static final long serialVersionUID = 8726512919897609729L;

  private Long customerNumber;

  private String customerName;

  private String contactLastName;

  private String contactFirstName;
  private String phone;
  private String country;

  private Long employeeNumber;

  private String employeeLastName;
  private String employeeFirstName;
  private String employeeEmail;
  private String employeeExtension;
  private String employeeJobTitle;

  private String employeeOfficeCode;
  private String employeeOfficeCity;
  private String employeeOfficePhone;
  private String employeeOfficeCountry;
  private String employeeOfficeTerritory;

  public Long getCustomerNumber()
  {
    return customerNumber;
  }

  public void setCustomerNumber( Long customerNumber )
  {
    this.customerNumber = customerNumber;
  }

  public String getCustomerName()
  {
    return customerName;
  }

  public void setCustomerName( String customerName )
  {
    this.customerName = customerName;
  }

  public String getContactLastName()
  {
    return contactLastName;
  }

  public void setContactLastName( String contactLastName )
  {
    this.contactLastName = contactLastName;
  }

  public String getContactFirstName()
  {
    return contactFirstName;
  }

  public void setContactFirstName( String contactFirstName )
  {
    this.contactFirstName = contactFirstName;
  }

  public String getPhone()
  {
    return phone;
  }

  public void setPhone( String phone )
  {
    this.phone = phone;
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

  public String getEmployeeLastName()
  {
    return employeeLastName;
  }

  public void setEmployeeLastName( String employeeLastName )
  {
    this.employeeLastName = employeeLastName;
  }

  public String getEmployeeFirstName()
  {
    return employeeFirstName;
  }

  public void setEmployeeFirstName( String employeeFirstName )
  {
    this.employeeFirstName = employeeFirstName;
  }

  public String getEmployeeEmail()
  {
    return employeeEmail;
  }

  public void setEmployeeEmail( String employeeEmail )
  {
    this.employeeEmail = employeeEmail;
  }

  public String getEmployeeExtension()
  {
    return employeeExtension;
  }

  public void setEmployeeExtension( String employeeExtension )
  {
    this.employeeExtension = employeeExtension;
  }

  public String getEmployeeJobTitle()
  {
    return employeeJobTitle;
  }

  public void setEmployeeJobTitle( String employeeJobTitle )
  {
    this.employeeJobTitle = employeeJobTitle;
  }

  public String getEmployeeOfficeCode()
  {
    return employeeOfficeCode;
  }

  public void setEmployeeOfficeCode( String employeeOfficeCode )
  {
    this.employeeOfficeCode = employeeOfficeCode;
  }

  public String getEmployeeOfficeCity()
  {
    return employeeOfficeCity;
  }

  public void setEmployeeOfficeCity( String employeeOfficeCity )
  {
    this.employeeOfficeCity = employeeOfficeCity;
  }

  public String getEmployeeOfficePhone()
  {
    return employeeOfficePhone;
  }

  public void setEmployeeOfficePhone( String employeeOfficePhone )
  {
    this.employeeOfficePhone = employeeOfficePhone;
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

  @Override
  public String toString()
  {
    return ToStringBuilder.reflectionToString( this );
  }
}
