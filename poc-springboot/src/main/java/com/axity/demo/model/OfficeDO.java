package com.axity.demo.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "offices")
public class OfficeDO implements Serializable
{

  private static final long serialVersionUID = 3715114113960266209L;

  @Id
  @Column(name = "officecode", length = 10)
  private String officeCode;

  @Column(name = "city", nullable = false, length = 50)
  private String city;

  @Column(name = "phone", nullable = false, length = 50)
  private String phone;

  @Column(name = "addressline1", nullable = false, length = 50)
  private String addressLine1;

  @Column(name = "addressline2", nullable = true, length = 50)
  private String addressLine2;

  @Column(name = "state", nullable = true, length = 50)
  private String state;

  @Column(name = "country", nullable = false, length = 50)
  private String country;

  @Column(name = "postalcode", nullable = false, length = 15)
  private String postalCode;

  @Column(name = "territory", nullable = false, length = 10)
  private String territory;
  
//  @OneToMany(mappedBy = "office", fetch = FetchType.LAZY)
//  private List<EmployeeDO> employees;

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
      OfficeDO that = (OfficeDO) object;

      isEquals = Objects.equals( this.officeCode, that.officeCode );
    }
    return isEquals;
  }

  @Override
  public int hashCode()
  {
    return Objects.hash( this.officeCode );
  }
}
