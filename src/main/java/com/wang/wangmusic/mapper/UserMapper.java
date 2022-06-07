package com.wang.wangmusic.mapper;

import com.wang.wangmusic.dto.UserDto;
import com.wang.wangmusic.entity.User;
import com.wang.wangmusic.vo.UserVo;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(User user);

    UserVo toVo(UserDto userDto);
}
