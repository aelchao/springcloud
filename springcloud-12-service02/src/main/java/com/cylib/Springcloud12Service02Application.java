package com.cylib;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
public class Springcloud12Service02Application {
	@Autowired
	private RestTemplate restTemplate;

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	// 构建采样器
	@Bean
	public AlwaysSampler getAlwaysSampler() {
		return new AlwaysSampler();
	}

	@RequestMapping("/getservice")
	public String getService() {
		return restTemplate.getForObject("http://localhost:8092/getinfo", String.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(Springcloud12Service02Application.class, args);
	}
}
