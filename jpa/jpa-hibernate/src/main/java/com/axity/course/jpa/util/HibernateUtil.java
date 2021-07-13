package com.axity.course.jpa.util;

import java.io.InputStream;
import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.axity.course.jpa.model.CustomerDO;
import com.axity.course.jpa.model.EmployeeDO;
import com.axity.course.jpa.model.OfficeDO;
import com.axity.course.jpa.model.PaymentDO;

/**
 * Fuente: https://mkyong.com/hibernate/maven-3-hibernate-3-6-oracle-11g-example-xml-mapping/
 * 
 * @author mkyong
 */
public class HibernateUtil
{
  private static final SessionFactory sessionFactory = buildSessionFactory();
  private static StandardServiceRegistry registry;
  private static SessionFactory buildSessionFactory()
  {
    try
    {
      Configuration configuration = new Configuration();
      InputStream inputStream = HibernateUtil.class.getClassLoader().getResourceAsStream( "hibernate.properties" );
      Properties hibernateProperties = new Properties();
      hibernateProperties.load( inputStream );
      configuration.setProperties( hibernateProperties );

      configuration.addAnnotatedClass( OfficeDO.class );
      configuration.addAnnotatedClass( CustomerDO.class );
      configuration.addAnnotatedClass( EmployeeDO.class );
      configuration.addAnnotatedClass( PaymentDO.class );

      ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings( configuration.getProperties() )
          .buildServiceRegistry();
      SessionFactory sessionFactory = configuration.buildSessionFactory( serviceRegistry );
      return sessionFactory;

    }
    catch( Throwable ex )
    {
      // Make sure you log the exception, as it might be swallowed
      System.err.println( "Initial SessionFactory creation failed." + ex );
      throw new ExceptionInInitializerError( ex );
    }
  }

  /**
   * Regresa el {@link org.hibernate.SessionFactory}
   * 
   * @return
   */
  public static SessionFactory getSessionFactory()
  {
    return sessionFactory;
  }

  /**
   * Close caches and connection pools
   */
  public static void shutdown()
  {
    // Close caches and connection pools
    getSessionFactory().close();
  }
}
