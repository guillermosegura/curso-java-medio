package com.axity.demo.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Entidad de la tabla payments
 * 
 * @author guillermo.segura@axity.com
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "payments")
public class PaymentDO implements Serializable
{

  private static final long serialVersionUID = 2263012586989817489L;

  @EmbeddedId
  private PaymentId id;

  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name = "customernumber", referencedColumnName = "customernumber", insertable = false, updatable = false)
  private CustomerDO customer;

  @Column(name = "paymentdate", nullable = false)
  @Temporal(TemporalType.DATE)
  private Date paymentDate;

  @Column(name = "amount", precision = 10, scale = 2)
  private BigDecimal amount;

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
      PaymentDO that = (PaymentDO) object;

      isEquals = Objects.equals( this.id, that.id );
    }
    return isEquals;
  }

  @Override
  public int hashCode()
  {
    return Objects.hash( this.id );
  }

}
