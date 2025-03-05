package com.guideme.guideme.user.dto;

import com.guideme.guideme.user.domain.Role;
import lombok.Data;

@Data
public class UserDto {

    private Long id;
    private String username;
    private String password;
    private String name;
    private String email;
    private String mobile;
    private Role role;
}
