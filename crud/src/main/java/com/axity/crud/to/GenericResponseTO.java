package com.axity.crud.to;

import java.io.Serializable;

public class GenericResponseTO implements Serializable {

	private static final long serialVersionUID = 7279794246029133581L;

	private LogBackTO logBack;

	private StatusOperationTO statusOperation;

	public LogBackTO getLogBack() {
		return logBack;
	}

	public void setLogBack(LogBackTO logBack) {
		this.logBack = logBack;
	}

	public StatusOperationTO getStatusOperation() {
		return statusOperation;
	}

	public void setStatusOperation(StatusOperationTO statusOperation) {
		this.statusOperation = statusOperation;
	}
}
