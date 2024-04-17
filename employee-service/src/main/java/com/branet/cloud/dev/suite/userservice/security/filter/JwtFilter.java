package com.branet.cloud.dev.suite.userservice.security.filter;

import com.branet.cloud.dev.suite.userservice.security.util.JwtUtil;
import com.branet.cloud.dev.suite.userservice.security.util.UserDetailsImpl;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/** The type Jwt filter. */
@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
  private final JwtUtil jwtUtil = new JwtUtil();

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    if (request.getHeader("Authorization") != null) {
      String token = getToken(request);

      if (jwtUtil.isTokenExpired(token)) {
        throw new RuntimeException("Token is expired");
      }
      UserDetailsImpl userDetails =
          UserDetailsImpl.builder()
              .id(jwtUtil.extractClaim(token, claims -> claims.get("id", Long.class)))
              .email(jwtUtil.extractClaim(token, Claims::getSubject))
              .role(jwtUtil.extractClaim(token, claims -> claims.get("position", String.class)))
              .build();

      Authentication authentication =
          new UsernamePasswordAuthenticationToken(
              userDetails, userDetails.getUsername(), userDetails.getAuthorities());
      SecurityContextHolder.getContext().setAuthentication(authentication);
    }
    filterChain.doFilter(request, response);
  }

  private String getToken(HttpServletRequest request) {
    String bearer = request.getHeader("Authorization");
    String[] parts = bearer.split(" ");
    if (!"Bearer".equals(parts[0])) {
      throw new RuntimeException("Invalid token");
    }

    return parts[1];
  }
}
