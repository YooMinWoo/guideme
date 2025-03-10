package com.guideme.guideme.user.dto;

import com.guideme.guideme.user.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor  // 기본 생성자 추가
@AllArgsConstructor
public class UserDto {

    private Long user_id;
    private String username;
    private String password;
    private String name;
    private String email;
    private String mobile;
    private Role role;
}
