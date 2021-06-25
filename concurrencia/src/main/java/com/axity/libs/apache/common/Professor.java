package com.axity.libs.apache.common;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString(exclude = "employeeNumber")
public class Professor
{

  private String name;
  private String lastname;
  private String title;
  private Integer employeeNumber;
}
