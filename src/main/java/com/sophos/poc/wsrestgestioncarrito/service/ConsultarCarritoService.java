package com.sophos.poc.wsrestgestioncarrito.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sophos.poc.wsrestgestioncarrito.cache.GestionCarritoCacheManagement;
import com.sophos.poc.wsrestgestioncarrito.cache.repository.CarritoDTORepository;
import com.sophos.poc.wsrestgestioncarrito.dto.CarritoDTO;
import com.sophos.poc.wsrestgestioncarrito.dto.CarritoDetalleDTO;
import com.sophos.poc.wsrestgestioncarrito.model.response.GestionCarritoConsultarRs;
import com.sophos.poc.wsrestgestioncarrito.util.ConfigurationProperties;



@Service
public class ConsultarCarritoService {

	@Autowired
	GestionCarritoCacheManagement carritos;
	@Autowired
	CarritoDTORepository redisRepository;
	@Autowired
	ConfigurationProperties pr;
	
	public GestionCarritoConsultarRs getCart(String sessionKey) {
		if (pr.getCacheSource().equals(pr.CACHE_SOURCE_REDIS)) {
			try {
				CarritoDTO carritoRedis = redisRepository.findById(sessionKey).get();
				if (carritoRedis != null) {
					GestionCarritoConsultarRs response = new GestionCarritoConsultarRs();
					response.setIdSession(carritoRedis.getIdSession());
					response.setCart(carritoRedis.getCart());
					return response;
				}
			} catch (Exception e) {
				System.err.println("Error en consulta a REDIS: " + e);
			}
		} else {
			CarritoDTO carrito = carritos.getCarrito(sessionKey);
			if (carrito != null) {
				GestionCarritoConsultarRs response = new GestionCarritoConsultarRs();
				response.setIdSession(carrito.getIdSession());
				response.setCart(carrito.getCart());
				return response;
			}
		}
		return emptyCarrito(sessionKey);
	}

	private GestionCarritoConsultarRs emptyCarrito(String sessionKey) {
		GestionCarritoConsultarRs response = new GestionCarritoConsultarRs();
		response.setIdSession(sessionKey);
		CarritoDetalleDTO cart = new CarritoDetalleDTO();
		response.setCart(cart );		
		return response;
	}

}
