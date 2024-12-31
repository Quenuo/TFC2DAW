package com.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
//Esta clase es para generar un token enlazado con el id de un usuario
@Component
public class JwtUtil {

    @Value("${jwtKey}")
    private String secretKey;

    @Value("${jwtKeyExpiration}")
    private long keyExpiration;

    private SecretKey key;

    @PostConstruct
    public void init() {
        key = Keys.hmacShaKeyFor(secretKey.getBytes());
    }
    //genero un token con enlazado con el id del usuario mi key, que durara una 1 hora(despues el usuario
    //Tendra que iniciar sesion de nuevo
    public String generateToken(String userId) {
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder()
                .claims(claims)
                .subject(userId)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + keyExpiration))
                .signWith(key)
                .compact();
    }

    public String validateToken(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return claims.getSubject();
    }
}
