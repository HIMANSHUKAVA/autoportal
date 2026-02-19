package com.main.Security;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

    private static final String SECRET =
        "my-super-secret-jwt-key-which-is-very-secure-256bit";

    //  ONE SINGLE KEY OBJECT
    private final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());

    //  TOKEN GENERATE
    public String generatetoken(String username, String role) {

    	 return Jwts.builder()
    	            .setSubject(username)
    	            .claim("role", "ROLE_" + role)   // 🔥 FIX HERE
    	            .setIssuedAt(new Date())
    	            .setExpiration(
    	                    new Date(System.currentTimeMillis() + 86400000)
    	            )
    	            .signWith(key, SignatureAlgorithm.HS256)
    	            .compact();
        
    }

    //  TOKEN VALIDATE
    public Claims validateToken(String token) {

        return Jwts.parserBuilder()
                .setSigningKey(key)     //  SAME KEY
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
