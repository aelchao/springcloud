package com.cylib.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: aelchao aelchao207@gmail.com
 * @Date: 2018/8/26 下午5:51
 */
@FeignClient("springcloud-provider")
public interface HelloService {
    @RequestMapping("/show")
    String getInfo();
}
