package com.axity.course.jpa.dao.impl;

import java.util.List;

import org.hibernate.Session;

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
  @SuppressWarnings("unchecked")
  public List<OfficeDO> findByCountry( String country )
  {
    Session session = getSession();

    String queryString = "SELECT o FROM OfficeDO o WHERE o.country = :country";
    return session.createQuery( queryString ).setParameter( "country", country ).list();
  }

  @Override
  @SuppressWarnings("unchecked")
  public List<OfficeDO> findByTerritory( String territory )
  {
    Session session = getSession();

    String queryString = "SELECT o FROM OfficeDO o WHERE o.territory = :territory";
    return session.createQuery( queryString ).setParameter( "territory", territory ).list();
  }
}
