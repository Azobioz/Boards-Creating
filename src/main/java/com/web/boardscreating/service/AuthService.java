package com.web.boardscreating.service;

import com.web.boardscreating.model.UserEntity;
import com.web.boardscreating.repository.UserRepository;
import com.web.boardscreating.security.AuthResponse;
import com.web.boardscreating.security.request.AuthRequest;
import com.web.boardscreating.security.request.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;
    private final AuthenticationManager authManager;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(RegisterRequest registerRequest) {
        for (UserEntity user : userRepository.findAll()) {
            if (registerRequest.getUsername().equals(user.getUsername())) {
                throw new UsernameNotFoundException("User with this username already exists");
            }
        }
        UserEntity user = UserEntity.builder()
                .username(registerRequest.getUsername())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .email(registerRequest.getEmail())
                .build();
        userRepository.save(user);
        String jwtToken = jwtService.generateToken(user);
        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthResponse authenticate(AuthRequest authRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );
        UserEntity user = userRepository.findByUsername(authRequest.getUsername());
        if (user == null) {
            throw new UsernameNotFoundException("Нет такого пользователя: " + authRequest.getUsername());
        }
        String jwtToken = jwtService.generateToken(user);
        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }

}
