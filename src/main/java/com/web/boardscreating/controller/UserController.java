package com.web.boardscreating.controller;

import com.web.boardscreating.dto.CredentialsDto;
import com.web.boardscreating.dto.SignUpDto;
import com.web.boardscreating.dto.UserEntityDto;
import com.web.boardscreating.exception.AppException;
import com.web.boardscreating.service.MyUserService;
import com.web.boardscreating.security.UserAuthProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.hibernate5.SpringSessionContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Map;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class UserController {

    private final MyUserService userService;
    private final UserAuthProvider userAuthProvider;

    @PostMapping("/register")
    public ResponseEntity<UserEntityDto> register(@RequestBody SignUpDto signUpDto) throws AppException {
        UserEntityDto user = userService.register(signUpDto);
        String accessToken = userAuthProvider.createAccessToken(user.getUsername());
        String refreshToken = userAuthProvider.createRefreshToken(user.getUsername());
        userService.updateTokens(user.getUsername(), accessToken, refreshToken);
        user.setToken(accessToken);
        user.setRefreshToken(refreshToken);
        return ResponseEntity.created(URI.create("/users/" + user.getId())) // по этому url могу найти нового пользователя
                .body(user);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<UserEntityDto> authenticate(@RequestBody CredentialsDto credentialsDto) throws AppException {
        UserEntityDto user = userService.login(credentialsDto);
        String accessToken = userAuthProvider.createAccessToken(user.getUsername());
        String refreshToken = userAuthProvider.createRefreshToken(user.getUsername());
        userService.updateTokens(user.getUsername(), accessToken, refreshToken);
        user.setToken(accessToken);
        user.setRefreshToken(refreshToken);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/refresh") // чтобы обновлять токен
    public ResponseEntity<UserEntityDto> refresh(@RequestBody String refreshToken) throws AppException {
        if (refreshToken == null || refreshToken.trim().isEmpty()) {
            throw new AppException("Refresh token is missing or empty", HttpStatus.BAD_REQUEST);
        }
        try {
            Authentication auth = userAuthProvider.validateToken(refreshToken.trim());
            String username = auth.getName();
            UserEntityDto user = userService.findByUsername(username);
            String newAccessToken = userAuthProvider.createAccessToken(username);
            userService.updateTokens(username, newAccessToken, refreshToken); // Обновляем токены
            user.setToken(newAccessToken);
            user.setRefreshToken(refreshToken);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            throw new AppException("Invalid or expired refresh token: " + e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/current-user")
    public UserEntityDto getCurrentUser(Authentication authentication) throws AppException {
        if (authentication == null && !authentication.isAuthenticated()) {
            throw new AppException("User not authenticated", HttpStatus.UNAUTHORIZED);
        }
        String username  = authentication.getName();
        return userService.findByUsername(username);
    }


}
