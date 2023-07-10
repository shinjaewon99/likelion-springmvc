package com.example.likelion.week15day1;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.Instant;
import java.util.Date;

@Component
public class JwtUtils {
    
    private final Key signKey;
    private final JwtParser jwtParser;

    public JwtUtils(
            @Value("${jwt.secret")
            String jwtSecret){

        this.signKey = Keys.hmacShaKeyFor(jwtSecret.getBytes());

        // JWT 번역기 생성
        this.jwtParser = Jwts
                .parserBuilder()
                .setSigningKey(signKey)
                .build();
    }

    // 새로운 JWT를 발급하는 메소드
    public String generateToken(UserDetails userDetails){
        // Claim : JWT에 담길 데이터의 키(맵의 키와 비슷한 용도)
        // 이부분은 JWT에 담고싶은 사용자 정보를 담는다.
        Claims jwtClaims = Jwts.claims()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(Date.from(Instant.now().plusSeconds(3600)));

        return Jwts.builder()
                .setClaims(jwtClaims)
                .signWith(signKey)
                .compact();

    }

    // 1. JWT가 유효한지 검증하는 메소드
    // jjwt 라이브러리에서는 JWT를 해석하는 과정에서
    // 유효하지 않은 경우 예외
    public boolean validate(String token){
        // 정당한 JWT인 경우 True
        // parseClaimsJws : 암호화된 JWT를 해석하기 위한 메소드
        try{
            jwtParser.parseClaimsJws(token);
            return true;
        }catch (Exception e){

            return false;
        }
    }

    // JWT를 인자로 받고, 그 JWT를 해석해서
    // 사용자 정보를 회수하는 메소드
    public Claims parseClaims(String token) {
        return jwtParser
                .parseClaimsJws(token)
                .getBody();
    }
}
