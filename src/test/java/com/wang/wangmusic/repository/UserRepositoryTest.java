package com.wang.wangmusic.repository;

import com.wang.wangmusic.entity.Role;
import com.wang.wangmusic.entity.User;
import com.wang.wangmusic.enums.Gender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @BeforeEach
    void before() {
        User user = new User();
        user.setUsername("殇花");
        user.setNickname("程序员殇花");
        user.setEnable(true);
        user.setLocked(false);
        user.setLocked(false);
        user.setPassword("9527");
        user.setGender(Gender.FEMALE);
        user.setLastLoginTime(new Date());
        userRepository.save(user);

        Role role = new Role();
        role.setName("aaa");
        role.setName("aaa");
        roleRepository.save(role);

        user.setRoles(Collections.singletonList(role));
        userRepository.save(user);
        userRepository.flush();
    }

    @Test
    void findByUsername() {
        User dbUser = userRepository.getByUsername("殇花");
        System.out.println(dbUser.getRoles());
    }

}