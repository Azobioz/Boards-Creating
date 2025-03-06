package com.web.boardscreating.mapper;

import com.web.boardscreating.dto.SignUpDto;
import com.web.boardscreating.dto.UserEntityDto;
import com.web.boardscreating.model.UserEntity;

import java.util.stream.Collectors;

import static com.web.boardscreating.mapper.BoardMapper.mapToBoardDto;


public class UserEntityMapper {

    public static UserEntityDto mapToUserEntityDto(UserEntity user) {
        return UserEntityDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .token(user.getToken())
                .refreshToken(user.getRefreshToken())
                .email(user.getEmail())
                .build();
    }

    public static UserEntity mapToUserEntityFromSignUpDto(SignUpDto signUpDto) {
        return UserEntity.builder()
                .username(signUpDto.getUsername())
                .email(signUpDto.getEmail())
                .build();
    }

}
