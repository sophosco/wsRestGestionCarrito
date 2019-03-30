package com.sophos.poc.wsrestgestioncarrito.service;

import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.Base64;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sophos.poc.wsrestgestioncarrito.model.request.AuditoriaAccionRq;
import com.sophos.poc.wsrestgestioncarrito.model.request.AuditoriaAccionRs;
import com.sophos.poc.wsrestgestioncarrito.model.request.GestionCarritoActualizarRq;
import com.sophos.poc.wsrestgestioncarrito.util.ConfigurationProperties;


@Service
public class AuditoriaService {
	
	@Autowired
	private ConfigurationProperties pr;
	
	private static final Logger logger = LogManager.getLogger(AuditoriaService.class);
	
	@Async
	public void sendAudit(String rqUID,String channel,String ip,String tokenSesion, @Valid GestionCarritoActualizarRq rq) {
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.set("X-RqUID", rqUID);
			headers.set("X-Channel", channel);
			headers.set("X-IPAddr",ip);
			headers.set("X-Sesion", tokenSesion);
			headers.set("X-haveToken", "false");
			
			String messageData = Base64.getEncoder().encodeToString(rq.toString().getBytes());
			AuditoriaAccionRq inRequest = new AuditoriaAccionRq();
			inRequest.setIdSesion(rq.getIdSession());
			OffsetDateTime fechaCreacion = OffsetDateTime.now();
			inRequest.setFechaCreacion(fechaCreacion);
			inRequest.setTipoAccion(pr.ACT_UPDATE_CART);
			inRequest.setModuloAplicacion(pr.APP_NAME);
			inRequest.setMessageData(messageData);
				
			HttpEntity<AuditoriaAccionRq> request = new HttpEntity<AuditoriaAccionRq>(inRequest ,headers);
			RestTemplate restTemplate = new RestTemplate();
			String auditoriaEndpoint = System.getenv(pr.POC_SERVICE_AUDIT_VALIDATE);
			restTemplate.exchange(auditoriaEndpoint ,HttpMethod.POST,request , AuditoriaAccionRs.class);			
		}catch (Exception e) {
			logger.error("Error al enviar auditoria", e);
		}		
		
	}

}
