package com.sophos.poc.wsrestgestioncarrito.util;

import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigurationProperties {

	private static ConfigurationProperties instance = null;
	private String cacheSource;
	private String securityEndpointValidation;
	private static final String FILE = "configuration.properties";
	private static final String CACHE_SOURCE = "cache.source";
	private static final String REDIS_HOST = "redis.default.host";
	private static final String REDIS_PORT = "redis.default.port";
	private static final String REDIS_PSS = "redis.default.pass";
	
	private String redisHost;
	private int	   redisPort;
	private String redisPass;
	
	public final String APP_NAME = "wsRestGestionCarrito";
	public final String SECURITY_SUCCESS = "00";
	public final String CACHE_SOURCE_REDIS = "REDIS";
	public final String POC_REDIS_HOST = "POC_REDIS_HOST";
	public final String POC_REDIS_PORT = "POC_REDIS_PORT";
	public final String POC_SERVICE_AUDIT_VALIDATE = "POC_SERVICE_AUDIT_VALIDATE";
	public final String ACT_UPDATE_CART = "updateCart";
	
	private static final Logger logger = LogManager.getLogger(ConfigurationProperties.class);
			
	public ConfigurationProperties getInstance() {
		if (instance == null) {
			return new ConfigurationProperties();
		}
		return instance;
	}
	
	public ConfigurationProperties() {
		Properties pr = new Properties();
		try {
			InputStream inputStream = ConfigurationProperties.class.getClassLoader().getResourceAsStream(FILE); 
			pr.load(inputStream);
			
			String envRP = System.getenv().get("POC_REDIS_PORT");
			String envRH = System.getenv().get("POC_REDIS_HOST");
			String envRC = System.getenv().get("POC_REDIS_PASS");
			
			setCacheSource(pr.getProperty(CACHE_SOURCE));	
			setSecurityEndpointValidation(System.getenv("POC_SERVICE_SECURITY_VALIDATE"));
			setRedisHost(envRH != null && !envRH.equals("") ? envRH : pr.getProperty(REDIS_HOST));
			setRedisPass(envRC != null && !envRC.equals("") ? envRC : pr.getProperty(REDIS_PSS));
			setRedisPort(envRP != null && !envRP.equals("") ? Integer.parseInt(envRP) : Integer.parseInt(pr.getProperty(REDIS_PORT)) );
			
			
		}catch (NumberFormatException ex) {
			setRedisPort(6379);
		}catch(Exception e) {
			logger.error("ConfigurationProperties-Error inicializando las variables", e);
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

	public String getRedisHost() {
		return redisHost;
	}

	public void setRedisHost(String redisHost) {
		this.redisHost = redisHost;
	}

	public int getRedisPort() {
		return redisPort;
	}

	public void setRedisPort(int redisPort) {
		this.redisPort = redisPort;
	}

	public String getRedisPass() {
		return redisPass;
	}

	public void setRedisPass(String redisPass) {
		this.redisPass = redisPass;
	}
}
