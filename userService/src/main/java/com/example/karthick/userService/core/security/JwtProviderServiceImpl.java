package com.example.karthick.userService.core.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class JwtProviderServiceImpl implements JwtProviderService {

    @Value("${jwt.secret.key}")
    private String key;

    @PostConstruct
    public void validateKey() {
        if (key == null || key.length() < 32) {
            throw new IllegalArgumentException("JWT secret key must be at least 32 characters long for HS256.");
        }
    }

    @Override
    public String generateToken(Authentication authentication) {

        Instant now = Instant.now();
        Instant expiry = now.plus(2, ChronoUnit.WEEKS);

        String token = Jwts.builder()
                .claim("authorities", authentication.getAuthorities())
                .setSubject(authentication.getName())
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(expiry))
                .signWith(Keys.hmacShaKeyFor(key.getBytes(StandardCharsets.UTF_8)))
                .compact();

        return "Bearer " + token;  // Consistently return with Bearer prefix
    }
}
