package com.sophos.poc.wsrestgestioncarrito.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

import com.sophos.poc.wsrestgestioncarrito.util.ConfigurationProperties;


@Configuration
@ComponentScan("com.sophos.poc.wsrestgestioncarrito.cache")
@EnableRedisRepositories(basePackages = "com.sophos.poc.wsrestgestioncarrito.cache.repository")
@PropertySource("classpath:application.properties")
public class RedisJavaConfiguration {
	@Autowired
	private ConfigurationProperties cts;
	@Bean
	JedisConnectionFactory jedisConnectionFactory() {
		int 	redisPort = Integer.parseInt(System.getenv().get(cts.POC_REDIS_PORT) );
	    String  redisHost = System.getenv().get(cts.POC_REDIS_HOST) ;	    
		RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(redisHost, redisPort);
	    return new JedisConnectionFactory(redisStandaloneConfiguration);	    
	}
	 
	@Bean
	public RedisTemplate<String, Object> redisTemplate() {
	    RedisTemplate<String, Object> template = new RedisTemplate<>();
	    template.setConnectionFactory(jedisConnectionFactory());
	    return template;
	}

}
