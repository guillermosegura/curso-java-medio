package com.axity.demo.to;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class GenericResponse<T> implements Serializable
{

  private static final long serialVersionUID = 3366362528164181424L;
  private Header header;
  private T body;

}
