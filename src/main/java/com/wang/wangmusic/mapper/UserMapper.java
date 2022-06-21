package com.wang.wangmusic.mapper;

import com.wang.wangmusic.dto.UserCreateRequest;
import com.wang.wangmusic.dto.UserDto;
import com.wang.wangmusic.dto.UserUpdateRequest;
import com.wang.wangmusic.entity.User;
import com.wang.wangmusic.vo.UserVo;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(User user);

    UserVo toVo(UserDto userDto);

    User createEntity(UserCreateRequest userCreateRequest);

    User updateEntity(@MappingTarget User user, UserUpdateRequest userUpdateRequest);
}
