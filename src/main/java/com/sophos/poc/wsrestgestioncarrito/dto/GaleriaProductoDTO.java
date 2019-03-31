package com.sophos.poc.wsrestgestioncarrito.dto;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-03-20T23:44:12.597Z")
public class GaleriaProductoDTO {

	@JsonProperty("small")
	private Object small = null;

	@JsonProperty("medium")
	private Object medium = null;

	@JsonProperty("big")
	private Object big = null;

	public Object getSmall() {
		return small;
	}

	public void setSmall(Object small) {
		this.small = small;
	}

	public Object getMedium() {
		return medium;
	}

	public void setMedium(Object medium) {
		this.medium = medium;
	}

	public Object getBig() {
		return big;
	}

	public void setBig(Object big) {
		this.big = big;
	}

	

}
