package com.web.boardscreating.mapper;

import com.web.boardscreating.dto.BoardDto;
import com.web.boardscreating.model.Board;

import java.util.ArrayList;

public class BoardMapper {


    public static BoardDto mapToBoardDto(Board board) {
        return BoardDto.builder()
                .id(board.getId())
                .name(board.getName())
                .inBoard(new ArrayList<>(board.getInBoard()))
                .build();

    }

    public static Board mapToBoard(BoardDto boardDto) {
        return Board.builder()
                .id(boardDto.getId())
                .name(boardDto.getName())
                .inBoard(new ArrayList<>(boardDto.getInBoard()))
                .build();
    }

}
