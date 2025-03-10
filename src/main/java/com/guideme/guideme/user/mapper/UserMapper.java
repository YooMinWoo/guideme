package com.guideme.guideme.user.mapper;

import com.guideme.guideme.user.domain.User;
import com.guideme.guideme.user.dto.MyPageDto;
import com.guideme.guideme.user.dto.UserDto;

public class UserMapper {

    public static MyPageDto toMyPageDto(User user){
        return MyPageDto.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .name(user.getName())
                .mobile(user.getMobile())
                .build();
    }

    public static User toUserEntity(UserDto userDto){
        return User.builder()
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .email(userDto.getEmail())
                .name(userDto.getName())
                .mobile(userDto.getMobile())
                .role(userDto.getRole())
                .build();
    }
}
