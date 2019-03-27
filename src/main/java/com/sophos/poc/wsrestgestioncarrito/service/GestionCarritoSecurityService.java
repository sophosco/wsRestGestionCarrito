package com.sophos.poc.wsrestgestioncarrito.service;

import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sophos.poc.wsrestgestioncarrito.model.request.GestionCarritoSecurityRq;
import com.sophos.poc.wsrestgestioncarrito.model.request.SecurityRequestHeader;
import com.sophos.poc.wsrestgestioncarrito.model.request.SecurityRequestPayload;
import com.sophos.poc.wsrestgestioncarrito.model.response.GestionCarritoSecurityRs;
import com.sophos.poc.wsrestgestioncarrito.util.ConfigurationProperties;

@Service
public class GestionCarritoSecurityService {

	@Autowired
	ConfigurationProperties pr;
	
	private static final Logger logger = LogManager.getLogger(GestionCarritoSecurityService.class);
	
	public Boolean verifyJwtToken(String token, String payload) {
		
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		GestionCarritoSecurityRq inRequest = new GestionCarritoSecurityRq();
		SecurityRequestHeader requestHeader = new SecurityRequestHeader();
		SecurityRequestPayload requestPayload = new SecurityRequestPayload(); 
		requestHeader.setToken(token);
		requestPayload.setId(payload);
		inRequest.setRequestHeader(requestHeader );
		inRequest.setRequestPayload(requestPayload );
		
		HttpEntity<GestionCarritoSecurityRq> request = new HttpEntity<GestionCarritoSecurityRq>(inRequest ,headers);
		RestTemplate restTemplate = new RestTemplate();
		GestionCarritoSecurityRs response = restTemplate.exchange(pr.getSecurityEndpointValidation(),HttpMethod.POST,request , GestionCarritoSecurityRs.class).getBody();
		
		logger.info("verifyJwtTokenResponse: "+ response.getResponseHeader().getStatus().getCode());
		if (response.getResponseHeader().getStatus().getCode().equals(pr.SECURITY_SUCCESS)) {			
			return true;
		}
		return false;
	}
}
