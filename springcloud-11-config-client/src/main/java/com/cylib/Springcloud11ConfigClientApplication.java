package com.cylib;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
@RefreshScope
public class Springcloud11ConfigClientApplication {

    @Value("${star.username}")
    private String username;

    @RequestMapping("getusername")
    public String getUsername() {
        return username;
    }

	public static void main(String[] args) {
		SpringApplication.run(Springcloud11ConfigClientApplication.class, args);
	}
}
