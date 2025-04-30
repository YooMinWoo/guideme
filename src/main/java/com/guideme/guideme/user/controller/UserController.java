package com.guideme.guideme.user.controller;

import com.guideme.guideme.global.dto.ApiResponse;
import com.guideme.guideme.security.jwt.JwtUtil;
import com.guideme.guideme.security.user.CustomUserDetails;
import com.guideme.guideme.user.domain.Role;
import com.guideme.guideme.user.domain.User;
import com.guideme.guideme.user.dto.MyPageDto;
import com.guideme.guideme.user.dto.TokenDto;
import com.guideme.guideme.user.dto.SignupDto;
import com.guideme.guideme.user.dto.UserDto;
import com.guideme.guideme.user.mapper.UserMapper;
import com.guideme.guideme.user.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/my")
    public ResponseEntity<?> my(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        MyPageDto myPageDto = UserMapper.toMyPageDto(customUserDetails.getUser());
        return ResponseEntity.status(HttpStatus.OK.value()).body(ApiResponse.success("my!", myPageDto));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> userSignup(@RequestBody SignupDto signupDto) {
        userService.userSignup(signupDto.getUser());
        return ResponseEntity.status(HttpStatus.OK.value()).body(ApiResponse.success("회원가입 성공!", null));
    }

    @PostMapping("/guide/signup")
    public ResponseEntity<?> guideSignup(@RequestBody SignupDto signupDto) {
        userService.guideSignup(signupDto.getUser(), signupDto.getBusiness());
        return ResponseEntity.status(HttpStatus.OK.value()).body(ApiResponse.success("회원가입 성공!", null));
    }

    @PostMapping("/admin/signup")
    public ResponseEntity<?> adminSignup(@RequestBody SignupDto signupDto) {
        userService.adminSignup(signupDto.getUser(), signupDto.getAdminCode());
        return ResponseEntity.status(HttpStatus.OK.value()).body(ApiResponse.success("회원가입 성공!", null));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDto userDto, HttpServletResponse response) {
        TokenDto tokenDto = userService.login(userDto);
        response.setHeader("Set-Cookie", tokenDto.getRefreshToken());
        return ResponseEntity.status(HttpStatus.OK.value()).body(ApiResponse.success("로그인 성공!", tokenDto));
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) throw new RuntimeException("No cookies found");

        String refreshToken = Arrays.stream(cookies)
                .filter(cookie -> "refreshToken".equals(cookie.getName()))
                .findFirst()
                .map(Cookie::getValue)
                .orElseThrow(() -> new RuntimeException("No refresh token found"));

        TokenDto tokenDto = userService.refreshToken(refreshToken);
        response.addHeader("Set-Cookie", tokenDto.getRefreshToken());
        return ResponseEntity.status(HttpStatus.OK.value()).body(ApiResponse.success("로그인 성공!", tokenDto));
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/isLogin")
    public ResponseEntity<?> isLogin(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        return ResponseEntity.status(HttpStatus.OK.value()).body(ApiResponse.success("isLogin", null));
    }

}
