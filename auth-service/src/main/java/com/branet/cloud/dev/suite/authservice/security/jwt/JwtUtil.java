package com.branet.cloud.dev.suite.authservice.security.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import java.util.Map;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/** The type Jwt util. */
@Service
public class JwtUtil {

  private final String secret;
  private final Integer expiration;

  /**
   * Instantiates a new Jwt util.
   *
   * @param secret the secret
   * @param expiration the expiration
   */
  public JwtUtil(
      @Value("${jwt.secret}") String secret, @Value("${jwt.expiration}") Integer expiration) {
    this.secret = secret;
    this.expiration = expiration;
  }

  /**
   * Generate token string.
   *
   * @param extraClaims the extra claims
   * @param username the username
   * @return the string
   */
  public String generateToken(Map<String, Object> extraClaims, String username) {
    return Jwts.builder()
        .claims(extraClaims)
        .subject(username)
        .issuedAt(new Date(System.currentTimeMillis()))
        .expiration(new Date(System.currentTimeMillis() + expiration))
        .signWith(getSigningKey())
        .compact();
  }

  private SecretKey getSigningKey() {
    byte[] keyBytes = Decoders.BASE64.decode(secret);
    return Keys.hmacShaKeyFor(keyBytes);
  }
}
