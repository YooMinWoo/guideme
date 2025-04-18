package com.guideme.guideme.init;

import com.guideme.guideme.business.domain.Business;
import com.guideme.guideme.business.dto.BusinessDto;
import com.guideme.guideme.business.mapper.BusinessMapper;
import com.guideme.guideme.business.repository.BusinessRepository;
import com.guideme.guideme.user.domain.Role;
import com.guideme.guideme.user.domain.User;
import com.guideme.guideme.user.dto.UserDto;
import com.guideme.guideme.user.mapper.UserMapper;
import com.guideme.guideme.user.repository.UserRepository;
import com.guideme.guideme.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserInit {

    private final UserService userService;

    @Transactional
    public User guideSignup(){
        UserDto guideDto = UserDto.builder()
                .username("alsn99")
                .password("12345678")
                .email("alsn0527@naver.com")
                .name("유민우")
                .mobile("010-9204-5515")
                .build();

        BusinessDto businessDto = BusinessDto.builder()
                .userId(guideDto.getUser_id())
                .tradeName("유민우의 주식회사")
                .registrationNumber("010-123-4566")
                .businessMobile("010-1234-5515")
                .businessEmail("alsn9914@gmail.com")
                .information("유민우가 가이드해드립니다 ㅋㅋ")
                .build();

        return userService.guideSignup(guideDto, businessDto);
    }

    @Transactional
    public User adminSignup(){
        UserDto guideDto = UserDto.builder()
                .username("alsn98")
                .password("12345678")
                .email("alsn0527@naver.com")
                .name("유민우")
                .mobile("010-9204-5515")
                .build();

        String adminCode = "guideme-admin_code";
        return userService.adminSignup(guideDto,adminCode);
    }

    @Transactional
    public User userSignup(){
        UserDto userDto = UserDto.builder()
                .username("alsn97")
                .password("12345678")
                .email("alsn0527@naver.com")
                .name("유민우")
                .mobile("010-9204-5515")
                .build();

        return userService.userSignup(userDto);

    }
}
