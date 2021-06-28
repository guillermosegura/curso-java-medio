package com.axity.crud.controller;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.axity.crud.service.LogService;
import com.axity.crud.to.LogTO;
import com.axity.crud.to.ProductTO;

@RestController
@RequestMapping("/api/v1")
@Intercept
public class LogController {

	@Autowired
	private LogService logService;

	@GetMapping("/logs")
	public ResponseEntity<List<LogTO>> findAll(@RequestParam(name = "page", required = false) Integer page,
			@RequestParam(name = "size", required = false) Integer size) {

		if (page == null) {
			page = 0;
		}
		if (size == null) {
			size = 10;
		}
		List<LogTO> logs = logService.findLogs(page, size);

		ResponseEntity<List<LogTO>> response;
		if (CollectionUtils.isEmpty(logs)) {
			response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			response = new ResponseEntity<>(logs, HttpStatus.OK);
		}
		return response;
	}

	@GetMapping("/logs/{id}")
	public ResponseEntity<LogTO> findById(@PathVariable(value = "id") Long id) {
		LogTO log = this.logService.findLog(id);
		ResponseEntity<LogTO> response;
		if (log == null) {
			response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			response = new ResponseEntity<>(log, HttpStatus.OK);
		}
		return response;
	}

	@PostMapping("/logs")
	public ResponseEntity<LogTO> save(@RequestBody LogTO log) {
		logService.saveLog(log);
		return new ResponseEntity<>(log, HttpStatus.CREATED);
	}

	@DeleteMapping("/logs/{id}")
	public ResponseEntity<Void> delete(@PathVariable(value = "id") Long id) {
		logService.deleteLog(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("/logs")
	public ResponseEntity<Void> deleteAll() {
		logService.deleteLogs();
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
