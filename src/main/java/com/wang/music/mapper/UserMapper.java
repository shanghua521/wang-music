package com.wang.music.mapper;

import com.wang.music.dto.UserCreateRequest;
import com.wang.music.dto.UserDto;
import com.wang.music.dto.UserUpdateRequest;
import com.wang.music.entity.User;
import com.wang.music.vo.UserVo;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(User user);

    UserVo toVo(UserDto userDto);

    User createEntity(UserCreateRequest userCreateRequest);

    User updateEntity(@MappingTarget User user, UserUpdateRequest userUpdateRequest);
}
