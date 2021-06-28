package com.axity.crud.to;

import java.io.Serializable;

public class CorreoTO implements Serializable {

	private static final long serialVersionUID = 6410714139829680127L;
	private String id;
	private String mail;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

}
