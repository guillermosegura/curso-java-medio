package com.axity.crud.model;

import java.io.Serializable;

/**
 * Clase abstracta base para la entidades
 * 
 * @author gsegura
 * @param <T>
 */
public abstract class AbstractEntity<T> implements Comparable<T>, Serializable
{

  private static final long serialVersionUID = -1924866952718617763L;

  /**
   * The entities must implement the method equals(Object):boolean
   * 
   * @param obj
   */
  public abstract boolean equals( Object object );

  /**
   * The entities must implement the method hashCode():int
   */
  public abstract int hashCode();
}
