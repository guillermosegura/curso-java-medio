package com.axity.course.jpa.dao;

import java.util.List;

/**
 * Interface base de DAOs
 * 
 * @author guillermo.segura@axity.com
 * @param <T>
 */
public interface BaseDAO<T, I>
{
  /**
   * Crea un registro
   * 
   * @param entity
   */
  void create( T entity );

  /**
   * Actualiza un registro
   * 
   * @param entity
   */
  void edit( T entity );

  /**
   * Elimina un registro
   * 
   * @param entity
   */
  void remove( T entity );

  /**
   * Busca un registro por su id
   * 
   * @param i
   * @return
   */
  T find( I i );

  /**
   * Busca todos los registros
   * 
   * @return
   */
  List<T> findAll();

  /**
   * Cuenta todos los registros
   * 
   * @return
   */
  long count();
}
