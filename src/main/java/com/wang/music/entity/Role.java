package com.wang.music.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "role")
@RequiredArgsConstructor
@ToString(callSuper = true)
public class Role extends AbstractEntity {
    @Column(name = "name", length = 128)
    private String name;

    @Column(name = "title", length = 128)
    private String title;

}