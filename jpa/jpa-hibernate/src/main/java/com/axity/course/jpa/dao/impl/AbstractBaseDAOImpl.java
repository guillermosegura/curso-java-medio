package com.axity.course.jpa.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.axity.course.jpa.util.HibernateUtil;

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

  private Session session;

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

    validateSession();

    session.beginTransaction();
    session.save( entity );
    session.getTransaction().commit();
  }

  /**
   * Edita una entidad
   * 
   * @param entity
   */
  public void edit( T entity )
  {
    validateSession();

    session.beginTransaction();
    session.saveOrUpdate( entity );
    session.getTransaction().commit();
  }

  /**
   * Elimina una entidad
   * 
   * @param entity
   */
  public void remove( T entity )
  {
    validateSession();
    session.beginTransaction();
    session.delete( entity );
    session.getTransaction().commit();
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
    validateSession();
    return (T) session.get( entityClass, i );
  }

  /**
   * Busca todas las entidades
   * 
   * @return
   */
  @SuppressWarnings("unchecked")
  public List<T> findAll()
  {
    validateSession();

    String queryString = "FROM " + this.entityClass.getSimpleName();
    Query query = session.createQuery( queryString );

    return new ArrayList<>( query.list() );
  }

  /**
   * Cuenta las entidades
   * 
   * @return
   */
  public long count()
  {
    validateSession();

    String queryString = "SELECT COUNT(1) FROM " + this.entityClass.getSimpleName();
    Query query = session.createQuery( queryString );
    return (long) query.uniqueResult();
  }

  private void validateSession()
  {
    if( this.session == null || !session.isOpen() )
    {
      session = HibernateUtil.getSessionFactory().openSession();
    }
  }

  /**
   * Obtiene la session de hibernate
   * 
   * @return
   */
  protected Session getSession()
  {
    validateSession();
    return this.session;
  }

}
