package com.axity.crud.persistence;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.axity.crud.model.LogDO;

public interface LogPersistence extends JpaRepository<LogDO, Long> {

}
