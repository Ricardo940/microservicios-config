package com.config.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class MicroserviciosConfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviciosConfigApplication.class, args);
	}

}
