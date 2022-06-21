package com.wang.wangmusic.service;

import com.wang.wangmusic.dto.UserCreateRequest;
import com.wang.wangmusic.dto.UserDto;
import com.wang.wangmusic.dto.UserUpdateRequest;
import com.wang.wangmusic.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Arrays;
import java.util.List;

public interface UserService extends UserDetailsService {

    UserDto create(UserCreateRequest userCreateRequest);

    @Override
    User loadUserByUsername(String username);

    UserDto get(String id);

    UserDto update(String id, UserUpdateRequest userUpdateRequest);

    void delete(String id);

    Page<UserDto> search(Pageable pageable);
}
