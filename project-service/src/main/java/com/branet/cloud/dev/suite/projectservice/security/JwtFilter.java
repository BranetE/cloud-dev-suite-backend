package com.branet.cloud.dev.suite.projectservice.security;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String token = getToken(request);

        if(jwtUtil.isTokenExpired(token)){
            throw new RuntimeException("Token is expired");
        }
        UserDetailsImpl userDetails = UserDetailsImpl.builder()
                .id(jwtUtil.extractClaim(token, claims -> claims.get("id", Long.class)))
                .email(jwtUtil.extractClaim(token, Claims::getSubject))
                .role(jwtUtil.extractClaim(token, claims -> claims.get("role", String.class)))
                        .build();

        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }

    private String getToken(HttpServletRequest request){
        String bearer = request.getHeader("Authorization");
        String[] parts = bearer.split(" ");
        if(!"Bearer".equals(parts[0])){
            throw new RuntimeException("Invalid token");
        }

        return parts[1];
    }
}
