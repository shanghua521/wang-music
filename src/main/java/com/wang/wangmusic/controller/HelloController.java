package com.wang.wangmusic.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping(value = "v1/hello")
public class HelloController {

    @RequestMapping
    public String sayHello() {
        return "hello";
    }

}
