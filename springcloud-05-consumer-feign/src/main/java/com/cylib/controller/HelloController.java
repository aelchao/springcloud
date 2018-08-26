package com.cylib.controller;

import com.cylib.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: aelchao aelchao207@gmail.com
 * @Date: 2018/8/26 下午5:54
 */
@RestController
public class HelloController {
    @Autowired
    private HelloService helloService;

    @RequestMapping("getinfo")
    public String getInfo() {
        return helloService.getInfo();
    }
}
