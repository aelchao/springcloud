package com.cylib;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import zipkin.server.EnableZipkinServer;

@SpringBootApplication
@EnableZipkinServer
public class Springcloud12ServerZipkinApplication {

	public static void main(String[] args) {
		SpringApplication.run(Springcloud12ServerZipkinApplication.class, args);
	}
}
