package com.sophos.poc.wsrestgestioncarrito.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sophos.poc.wsrestgestioncarrito.cache.GestionCarritoCacheManagement;
import com.sophos.poc.wsrestgestioncarrito.cache.repository.CarritoDTORepository;
import com.sophos.poc.wsrestgestioncarrito.dto.CarritoDTO;
import com.sophos.poc.wsrestgestioncarrito.model.request.GestionCarritoActualizarRq;
import com.sophos.poc.wsrestgestioncarrito.util.ConfigurationProperties;


@Service
public class ActualizarCarritoService {

	@Autowired
	GestionCarritoCacheManagement carritos;
	@Autowired
	CarritoDTORepository redisRepository;
	@Autowired
	ConfigurationProperties pr;
	
	public void updateCart(String idSession, @Valid GestionCarritoActualizarRq rq) {
		
		CarritoDTO carro = new CarritoDTO();
		carro.setIdSession(idSession);
		carro.setCart(rq.getCart());
		
		if (pr.getCacheSource().equals(pr.CACHE_SOURCE_REDIS)) {
			try {
				redisRepository.save(carro);	
			}catch (Exception e) {
				System.err.println("Error REDIS:" + e);
			}				
		}else {
			carritos.addCarrito(idSession, carro);
		}		
	}

}
