package com.wang.music.controller;

import com.wang.music.dto.TokenCreateRequest;
import com.wang.music.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tokens")
public class TokenController {

    private final UserService userService;

    @Autowired
    public TokenController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public String createToken(@Validated @RequestBody TokenCreateRequest tokenCreateRequest) {
        return userService.createToken(tokenCreateRequest);
    }

}
