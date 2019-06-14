package com.demo.hystrix.controller;

import org.apache.commons.lang.math.RandomUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Controller
@RequestMapping("/rest")
public class HystrixController
{

    @HystrixCommand(fallbackMethod = "fallBackHello", commandKey = "hello",
        groupKey = "hello")
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public @ResponseBody String hello ()
    {
        String message = "Hello World";
        if (RandomUtils.nextBoolean()) {
            throw new RuntimeException("Failed!");
        }
        return message;

    }
    
    public String fallBackHello () {
        String message = "Fallback Hello World!";
        return message;
    }

}
