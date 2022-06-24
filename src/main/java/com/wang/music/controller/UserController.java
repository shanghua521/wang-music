package com.wang.music.controller;

import com.wang.music.dto.UserCreateRequest;
import com.wang.music.dto.UserUpdateRequest;
import com.wang.music.mapper.UserMapper;
import com.wang.music.service.UserService;
import com.wang.music.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "用户")
@RequestMapping(value = "users")
public class UserController {

    final UserService userService;
    final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping
    @ApiOperation("分页查询")
    public Page<UserVo> search(@PageableDefault(sort = {"createTime"}, direction = Sort.Direction.ASC) Pageable pageable) {
        return userService.search(pageable).map(userMapper::toVo);
    }

    @PostMapping
    @ApiOperation("创建用户")
    UserVo create(@Validated @RequestBody UserCreateRequest userCreateRequest) {
        return userMapper.toVo(userService.create(userCreateRequest));
    }

    @PostMapping("/{id}")
    @ApiOperation("根据ID查询")
    UserVo get(@PathVariable String id) {
        return userMapper.toVo(userService.get(id));
    }

    @PutMapping("/{id}")
    @ApiOperation("根据ID更新")
    UserVo update(@PathVariable String id, @Validated @RequestBody UserUpdateRequest userUpdateRequest) {
        return userMapper.toVo(userService.update(id, userUpdateRequest));
    }

    @DeleteMapping("/{id}")
    @ApiOperation("根据ID删除")
    void delete(@PathVariable String id) {
        userService.delete(id);
    }


    @GetMapping("/me")
    UserVo me() {
        return userMapper.toVo(userService.getCurrentUser());
    }
}
