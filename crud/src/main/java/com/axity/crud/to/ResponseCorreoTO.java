package com.axity.crud.to;

public class ResponseCorreoTO extends GenericResponseTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3408836967425406446L;
	private CorreoTO correo;

	public CorreoTO getCorreo() {
		return correo;
	}

	public void setCorreo(CorreoTO correo) {
		this.correo = correo;
	}

}
