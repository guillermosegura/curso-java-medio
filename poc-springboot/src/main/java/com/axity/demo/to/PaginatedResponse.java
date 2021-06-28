package com.axity.demo.to;

import java.io.Serializable;
import java.util.List;

import lombok.Setter;
import lombok.ToString;
import lombok.Getter;

/**
 * Objeto de transferencia para las consultas paginadas
 * 
 * @author guillermo.segura@axity.com
 * @param <T>
 */
@Getter
@Setter
@ToString
public class PaginatedResponse<T extends Serializable> implements Serializable
{

  private static final long serialVersionUID = -6578796470167164711L;
  private int page;
  private int pageSize;
  private int pages;
  private long totalElements;
  private List<T> response;

}
