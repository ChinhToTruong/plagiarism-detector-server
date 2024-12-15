package zev.plagiarismdetectorserver.service.impl;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import zev.plagiarismdetectorserver.service.JwtService;

@Service
public class JwtServiceImpl implements JwtService {

  @Value("${jwt.time-expired}")
  private Long timeExpired;

  @Value("${jwt.secret-key}")
  private String secretKey;

  @Override
  public String generateToken(UserDetails user) {
    return generrateToken(new HashMap<>(), user);
  }

  @Override
  public String extractUsername(String token) {
    return extractClaim(token, Claims::getSubject).toString();
  }

  @Override
  public boolean isValid(String token, UserDetails user) {
    return false;
  }

  private String generrateToken(Map<String, Object> claims, UserDetails user) {
    return Jwts.builder()
        .setClaims(claims)
        .setSubject(user.getUsername())
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() + timeExpired))
        .signWith(getKey(), SignatureAlgorithm.HS256)
        .compact();
  }

  private Key getKey() {
    byte[] keyBytes = Decoders.BASE64.decode(secretKey);

    return Keys.hmacShaKeyFor(keyBytes);
  }

  private <T> Object extractClaim(String token, Function<Claims, T> claimResolver) {
    final Claims claims = extractAllClaims(token);
    return claimResolver.apply(claims);
  }

  private Claims extractAllClaims(String token) {
    return Jwts.parser().build().parseClaimsJws(token).getBody();
  }
}
