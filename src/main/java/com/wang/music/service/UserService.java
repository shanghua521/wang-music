package com.wang.music.service;

import com.wang.music.dto.TokenCreateRequest;
import com.wang.music.dto.UserCreateRequest;
import com.wang.music.dto.UserDto;
import com.wang.music.dto.UserUpdateRequest;
import com.wang.music.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    UserDto create(UserCreateRequest userCreateRequest);

    @Override
    User loadUserByUsername(String username);

    UserDto get(String id);

    UserDto update(String id, UserUpdateRequest userUpdateRequest);

    void delete(String id);

    Page<UserDto> search(Pageable pageable);

    String createToken(TokenCreateRequest tokenCreateRequest);

    UserDto getCurrentUser();
}
