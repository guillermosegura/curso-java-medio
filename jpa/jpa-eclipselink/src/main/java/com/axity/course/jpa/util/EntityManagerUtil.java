package com.axity.course.jpa.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerUtil
{
  private static EntityManagerFactory entityManagerFactory;
  private static EntityManager entityManager = buildEntityManager();
  private static EntityManager buildEntityManager()
  {
    try
    {
      entityManagerFactory = Persistence.createEntityManagerFactory( "my-app" );
      return entityManagerFactory.createEntityManager();
    }
    catch( Throwable ex )
    {
      // Make sure you log the exception, as it might be swallowed
      System.err.println( "Initial SessionFactory creation failed." + ex );
      throw new ExceptionInInitializerError( ex );
    }
  }

  public static EntityManager getEntityManager()
  {
    return entityManager;
  }

}
