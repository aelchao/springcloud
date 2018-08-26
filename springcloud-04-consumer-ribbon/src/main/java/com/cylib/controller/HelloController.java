package com.cylib.controller;

import com.cylib.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: aelchao aelchao207@gmail.com
 * @Date: 2018/8/26 下午4:18
 */
@Controller
public class HelloController {
    @Autowired
    private HelloService helloService;

    @RequestMapping("/getinfo")
    @ResponseBody
    public String getInfo() {
        return helloService.getInfo();
    }
}
