package com.sophos.poc.wsrestgestioncarrito.util;

import java.io.InputStream;
import java.util.Properties;

import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigurationProperties {

	private static ConfigurationProperties instance = null;
	private String cacheSource;
	private String securityEndpointValidation;
	
	private static final String FILE = "configuration.properties";
	private static final String CACHE_SOURCE = "cache.source";
	private static final String SECURITY_EP_VAL = "security.endpoint.validate";
	public final String SECURITY_SUCCESS = "00";
	public final String CACHE_SOURCE_REDIS = "REDIS";
	public final String POC_REDIS_HOST = "POC_REDIS_HOST";
	public final String POC_REDIS_PORT = "POC_REDIS_PORT";
	
	public ConfigurationProperties getInstance() {
		if (instance == null) {
			return instance = new ConfigurationProperties();
		}
		return instance;
	}
	
	public ConfigurationProperties() {
		Properties pr = new Properties();
		try {
			InputStream inputStream = ConfigurationProperties.class.getClassLoader().getResourceAsStream(FILE); 
			pr.load(inputStream);
			setCacheSource(pr.getProperty(CACHE_SOURCE));	
			setSecurityEndpointValidation(pr.getProperty(SECURITY_EP_VAL));
		}catch(Exception e) {
			
		}
	}

	public String getCacheSource() {
		return cacheSource;
	}

	public void setCacheSource(String cacheSource) {
		this.cacheSource = cacheSource;
	}

	public String getSecurityEndpointValidation() {
		return securityEndpointValidation;
	}

	public void setSecurityEndpointValidation(String securityEndpointValidation) {
		this.securityEndpointValidation = securityEndpointValidation;
	}
}
