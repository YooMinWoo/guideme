//package com.guideme.guideme.security.jwt;
//
//import com.guideme.guideme.security.handler.CustomAuthenticationFailureHandler;
//import com.guideme.guideme.security.handler.CustomAuthenticationSuccessHandler;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//
//import java.io.IOException;
//
//public class LoginFilter extends UsernamePasswordAuthenticationFilter {
//
//    private final AuthenticationManager authenticationManager;
//    private final JwtUtil jwtUtil;
//    private final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
//    private final CustomAuthenticationFailureHandler customAuthenticationFailureHandler;
//    private final String loginUrl = "/loginProcess";
//
//    public LoginFilter(AuthenticationManager authenticationManager, JwtUtil jwtUtil , CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler, CustomAuthenticationFailureHandler customAuthenticationFailureHandler) {
//        this.authenticationManager = authenticationManager;
//        this.jwtUtil = jwtUtil;
//        this.customAuthenticationSuccessHandler = customAuthenticationSuccessHandler;
//        this.customAuthenticationFailureHandler = customAuthenticationFailureHandler;
//        setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher(loginUrl, "POST"));
//    }
//
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
//
//        // 클라이언트 요청에서 username(회원 아이디), password 추출
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//
//        // 검증을 위해 token에 담음
//        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,password);
//
//        // token에 담은 검증을 위한 AuthenticationManager로 전달
//        return authenticationManager.authenticate(authenticationToken);
//    }
//
//    // 로그인 성공시 실행하는 메서드(여기서 jwt 발급)
//    @Override
//    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
//        customAuthenticationSuccessHandler.onAuthenticationSuccess(request,response,authentication);
//    }
//
//    // 로그인 실패시 실행하는 메서드
//    @Override
//    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
//        customAuthenticationFailureHandler.onAuthenticationFailure(request,response,exception);
//    }
//
//}
