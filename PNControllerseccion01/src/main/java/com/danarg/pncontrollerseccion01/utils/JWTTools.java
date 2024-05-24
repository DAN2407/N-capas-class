package com.danarg.pncontrollerseccion01.utils;

import com.danarg.pncontrollerseccion01.domain.entities.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JWTTools {
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.exptime}")
    private Integer exp;

    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();

        return Jwts.builder()
                .claims(claims)
                .subject(user.getEmail())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + exp))
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .compact();
    }
}
