package com.wang.music.repository;

import com.wang.music.entity.Role;
import com.wang.music.entity.User;
import com.wang.music.enums.Gender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

@DataJpaTest
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

        user.setRoles(new ArrayList<>(Collections.singletonList(role)));
        userRepository.save(user);
        userRepository.flush();
    }

    @Test
    void findByUsername() {
        User dbUser = userRepository.getByUsername("殇花");
        System.out.println(dbUser);
        System.out.println(dbUser.getRoles());
    }

}