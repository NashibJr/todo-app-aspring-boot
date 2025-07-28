package com.example.todo.todo.utils;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.*;

import com.example.todo.todo.entities.UserEntity;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JwtUtil {
    private static String jwtSecretString = "hhgdhRfhgvcdnc_dhdbhQbOdsQWDVvnjIf83LsbdsAA";
    private final Key jwtSecret = Keys.hmacShaKeyFor(
            jwtSecretString.getBytes(StandardCharsets.UTF_8));
    private long tokenLife = 60 * 60 * 1000 * 2;

    public String getToken(String userId) {
        return Jwts.builder()
                .setSubject(userId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + tokenLife))
                .signWith(jwtSecret)
                .compact();
    }

    public boolean verifyToken(UserEntity user, String token) {
        String payload = Jwts.parserBuilder()
                .setSigningKey(jwtSecret)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();

        return user.getId() == payload;
    }
}
