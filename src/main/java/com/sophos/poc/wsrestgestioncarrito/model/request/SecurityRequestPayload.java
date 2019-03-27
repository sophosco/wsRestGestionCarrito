package com.sophos.poc.wsrestgestioncarrito.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SecurityRequestPayload {

	@JsonProperty("Id")
	private String id = null;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	
}
