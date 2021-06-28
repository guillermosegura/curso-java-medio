package com.axity.crud.to;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class StatusOperationTO implements Serializable {

	private static final long serialVersionUID = -2236179669501073544L;
	private Integer code;
	private String description;
	private List<MessageErrorTO> errors;

	public StatusOperationTO() {
		code = 0;
		errors = new ArrayList<>();
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<MessageErrorTO> getErrors() {
		return errors;
	}

	public void setErrors(List<MessageErrorTO> errors) {
		this.errors = errors;
	}

}
