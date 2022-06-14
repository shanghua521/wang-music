package com.wang.wangmusic.service;

import com.wang.wangmusic.dto.UserCreateDto;
import com.wang.wangmusic.dto.UserDto;
import com.wang.wangmusic.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UserService extends UserDetailsService {

    List<UserDto> list();

    UserDto create(UserCreateDto userCreateDto);

    @Override
    User loadUserByUsername(String username);
}
