package com.guideme.guideme.user.dto;

import com.guideme.guideme.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MyPageDto {

    private String username;
    private String name;
    private String email;
    private String mobile;

}
