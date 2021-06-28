package com.axity.crud.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.List;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.axity.crud.to.LogTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest()
@Transactional
public class LogServiceTest {

	@Autowired
	private LogService logService;

	@Test
	public void testSaveLog() {
		LogTO log = new LogTO();
		log.setLog(UUID.randomUUID().toString());
		this.logService.saveLog(log);
		

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		System.out.println(gson.toJson(log));
	}
 

	@Test
	public void testDeleteLogs() {
		this.logService.deleteLogs();
	}

	@Test
	public void testFindLogs() {
		int page = 0;
		int pageSize = 5;
		List<LogTO> logs = logService.findLogs(page, pageSize);
		assertNotNull(logs);

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		System.out.println(gson.toJson(logs));

	}

}
