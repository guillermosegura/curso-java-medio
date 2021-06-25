package com.axity.libs.apache.common;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Office implements Serializable
{

  private static final long serialVersionUID = 7495064891778035562L;

  @JsonProperty("officeId")
  @JsonAlias("officeCode")
  private String officeCode;
  private String city;
  private String phone;
  private String addressLine1;
  private String addressLine2;
  private String state;
  private String country;
  @JsonProperty("zip")
  @JsonAlias("postalCode")
  private String postalCode;
  private String territory;

  public Office()
  {
  }

}
