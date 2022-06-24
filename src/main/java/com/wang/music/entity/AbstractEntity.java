package com.wang.music.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@MappedSuperclass
public abstract class AbstractEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "ksuid")
    @GenericGenerator(name="ksuid",strategy = "com.wang.music.utils.KsUidIdentifierGenerator")
    private String id;

    @CreationTimestamp
    @Column(name = "created_time")
    private Date createTime;

    @UpdateTimestamp
    @Column(name = "updated_time")
    private Date updatedTime;

}
