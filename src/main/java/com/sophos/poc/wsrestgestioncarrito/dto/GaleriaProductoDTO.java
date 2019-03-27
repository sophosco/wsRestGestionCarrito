package com.sophos.poc.wsrestgestioncarrito.dto;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-03-20T23:44:12.597Z")
public class GaleriaProductoDTO {

	@JsonProperty("small")
	private String small = null;

	@JsonProperty("medium")
	private String medium = null;

	@JsonProperty("big")
	private String big = null;

	public String getSmall() {
		return small;
	}

	public void setSmall(String small) {
		this.small = small;
	}

	public String getMedium() {
		return medium;
	}

	public void setMedium(String medium) {
		this.medium = medium;
	}

	public String getBig() {
		return big;
	}

	public void setBig(String big) {
		this.big = big;
	}

	

}
