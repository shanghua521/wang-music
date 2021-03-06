package com.wang.music.entity;

import com.wang.music.enums.Gender;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
@DynamicInsert
@DynamicUpdate
@RequiredArgsConstructor
@ToString(callSuper = true)
@Table(name = "`user`")
public class User extends AbstractEntity implements Serializable, UserDetails {

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

    @Column(name = "locked", columnDefinition = "tinyint default 0")
    private Boolean locked = false;

    @Column(name = "enabled", columnDefinition = "tinyint default 1")
    private Boolean enable = true;

    @Column(name = "last_login_ip")
    private String lasLoginIp;

    @Column(name = "last_login_time")
    private Date lastLoginTime;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    @ToString.Exclude
    private List<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    // ???????????????????????????
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // ???????????????????????????
    @Override
    public boolean isAccountNonLocked() {
        return !getLocked();
    }

    // ?????????????????????????????????
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // ????????????????????????
    @Override
    public boolean isEnabled() {
        return getEnable();
    }
}
