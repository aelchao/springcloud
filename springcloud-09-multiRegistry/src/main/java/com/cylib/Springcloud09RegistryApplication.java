package com.cylib;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class Springcloud09RegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(Springcloud09RegistryApplication.class, args);
	}
}
