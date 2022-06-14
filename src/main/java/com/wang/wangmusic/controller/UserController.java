package com.wang.wangmusic.controller;

import com.wang.wangmusic.dto.UserCreateDto;
import com.wang.wangmusic.mapper.UserMapper;
import com.wang.wangmusic.service.UserService;
import com.wang.wangmusic.vo.UserVo;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "users")
public class UserController {

    final UserService userService;
    final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostMapping("/")
    UserVo create(@RequestBody UserCreateDto userCreateDto) {
        return userMapper.toVo(userService.create(userCreateDto));
    }

    @GetMapping
    public List<UserVo> list() {
        return userService.list().stream().map(userMapper::toVo).collect(Collectors.toList());
    }

}
