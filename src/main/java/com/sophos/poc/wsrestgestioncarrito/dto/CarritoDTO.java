package com.sophos.poc.wsrestgestioncarrito.dto;

import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

@RedisHash("CarritoDTO")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-03-20T23:44:12.597Z")
public class CarritoDTO implements Serializable {

	private static final long serialVersionUID = 8419422065004282815L;

	@Id
	@JsonProperty("idSession")
	private String idSession = null;
	
	@JsonProperty("cart")
	private CarritoDetalleDTO cart = null;

	public String getIdSession() {
		return idSession;
	}

	public void setIdSession(String idSesion) {
		this.idSession = idSesion;
	}

	public CarritoDetalleDTO getCart() {
		return cart;
	}

	public void setCart(CarritoDetalleDTO cart) {
		this.cart = cart;
	}
	
	
	
}
