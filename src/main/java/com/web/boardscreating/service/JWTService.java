package com.web.boardscreating.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWTService {

    @Value("${jwt.token}")
    private String SECRET_KEY; // HS256 требует ключ длиной 32 символа

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        String jws =  Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername()) // зарегарнный Claim
                .setIssuedAt(new Date(System.currentTimeMillis())) //зарегарнный Claims
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) //
                .signWith(getSecretBytes(), SignatureAlgorithm.HS256)
                .compact();
        return jws;
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) { //Получаю все Claims
        try {
            return Jwts
                    .parserBuilder() // Создает билдер для парсера JWT
                    .setSigningKey(getSecretBytes()) //// Устанавливает ключ для проверки подписи токена
                    .build() // // Создает парсер
                    .parseClaimsJws(token) // Парсит токен и проверяет его подпись
                    .getBody(); // // Возвращает Claims из токена
        }
        catch (io.jsonwebtoken.security.SignatureException e) {
            System.out.println("Invalid JWT signature: " + e.getMessage());
            throw e;
        } catch (io.jsonwebtoken.ExpiredJwtException e) {
            System.out.println("JWT token has expired: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            System.out.println("Error parsing JWT token: " + e.getMessage());
            throw e;
        }
    }

    private Key getSecretBytes() {
        byte[] keyBytes = SECRET_KEY.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
