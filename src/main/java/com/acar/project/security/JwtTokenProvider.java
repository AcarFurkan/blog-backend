package com.acar.project.security;


import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;


import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${blog.app.secret}")
    private String APP_SECRET;

    @Value("${blog.app.jwtExpirationInMs}")
    private int JWT_EXPIRATION_IN_MS;

    public String generateJwtToken(Authentication authentication) {
        JwtUserDetails userDetails = (JwtUserDetails) authentication.getPrincipal();
        Date expiryDate = new Date(new Date().getTime() + JWT_EXPIRATION_IN_MS);
        return Jwts.builder()
                 .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(expiryDate)
                .signWith(getSigningKey(), SignatureAlgorithm.HS512) // <-- This can be helpful to you
                .compact();


    }
    private Key getSigningKey() {
        byte[] keyBytes = this.APP_SECRET.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }


    Long getUserIdFromJwt(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(APP_SECRET).build().parseClaimsJws(token).getBody();
        return Long.parseLong(claims.getSubject());
    }

    boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(APP_SECRET).build().parseClaimsJws(token);
            return !isTokenExpired(token);
        } catch (MalformedJwtException | ExpiredJwtException | UnsupportedJwtException | IllegalArgumentException e) {
            return false;
        }
    }


    private boolean isTokenExpired(String token) {
        Date expiration = Jwts.parserBuilder().setSigningKey(APP_SECRET).build().parseClaimsJws(token).getBody().getExpiration();
        return expiration.before(new Date());
    }

}
