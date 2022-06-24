package com.wang.music.repository;

import com.wang.music.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    User getByUsernameAndNickname(String username, String nickname);

    User getByUsername(String username);

    User getById(String id);

    Optional<User> findByUsername(String username);

    Page<User> findAll(Pageable pageable);
}
