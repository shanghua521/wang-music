package com.wang.music.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.wang.music.config.SecurityConfig;
import com.wang.music.dto.TokenCreateRequest;
import com.wang.music.dto.UserCreateRequest;
import com.wang.music.dto.UserDto;
import com.wang.music.dto.UserUpdateRequest;
import com.wang.music.entity.User;
import com.wang.music.exception.BizException;
import com.wang.music.exception.ExceptionType;
import com.wang.music.mapper.UserMapper;
import com.wang.music.repository.UserRepository;
import com.wang.music.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    final UserRepository userRepository;
    final UserMapper userMapper;
    final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto create(UserCreateRequest userCreateRequest) {
        checkUserName(userCreateRequest.getUsername());
        User user = userMapper.createEntity(userCreateRequest);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userMapper.toDto(userRepository.save(user));
    }

    @Override
    public User loadUserByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if (!user.isPresent()) throw new BizException(ExceptionType.USER_NOT_FOUNT);
        return user.get();
    }

    @Override
    public UserDto get(String id) {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) throw new BizException(ExceptionType.USER_NOT_FOUNT);
        return userMapper.toDto(user.get());
    }

    @Override
    public UserDto update(String id, UserUpdateRequest userUpdateRequest) {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) throw new BizException(ExceptionType.USER_NOT_FOUNT);
        return userMapper.toDto(userRepository.save(userMapper.updateEntity(user.get(), userUpdateRequest)));
    }

    @Override
    public void delete(String id) {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) throw new BizException(ExceptionType.USER_NOT_FOUNT);
        userRepository.deleteById(user.get().getId());
    }

    @Override
    public Page<UserDto> search(Pageable pageable) {
        return userRepository.findAll(pageable).map(userMapper::toDto);
    }

    @Override
    public String createToken(TokenCreateRequest tokenCreateRequest) {
        User user = loadUserByUsername(tokenCreateRequest.getUsername());
        // 密码也对
        if (!passwordEncoder.matches(tokenCreateRequest.getPassword(), user.getPassword())) {
            throw new BizException(ExceptionType.USER_PASSWORD_NOT_MATCH);
        }
        // 如果为启用
        if (!user.isEnabled()) {
            throw new BizException(ExceptionType.USER_NOT_ENABLE);
        }
        if (!user.isAccountNonLocked()) {
            throw new BizException(ExceptionType.USER_LOCKED);
        }
        return JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConfig.EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(SecurityConfig.SECRET));
    }

    @Override
    public UserDto getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = loadUserByUsername(authentication.getName());
        return userMapper.toDto(currentUser);
    }

    private void checkUserName(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            throw new BizException(ExceptionType.USER_NAME_DUPLICATE);
        }
    }

}
