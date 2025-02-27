package com.web.boardscreating.service;

import com.web.boardscreating.dto.CredentialsDto;
import com.web.boardscreating.dto.SignUpDto;
import com.web.boardscreating.dto.UserEntityDto;
import com.web.boardscreating.exception.AppException;
import com.web.boardscreating.mapper.UserEntityMapper;
import com.web.boardscreating.model.UserEntity;
import com.web.boardscreating.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;

import static com.web.boardscreating.mapper.UserEntityMapper.mapToUserEntityDto;
import static com.web.boardscreating.mapper.UserEntityMapper.mapToUserEntityFromSignUpDto;

@Service
@RequiredArgsConstructor
public class MyUserService { //будет обрабатывать логин и регистрацию

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserEntityDto findByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("No such username: " + username);
        }
        return mapToUserEntityDto(user);
    }

    public UserEntityDto login(CredentialsDto credentialsDto) throws AppException {
        UserEntity user = userRepository.findByUsername(credentialsDto.getUsername());

        if (user == null) {
            throw new UsernameNotFoundException("No such username: " + credentialsDto.getUsername());
        }

        if (passwordEncoder.matches(CharBuffer.wrap(credentialsDto.getPassword()), user.getPassword())) {
            return mapToUserEntityDto(user);
        }

        throw new AppException("Invalid password", HttpStatus.BAD_REQUEST);
    }

    public UserEntityDto register(SignUpDto userDto) throws AppException {
        UserEntity userEntity = userRepository.findByUsername(userDto.getUsername());

        if (userEntity != null) {
            throw new AppException("Login already exists", HttpStatus.BAD_REQUEST);
        }

        UserEntity user = mapToUserEntityFromSignUpDto(userDto);

        user.setPassword(passwordEncoder.encode(CharBuffer.wrap(userDto.getPassword())));

        UserEntity savedUser = userRepository.save(user);
        return mapToUserEntityDto(savedUser);
    }

}
