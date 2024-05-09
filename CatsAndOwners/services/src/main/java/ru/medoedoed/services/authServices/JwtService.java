package ru.medoedoed.services.authServices;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JwtService {
  @Value("${token.signing.key}")
  private String jwtSigningKey;

  @Value("${token.signing.expiration}")
  private long jwtExpiration;

  public String generateToken(Long userId) {
    return Jwts.builder()
        .claim("id", userId)
        .expiration(new Date(System.currentTimeMillis() + jwtExpiration))
        .signWith(getSigningKey())
        .compact();
  }

  public Long extractId(String token) {
    final Claims claims = extractAllClaims(token);
    return claims.get("id", Long.class);
  }

  private Date extractExpiration(String token) {
    final Claims claims = extractAllClaims(token);
    return claims.getExpiration();
  }

  private boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date());
  }

  public boolean isTokenValid(String token, Long userId) {
    final Long id = extractId(token);
    return (id.equals(userId) && !isTokenExpired(token));
  }

  private Claims extractAllClaims(String token) {
    return Jwts.parser().
            verifyWith(getSigningKey()).
            build().
            parseSignedClaims(token).getPayload();
  }

  private SecretKey getSigningKey() {
    byte[] keyBytes = Decoders.BASE64.decode(jwtSigningKey);
    return Keys.hmacShaKeyFor(keyBytes);
  }
}
