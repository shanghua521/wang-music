package com.wang.wangmusic.dto;

import com.wang.wangmusic.entity.Role;
import lombok.Data;

import java.util.List;

@Data
public class UserDto {

    private String id;

    private String username;

    private String nickname;

    private List<Role> roles;

}
