package com.guideme.guideme.user.controller;

import com.guideme.guideme.global.dto.ApiResponse;
import com.guideme.guideme.user.domain.Role;
import com.guideme.guideme.user.dto.TokenDto;
import com.guideme.guideme.user.dto.SignupDto;
import com.guideme.guideme.user.dto.UserDto;
import com.guideme.guideme.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> userSignup(@RequestBody SignupDto signupDto) throws Exception {
        signupDto.getUser().setRole(Role.ROLE_USER);
        userService.userSignup(signupDto.getUser());
        return ResponseEntity.status(HttpStatus.OK.value()).body(ApiResponse.success("회원가입 성공!", null));
    }

    @PostMapping("/guide/signup")
    public ResponseEntity<?> guideSignup(@RequestBody SignupDto signupDto) throws Exception {
        signupDto.getUser().setRole(Role.ROLE_GUIDE);
        userService.guideSignup(signupDto.getUser(), signupDto.getBusiness());
        return ResponseEntity.status(HttpStatus.OK.value()).body(ApiResponse.success("회원가입 성공!", null));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDto userDto){
        TokenDto tokenDto = userService.login(userDto);
        return ResponseEntity.status(HttpStatus.OK.value()).body(ApiResponse.success("로그인 성공!", tokenDto));
    }
}
