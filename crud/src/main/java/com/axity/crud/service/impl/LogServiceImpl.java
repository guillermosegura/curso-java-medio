package com.axity.crud.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.axity.crud.model.LogDO;
import com.axity.crud.persistence.LogPersistence;
import com.axity.crud.service.LogService;
import com.axity.crud.to.LogTO;

@Service
public class LogServiceImpl implements LogService {

	@Autowired
	private LogPersistence logPersistence;

	@Override
	public void saveLog(LogTO log) {

		LogDO entity = new LogDO();
		entity.setLog(log.getLog());
		entity.setDate(Calendar.getInstance().getTime());
		entity = logPersistence.save(entity);

		log.setId(entity.getId());
		log.setDate(entity.getDate());
	}

	@Override
	public LogTO findLog(Long id) {

		Optional<LogDO> logDO = this.logPersistence.findById(id);
		LogTO log = null;
		if (logDO.isPresent()) {
			log = new LogTO();
			log.setId(logDO.get().getId());
			log.setLog(logDO.get().getLog());
			log.setDate(logDO.get().getDate());
		}

		return log;
	}

	@Override
	public void deleteLog(Long id) {
		this.logPersistence.deleteById(id);
	}

	@Override
	public void deleteLogs() {
		this.logPersistence.deleteAll();
	}

	@Override
	public List<LogTO> findLogs(int page, int size) {

		Sort sort = Sort.by(Sort.Direction.DESC, "date");

		PageRequest pg = PageRequest.of(page, size, sort);

		Page<LogDO> logs = this.logPersistence.findAll(pg);

		List<LogTO> result = new ArrayList<>();

		logs.forEach(entity -> {
			LogTO log = new LogTO();
			log.setId(entity.getId());
			log.setLog(entity.getLog());
			log.setDate(entity.getDate());
			result.add(log);
		});

		return result;
	}

}
