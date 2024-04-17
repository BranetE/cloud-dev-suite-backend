package com.branet.cloud.dev.suite.userservice.security.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import java.util.function.Function;
import javax.crypto.SecretKey;
import org.springframework.stereotype.Component;

/** The type Jwt util. */
@Component
public class JwtUtil {

  private final String secret = "6a627a7fb025e2c5db643267523a1c801c1178bed30331a2606fe93f4dd9aa7b";

  /**
   * Is token expired boolean.
   *
   * @param token the token
   * @return the boolean
   */
  public boolean isTokenExpired(String token) {
    return extractClaim(token, Claims::getExpiration).before(new Date());
  }

  /**
   * Extract claim t.
   *
   * @param <T> the type parameter
   * @param token the token
   * @param claimResolver the claim resolver
   * @return the t
   */
  public <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
    Claims claims = extractAllClaims(token);
    return claimResolver.apply(claims);
  }

  private Claims extractAllClaims(String token) {
    return Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(token).getPayload();
  }

  private SecretKey getSigningKey() {
    byte[] keyBytes = Decoders.BASE64.decode(secret);
    return Keys.hmacShaKeyFor(keyBytes);
  }
}
