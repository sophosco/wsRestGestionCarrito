package com.sophos.poc.wsrestgestioncarrito.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sophos.poc.wsrestgestioncarrito.dto.CarritoDetalleDTO;

public class GestionCarritoActualizarRq {
	@JsonProperty("idSession")
	private String idSession = null;
	
	@JsonProperty("cart")
	private CarritoDetalleDTO cart = null;

	public String getIdSession() {
		return idSession;
	}

	public void setIdSession(String idSession) {
		this.idSession = idSession;
	}

	public CarritoDetalleDTO getCart() {
		return cart;
	}

	public void setCart(CarritoDetalleDTO cart) {
		this.cart = cart;
	}
	

	
}
