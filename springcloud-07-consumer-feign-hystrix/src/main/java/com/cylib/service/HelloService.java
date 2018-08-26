package com.cylib.service;

import com.cylib.service.impl.HelloServiceImpl;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: aelchao aelchao207@gmail.com
 * @Date: 2018/8/26 下午5:51
 */
@FeignClient(value="springcloud-provider", fallback = HelloServiceImpl.class)
public interface HelloService {
    @RequestMapping("/show")
    String getInfo();
}
