package com.web.boardscreating.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.web.boardscreating.dto.UserEntityDto;
import com.web.boardscreating.service.MyUserService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Collections;
import java.util.Date;
import java.util.Map;


@RequiredArgsConstructor
@Component
public class UserAuthProvider {

    @Value("${security.jwt.token.secret-key:secret-value")
    private String secretKey;

    private final MyUserService userService;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createAccessToken(String username) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + 3_600_000L); // 1 час

        return JWT.create()
                .withIssuer(username)
                .withIssuedAt(now)
                .withExpiresAt(validity)
                .sign(Algorithm.HMAC256(secretKey));
    }

    public String createRefreshToken(String username) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + 2_592_000_000L); // 30 дней

        return JWT.create()
                .withIssuer(username)
                .withIssuedAt(now)
                .withExpiresAt(validity)
                .sign(Algorithm.HMAC256(secretKey));
    }

    public Authentication validateToken(String token) {
        if (token == null || token.trim().isEmpty()) {
            throw new IllegalArgumentException("Token is missing or empty");
        }
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secretKey))
                .build();

        DecodedJWT decoded;
        try {
            decoded = verifier.verify(token.trim());
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid token format: " + e.getMessage());
        }

        String username = decoded.getIssuer(); // Извлекаем имя пользователя как строку
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("Issuer is missing in token");
        }

        UserEntityDto user = userService.findByUsername(username); // Проверяем, что пользователь существует

        return new UsernamePasswordAuthenticationToken(username, null, Collections.emptyList());
    }

}
