package com.wang.wangmusic.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "v1/hello")
public class HelloController {

    @RequestMapping
    public String sayHello() {
        return "hello";
    }

}
