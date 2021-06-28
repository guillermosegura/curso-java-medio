package com.axity.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.axity.demo.model.OfficeDO;

@Repository
public interface OfficeRepository extends JpaRepository<OfficeDO, String>
{

}
