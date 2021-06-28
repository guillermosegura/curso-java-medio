package com.axity.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.axity.demo.model.EmployeeDO;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeDO, String>
{

}
