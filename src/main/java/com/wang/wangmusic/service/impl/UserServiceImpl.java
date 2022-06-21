package com.wang.wangmusic.service.impl;

import com.wang.wangmusic.dto.UserCreateRequest;
import com.wang.wangmusic.dto.UserDto;
import com.wang.wangmusic.dto.UserUpdateRequest;
import com.wang.wangmusic.entity.User;
import com.wang.wangmusic.exception.BizException;
import com.wang.wangmusic.exception.ExceptionType;
import com.wang.wangmusic.mapper.UserMapper;
import com.wang.wangmusic.repository.UserRepository;
import com.wang.wangmusic.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

    private void checkUserName(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            throw new BizException(ExceptionType.USER_NAME_DUPLICATE);
        }
    }

}
