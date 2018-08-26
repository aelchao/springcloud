package com.cylib;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
@EnableEurekaClient
@Controller
public class Springcloud03ProviderApplication {

    @RequestMapping("/show")
    @ResponseBody
    public String showPage() {
        return "localhost:8082";
    }

    public static void main(String[] args) {
        SpringApplication.run(Springcloud03ProviderApplication.class, args);
    }
}
