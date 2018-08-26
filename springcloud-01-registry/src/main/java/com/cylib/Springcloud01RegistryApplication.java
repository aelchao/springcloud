package com.cylib;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class Springcloud01RegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(Springcloud01RegistryApplication.class, args);
	}
}
