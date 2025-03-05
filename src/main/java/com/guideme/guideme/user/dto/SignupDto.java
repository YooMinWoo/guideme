package com.guideme.guideme.user.dto;

import com.guideme.guideme.user.domain.Role;
import lombok.Data;

@Data
public class SignupDto {
    private String username;
    private String password;
    private Role role;
}
