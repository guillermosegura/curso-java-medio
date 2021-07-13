package com.axity.course.jpa.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.axity.course.jpa.util.EntityManagerUtil;

/**
 * Clase base de DAOs
 * 
 * @author guillermo.segura@axity.com
 * @param <T>
 * @param <I>
 */
public abstract class AbstractBaseDAOImpl<T extends Serializable, I extends Serializable>
{
  private static final Logger LOG = LoggerFactory.getLogger( AbstractBaseDAOImpl.class );

  /**
   * Entidad
   */
  private Class<T> entityClass;

  private EntityManager em;

  /**
   * Costructor por clase
   * 
   * @param entityClass
   */
  public AbstractBaseDAOImpl( Class<T> entityClass )
  {
    this.entityClass = entityClass;
  }

  /**
   * Constructor default
   */
  public AbstractBaseDAOImpl()
  {
    this.entityClass = this.getEntityClass();
  }

  @SuppressWarnings("unchecked")
  private Class<T> getEntityClass()
  {

    Class<T> entityClass_ = null;
    try
    {
      ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
      entityClass_ = (Class<T>) type.getActualTypeArguments()[0];
    }
    catch( Exception err )
    {
      entityClass_ = null;
      LOG.info( "Exception: ", err );
    }

    return entityClass_;
  }

  /**
   * Crea una entidad
   * 
   * @param entity
   */
  public void create( T entity )
  {

    validateEntityManager();

    em.getTransaction().begin();
    em.persist( entity );
    em.getTransaction().commit();
  }

  /**
   * Edita una entidad
   * 
   * @param entity
   */
  public void edit( T entity )
  {
    validateEntityManager();

    em.getTransaction().begin();
    em.merge( entity );
    em.getTransaction().commit();
  }

  /**
   * Elimina una entidad
   * 
   * @param entity
   */
  public void remove( T entity )
  {
    validateEntityManager();
    em.getTransaction().begin();
    em.remove( em.merge( entity ) );
    em.getTransaction().commit();
  }

  /**
   * Busca por Id
   * 
   * @param i
   * @return
   */
  @SuppressWarnings("unchecked")
  public T find( I i )
  {
    validateEntityManager();
    return (T) em.find( entityClass, i );
  }

  /**
   * Busca todas las entidades
   * 
   * @return
   */
  public List<T> findAll()
  {
    validateEntityManager();

    String queryString = "SELECT o FROM " + this.entityClass.getSimpleName() + " AS o";

    return em.createQuery( queryString, entityClass ).getResultList();
  }

  /**
   * Cuenta las entidades
   * 
   * @return
   */
  public long count()
  {
    validateEntityManager();
    String queryString = "SELECT COUNT(o) FROM " + this.entityClass.getSimpleName() + " AS o";
    return em.createQuery( queryString, Long.class ).getSingleResult();
  }

  private void validateEntityManager()
  {
    if( this.em == null || !em.isOpen() )
    {
      em = EntityManagerUtil.getEntityManager();
    }
  }

  /**
   * Obtiene la session de hibernate
   * 
   * @return
   */
  protected EntityManager getEntityManager()
  {
    validateEntityManager();
    return this.em;
  }

}
