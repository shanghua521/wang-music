package com.wang.wangmusic.controller;

import com.wang.wangmusic.entity.User;
import com.wang.wangmusic.repository.UserRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "v1/hello")
public class HelloController {

    private final UserRepository userRepository;

    public HelloController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping
    public String sayHello() {
        return "hello";
    }

    @RequestMapping(value = "users")
    public List<User> findAllUser() {
        return userRepository.findAll();
    }
}
