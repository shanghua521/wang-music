package com.wang.wangmusic.repository;

import com.wang.wangmusic.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

    User getByUsernameAndNickname(String username, String nickname);

    User getByUsername(String username);
}
