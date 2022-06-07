package com.wang.wangmusic.entity;

import com.wang.wangmusic.enums.Gender;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@ToString(callSuper = true)
public class User extends AbstractEntity implements Serializable {

    @Column(name = "username", unique = true, nullable = false, length = 64)
    private String username;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "password", nullable = false, length = 64)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @Column(name = "email")
    private String email;

    @Column(name = "locked")
    private Boolean locked;

    @Column(name = "enabled")
    private Boolean enable;

    @Column(name = "last_login_ip")
    private String lasLoginIp;

    @Column(name = "last_login_time")
    private Date lastLoginTime;

    @ManyToMany(cascade = {}, fetch = FetchType.LAZY)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))

    @ToString.Exclude
    private List<Role> roles;
}
