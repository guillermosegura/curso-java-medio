package com.axity.demo.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Entidad de la tabla employees
 * 
 * @author guillermo.segura@axity.com
 */

@Getter
@Setter
@ToString(exclude = { "employees", "customers" })
@Entity
@Table(name = "employees")
public class EmployeeDO implements Serializable
{

  private static final long serialVersionUID = 8294455790159202317L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "employeenumber")
  private Long employeeNumber;

  @Column(name = "lastname")
  private String lastName;

  @Column(name = "firstname")
  private String firstName;

  @Column(name = "extension")
  private String extension;

  @Column(name = "email")
  private String email;

  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name = "officecode", referencedColumnName = "officecode")
  private OfficeDO office;

  @ManyToOne(fetch = FetchType.EAGER, optional = true)
  @JoinColumn(name = "reportsto", referencedColumnName = "employeenumber")
  private EmployeeDO reportsTo;

  @OneToMany(mappedBy = "reportsTo", fetch = FetchType.LAZY)
  private List<EmployeeDO> employees;

  @Column(name = "jobtitle")
  private String jobTitle;

  @OneToMany(mappedBy = "salesRepEmployee", fetch = FetchType.LAZY)
  private List<CustomerDO> customers;

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
      EmployeeDO that = (EmployeeDO) object;

      isEquals = Objects.equals( this.employeeNumber, that.employeeNumber );
    }
    return isEquals;
  }

  @Override
  public int hashCode()
  {
    return Objects.hash( this.employeeNumber );
  }

}
