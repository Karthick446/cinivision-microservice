package com.example.karthick.userService.core.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class TokenVerifierFilter extends OncePerRequestFilter {

    private static final String SECRET_KEY = "secret_secret_secret_secret_secret_secret_secret_secret_secret_secret";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String path = request.getRequestURI();

        // Skip token verification for public endpoints
        if (path.startsWith("/api/user/users")) {
            filterChain.doFilter(request, response);
            return;
        }

        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7); // Remove Bearer prefix

            try {
                Jws<Claims> jwsClaims = Jwts.parserBuilder()
                        .setSigningKey(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()))
                        .build()
                        .parseClaimsJws(token);

                Claims body = jwsClaims.getBody();
                String email = body.getSubject();

                if (email != null && !email.isEmpty()) {
                    List<Map<String, String>> authorities = (List<Map<String, String>>) body.get("authorities");

                    Set<SimpleGrantedAuthority> grantedAuthorities = authorities.stream()
                            .map(a -> new SimpleGrantedAuthority(a.get("authority")))
                            .collect(Collectors.toSet());

                    Authentication authentication = new UsernamePasswordAuthenticationToken(email, null, grantedAuthorities);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }

            } catch (JwtException | IllegalArgumentException e) {
                SecurityContextHolder.clearContext();  // Clear context on invalid token
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Invalid or Expired Token!");
                return;
            }
        }

        filterChain.doFilter(request, response);
    }
}
