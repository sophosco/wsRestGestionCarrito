package com.sophos.poc.wsrestgestioncarrito.model.response;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sophos.poc.wsrestgestioncarrito.dto.CarritoDetalleDTO;


@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-03-20T23:44:12.597Z")
public class GestionCarritoConsultarRs {

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

	@Override
	public String toString() {
		return "GestionCarritoConsultarRs [idSession=" + idSession + ", cart=" + cart + "]";
	}
	
}
