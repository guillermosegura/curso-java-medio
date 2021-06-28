package com.axity.crud.to;

import java.io.Serializable;
import java.util.Date;

public class LogTO implements Serializable {

	private static final long serialVersionUID = -3996842691158824526L;
	private Long id;
	private String log;
	private Date date;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLog() {
		return log;
	}

	public void setLog(String log) {
		this.log = log;
	}

	public Date getDate() {
		return date != null ? (Date) date.clone() : null;
	}

	public void setDate(Date date) {
		this.date = date != null ? (Date) date.clone() : null;
	}

}
