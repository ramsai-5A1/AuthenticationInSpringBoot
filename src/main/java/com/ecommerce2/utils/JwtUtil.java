package com.ecommerce2.utils;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {  

    private String SECRET_KEY = "CDLq9H/a6377VQTxhyhR5dExViK7Fi+d3qoeRgGghEBoVwDzbW99nmsgW+aiEztDMUj/Bck6ga/Hw7hqO34YQA==";
    // private SecretKey secretKey = Keys.hmacShaKeyFor("CDLq9H/a6377VQTxhyhR5dExViK7Fi+d3qoeRgGghEBoVwDzbW99nmsgW+aiEztDMUj/Bck6ga/Hw7hqO34YQA==".getBytes(StandardCharsets.UTF_8));

    public String generateToken(String userName) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userName);
    }

    private String createToken(Map<String, Object> claims, String subject) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + 5 * 60 * 1000);
        System.out.println("Reached till here");

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

}
