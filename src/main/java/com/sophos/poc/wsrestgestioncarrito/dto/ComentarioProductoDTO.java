package com.sophos.poc.wsrestgestioncarrito.dto;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-03-22T23:44:12.597Z")
public class ComentarioProductoDTO {

	@JsonProperty("comment")
	private String comment = null;

	@JsonProperty("user")
	private String user = null;

	@JsonProperty("creationDate")
	private String creationDate = null;

	@JsonProperty("rating")
	private Long rating = null;

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public Long getRating() {
		return rating;
	}

	public void setRating(Long rating) {
		this.rating = rating;
	}

	

}
