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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "User Controller", description = "유저 컨트롤러입니다.")
public class UserController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/my")
    @Operation(
            summary = "마이페이지",
            description = "마이페이지 조회 API입니다. (유저 권한만 접근 가능)"
    )
    public ResponseEntity<?> my(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        MyPageDto myPageDto = UserMapper.toMyPageDto(customUserDetails.getUser());
        return ResponseEntity.status(HttpStatus.OK.value()).body(ApiResponse.success("my!", myPageDto));
    }

    @PostMapping("/signup")
    @Operation(summary = "유저 회원가입", description = "유저 회원가입 할 때 사용하는 API입니다.")
    public ResponseEntity<?> userSignup(@RequestBody SignupDto signupDto) {
        userService.userSignup(signupDto.getUser());
        return ResponseEntity.status(HttpStatus.OK.value()).body(ApiResponse.success("회원가입 성공!", null));
    }

    @PostMapping("/guide/signup")
    @Operation(
            summary = "가이드 회원가입",
            description = "가이드 회원가입 API입니다."
    )
    public ResponseEntity<?> guideSignup(@RequestBody SignupDto signupDto) {
        userService.guideSignup(signupDto.getUser(), signupDto.getBusiness());
        return ResponseEntity.status(HttpStatus.OK.value()).body(ApiResponse.success("회원가입 성공!", null));
    }

    @PostMapping("/admin/signup")
    @Operation(
            summary = "관리자 회원가입",
            description = "관리자 회원가입 API입니다. (관리자 코드 : guideme-admin_code)"
    )
    public ResponseEntity<?> adminSignup(@RequestBody SignupDto signupDto) {
        userService.adminSignup(signupDto.getUser(), signupDto.getAdminCode());
        return ResponseEntity.status(HttpStatus.OK.value()).body(ApiResponse.success("회원가입 성공!", null));
    }

    @PostMapping("/login")
    @Operation(
            summary = "로그인",
            description = "로그인 API입니다. (access토큰, refresh토큰이 주어짐)"
    )
    public ResponseEntity<?> login(@RequestBody UserDto userDto, HttpServletResponse response) {
        TokenDto tokenDto = userService.login(userDto);
        response.setHeader("Set-Cookie", tokenDto.getRefreshToken());
        return ResponseEntity.status(HttpStatus.OK.value()).body(ApiResponse.success("로그인 성공!", tokenDto));
    }

    @PostMapping("/refresh")
    @Operation(
            summary = "refresh",
            description = "refresh 토큰 값을 통해 access 토큰과 refresh 토큰을 재발급 받는 API입니다. (단, refresh 토큰 값이 일치할 경우)"
    )
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
    @Operation(
            summary = "access토큰 유효 체크",
            description = "access토큰 값이 유효한지 체크하는 API입니다."
    )
    public ResponseEntity<?> isLogin(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        return ResponseEntity.status(HttpStatus.OK.value()).body(ApiResponse.success("isLogin", null));
    }

}
