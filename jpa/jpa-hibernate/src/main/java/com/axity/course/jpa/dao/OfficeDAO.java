package com.axity.course.jpa.dao;

import java.util.List;

import com.axity.course.jpa.model.OfficeDO;

public interface OfficeDAO extends BaseDAO<OfficeDO, String>
{

  /**
   * Regresa un listado de las oficinas por país
   * 
   * @param country
   * @return
   */
  List<OfficeDO> findByCountry( String country );

  /**
   * Regresa un listado de las oficinas por territorio
   * 
   * @param territory
   * @return
   */
  List<OfficeDO> findByTerritory( String territory );

}
