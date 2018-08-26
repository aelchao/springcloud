package com.cylib.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: aelchao aelchao207@gmail.com
 * @Date: 2018/8/26 下午4:15
 */
@Service
public class HelloService {
    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getErrorInfo")
    public String getInfo() {
        return restTemplate.getForObject("http://springcloud-provider/show", String.class);
    }

    public String getErrorInfo() {
        return "服务调用发生异常，断路器打开";
    }
}
