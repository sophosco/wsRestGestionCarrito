package com.sophos.poc.wsrestgestioncarrito.cache;

import java.util.HashMap;

import org.springframework.stereotype.Component;

import com.sophos.poc.wsrestgestioncarrito.dto.CarritoDTO;


@Component
public class GestionCarritoCacheManagement {
	
	private static GestionCarritoCacheManagement instance;
	private HashMap<String, CarritoDTO> carritos;
	
	
	public GestionCarritoCacheManagement getInstance() {
		if (instance == null) {
			return new GestionCarritoCacheManagement();
		}
		else {
			return instance;
		}		
	}
	
	public GestionCarritoCacheManagement() {
		carritos = new HashMap<>();
	}	

	public HashMap<String, CarritoDTO> getCarritos() {
		return carritos;
	}
	
	public CarritoDTO getCarrito(String sessionKey) {
		return this.carritos.get(sessionKey);
	}

	public void addCarrito(String sessionKey, CarritoDTO carrito) {
		this.carritos.put(sessionKey,carrito);
	}
	

}
