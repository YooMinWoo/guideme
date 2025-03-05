package com.guideme.guideme.user.dto;

import com.guideme.guideme.business.dto.BusinessDto;
import lombok.Data;

@Data
public class SignupDto {

    private UserDto user;
    private BusinessDto business;

}
