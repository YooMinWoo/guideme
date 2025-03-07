//package com.guideme.guideme.security.jwt;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.guideme.guideme.global.dto.ApiResponse;
//import com.guideme.guideme.security.service.CustomUserDetailsService;
//import io.jsonwebtoken.ExpiredJwtException;
//import io.jsonwebtoken.JwtException;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//
//@RequiredArgsConstructor
//public class JwtFilter2 extends OncePerRequestFilter {
//
//    private final JwtProvider jwtProvider;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//
//        //request에서 access 헤더를 찾음
//        String accessToken = request.getHeader("access");
//        ApiResponse<?> apiResponse = null;
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        //access 헤더 검증
//        if (accessToken == null) {
//            filterChain.doFilter(request, response);
//            return;
//        }
//        //토큰 소멸 시간 검증
//        try{
//            jwtProvider.validateToken(accessToken);
//            // 토큰이 access인지 확인
//            String category = jwtProvider.getCategory(accessToken);
//
//            if(!category.equals("access")){
//                throw new JwtException("유효하지 않은 토큰입니다.");
//            }
//
//
//
//            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(jwtProvider.getUsername(accessToken),"",jwtProvider.getRole(accessToken));
//
//            //세션에 사용자 등록
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//
//
//            filterChain.doFilter(request, response);
//
//            return;
//        } catch(ExpiredJwtException e){
//            apiResponse = new ApiResponse<>(HttpStatus.UNAUTHORIZED.value(), "토큰이 만료되었습니다.", null);
//            response.setStatus(HttpStatus.UNAUTHORIZED.value());
//        } catch(JwtException e){
//            apiResponse = new ApiResponse<>(HttpStatus.UNAUTHORIZED.value(), "토큰에 에러가 발생했습니다.", null);
//            response.setStatus(HttpStatus.UNAUTHORIZED.value());
//        }
//        response.setContentType("application/json;charset=UTF-8");
//        String responseBody = objectMapper.writeValueAsString(apiResponse);
//        response.getWriter().write(responseBody);
//    }
//}