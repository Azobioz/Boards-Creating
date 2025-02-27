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

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class UserController {

    private final MyUserService userService;
    private final UserAuthProvider userAuthProvider;

    @PostMapping("/register")
    public ResponseEntity<UserEntityDto> register(@RequestBody SignUpDto signUpDto) throws AppException {
        UserEntityDto user = userService.register(signUpDto);
        user.setToken(userAuthProvider.createToken(user.getUsername()));
        return ResponseEntity.created(URI.create("/users/" + user.getId())) // по этому url могу найти нового пользователя
                .body(user);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<UserEntityDto> authenticate(@RequestBody CredentialsDto credentialsDto) throws AppException {
        UserEntityDto user = userService.login(credentialsDto);

        user.setToken(userAuthProvider.createToken(user.getUsername()));
        return ResponseEntity.ok(user);
    }

    @GetMapping("/current-user")
    public UserEntityDto getUser()  {
    }

}
