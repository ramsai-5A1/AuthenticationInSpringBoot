package com.ecommerce2.utils;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.ecommerce2.dto.ValidateTokenResponse;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.JwtParserBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {  

    private String SECRET_KEY = "CDLq9H/a6377VQTxhyhR5dExViK7Fi+d3qoeRgGghEBoVwDzbW99nmsgW+aiEztDMUj/Bck6ga/Hw7hqO34YQA==";

    public String generateToken(String userName) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userName);
    }

    private String createToken(Map<String, Object> claims, String subject) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + 5 * 60 * 1000);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(getSiginingKey(), SignatureAlgorithm.HS512)
                .compact();
    }

    private Key getSiginingKey() {
        byte[] keyBytes = Base64.getDecoder().decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private Claims extractAllClaims(String token) {
        JwtParserBuilder jwtParserBuilder = Jwts.parserBuilder(); 
        JwtParser jwtParser = jwtParserBuilder.setSigningKey(getSiginingKey()).build(); 
        Jws<Claims> jwsClaims = jwtParser.parseClaimsJws(token);
        return jwsClaims.getBody();
    }

    public ValidateTokenResponse validateToken(String token) {
        try {
            Claims claims = extractAllClaims(token);
            String extractedUsername = extractUsername(token);
            if (!isTokenExpired(token)) {
                return ValidateTokenResponse.builder()
                    .isValid(true)
                    .userName(extractedUsername)
                    .build();
            }
            return ValidateTokenResponse.builder()
                    .isValid(false)
                    .userName(null)
                    .build();
        } catch (Exception ex) {
            return ValidateTokenResponse.builder()
            .isValid(false)
            .userName(null)
            .build();
        }
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private boolean isTokenExpired(String token) {
        Date expirationDate = extractExpiration(token);
        return expirationDate.before(new Date());
    }

}
