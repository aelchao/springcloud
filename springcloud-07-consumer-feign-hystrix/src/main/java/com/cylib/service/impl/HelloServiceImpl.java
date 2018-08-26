package com.cylib.service.impl;

import com.cylib.service.HelloService;
import org.springframework.stereotype.Service;

/**
 * @Author: aelchao aelchao207@gmail.com
 * @Date: 2018/8/26 下午6:35
 */
@Service
public class HelloServiceImpl implements HelloService {
    @Override
    public String getInfo() {
        return "服务发生故障，断路器启动";
    }
}
