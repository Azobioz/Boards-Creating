package com.web.boardscreating.dto;


import com.web.boardscreating.model.Board;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEntityDto {

    private Long id;

    private String username;
    private String password;
    private String email;
    private String token;
    private String refreshToken;

    private Set<BoardDto> boards;

}
