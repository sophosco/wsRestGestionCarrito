package com.sophos.poc.wsrestgestioncarrito.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GestionCarritoConsultarRq {

	@JsonProperty("idSession")
	private String idSession = null;

	public String getIdSession() {
		return idSession;
	}

	public void setIdSession(String idSession) {
		this.idSession = idSession;
	}
	


}
