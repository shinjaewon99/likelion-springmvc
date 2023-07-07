package com.example.likelion.week14day5;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.sql.Date;
import java.time.Instant;

@Slf4j
@Component
public class JwtTokenUtils {
    private final Key sigingKey;
    public JwtTokenUtils(@Value("${jwt.secret}")
                         String jwtSecret) {

        this.sigingKey = Keys.hmacShaKeyFor(
                Decoders.BASE64.decode(jwtSecret)
        );
    }
    
    
    // 주어진 사용자 바탕으로 JWT를 문자열로 생성
    public String generateToken(UserDetails userDetails){

        // Claims : JWT 토큰에 담기는 정보의 단위를 Claim이라 부른다.

        Claims jwtClaims = Jwts.claims()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(Instant.now().plusSeconds(3600)));

        return Jwts.builder()
                .setClaims(jwtClaims)
                .signWith(sigingKey)
                .compact();
    }
}
