package com.sophos.poc.wsrestgestioncarrito.cache;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.RedisPassword;
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

	private static final Logger logger = LogManager.getLogger(RedisJavaConfiguration.class);
	@Autowired
	private ConfigurationProperties prp;
	@Bean
	JedisConnectionFactory jedisConnectionFactory() {
		RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(prp.getRedisHost(), prp.getRedisPort());
	    redisStandaloneConfiguration.setPassword(RedisPassword.of(prp.getRedisPass()));
	    return new JedisConnectionFactory(redisStandaloneConfiguration);
	}
	 
	@Bean
	public RedisTemplate<String, Object> redisTemplate() {
	    RedisTemplate<String, Object> template = new RedisTemplate<>();
	    template.setConnectionFactory(jedisConnectionFactory());
	    logger.info("Redis connection factory: " + prp.getRedisHost() + ":" +prp.getRedisPort());
	    return template;
	}

}
