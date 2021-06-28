package com.axity.crud.to;

import java.io.Serializable;

public class LogBackTO implements Serializable {

	private static final long serialVersionUID = -649197896108360871L;
	private String txId;
	private String token;
	private String sessionId;
	private String serviceName;
	private String operationName;
	private String forwardedFor;
	private String remoteAddr;

	public String getTxId() {
		return txId;
	}

	public void setTxId(String txId) {
		this.txId = txId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getOperationName() {
		return operationName;
	}

	public void setOperationName(String operationName) {
		this.operationName = operationName;
	}

	public String getForwardedFor() {
		return forwardedFor;
	}

	public void setForwardedFor(String forwardedFor) {
		this.forwardedFor = forwardedFor;
	}

	public String getRemoteAddr() {
		return remoteAddr;
	}

	public void setRemoteAddr(String remoteAddr) {
		this.remoteAddr = remoteAddr;
	}

}
