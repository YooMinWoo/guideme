package com.guideme.guideme.user.domain;

import com.guideme.guideme.global.entity.BaseEntity;
import com.guideme.guideme.user.dto.MyPageDto;
import com.guideme.guideme.user.dto.UserDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    private String username;
    private String password;
    private String name;
    private String email;
    private String mobile;

    @Enumerated(EnumType.STRING)
    private Role role;  // ROLE_ADMIN, ROLE_USER, ROLE_GUIDE

    @Builder
    public User(String username, String password, String name, String email, String mobile, Role role) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.role = role;
    }

}
