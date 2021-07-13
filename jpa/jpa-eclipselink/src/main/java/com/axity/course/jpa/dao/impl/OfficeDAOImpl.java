package com.axity.course.jpa.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.axity.course.jpa.dao.OfficeDAO;
import com.axity.course.jpa.model.OfficeDO;

public class OfficeDAOImpl extends AbstractBaseDAOImpl<OfficeDO, String> implements OfficeDAO
{

  /**
   * Constructor default
   */
  public OfficeDAOImpl()
  {
    super( OfficeDO.class );
  }

  @Override
  public List<OfficeDO> findByCountry( String country )
  {
    EntityManager em = getEntityManager();

    String queryString = "SELECT o FROM OfficeDO o WHERE o.country = :country";
    TypedQuery<OfficeDO> query = em.createQuery( queryString, OfficeDO.class );
    query.setParameter( "country", country );
    
    return query.getResultList();

  }

  @Override
  @SuppressWarnings("unchecked")
  public List<OfficeDO> findByTerritory( String territory )
  {
    EntityManager em = getEntityManager();

    String queryString = "SELECT o FROM OfficeDO o WHERE o.territory = :territory";
    TypedQuery<OfficeDO> query = em.createQuery( queryString, OfficeDO.class );
    query.setParameter( "territory", territory );
    
    return query.getResultList();
  }
}
