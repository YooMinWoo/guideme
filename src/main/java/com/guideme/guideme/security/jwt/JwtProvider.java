//package com.guideme.guideme.security.jwt;
//
//import io.jsonwebtoken.JwtException;
//import io.jsonwebtoken.Jwts;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//import javax.crypto.SecretKey;
//import javax.crypto.spec.SecretKeySpec;
//import java.nio.charset.StandardCharsets;
//import java.security.Key;
//import java.util.Date;
//
//@Component
//public class JwtProvider {
//
//    private final SecretKey secretKey;
//
//    public JwtProvider(@Value("${spring.jwt.secret}")String secretKey) {
//        this.secretKey = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), Jwts.SIG.HS256.key().build().getAlgorithm());
//    }
//
//    public String createJwt(String category, String username, String role, Long expiredMs) {
//
//        return Jwts.builder()
//                .claim("category", category)
//                .claim("username", username)
//                .claim("role", role)
//                .issuedAt(new Date(System.currentTimeMillis()))
//                .expiration(new Date(System.currentTimeMillis() + expiredMs))
//                .signWith(secretKey)
//                .compact();
//    }
//
//    // JWT 토큰 검증
//    public boolean validateToken(String token) {
//        return
//            Jwts.parser()
//                    .verifyWith(secretKey)
//                    .build()
//                    .parseSignedClaims(token)
//                    .getPayload()
//                    .getExpiration()
//                    .before(new Date());
//    }
//
//    public String getUsername(String token) {
//        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("username", String.class);
//    }
//
//    public String getCategory(String token) {
//
//        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("category", String.class);
//    }
//
//    public String getRole(String token) {
//        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("role", String.class);
//    }
//}
//
