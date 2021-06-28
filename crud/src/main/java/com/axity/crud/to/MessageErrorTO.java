package com.axity.crud.to;

import java.io.Serializable;

public class MessageErrorTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9000013352166280158L;
	private String item;
	private String error;

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
}
