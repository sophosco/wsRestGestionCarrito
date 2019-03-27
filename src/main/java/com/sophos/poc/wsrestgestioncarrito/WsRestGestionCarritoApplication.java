package com.sophos.poc.wsrestgestioncarrito;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class WsRestGestionCarritoApplication {

	public static void main(String[] args) {
		SpringApplication.run(WsRestGestionCarritoApplication.class, args);
	}

}
