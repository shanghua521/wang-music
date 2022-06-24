package com.wang.music.vo;

import com.wang.music.entity.Role;
import com.wang.music.enums.Gender;
import lombok.Data;

import java.util.List;

@Data
public class UserVo {

    private String id;

    private String username;

    private String nickname;

    private Gender gender;

    private Boolean locked;

    private Boolean enabled;

    private List<Role> roles;
}
