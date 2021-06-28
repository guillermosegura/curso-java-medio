package com.axity.crud.service;

import java.util.List;

import com.axity.crud.to.LogTO;

public interface LogService {

	void saveLog(LogTO log);
	
	LogTO findLog(Long id);
	
	void deleteLog(Long id);
	
	void deleteLogs();
	
	List<LogTO> findLogs(int page, int pageSize);
}
