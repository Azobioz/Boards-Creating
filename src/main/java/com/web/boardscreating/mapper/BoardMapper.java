package com.web.boardscreating.mapper;

import com.web.boardscreating.dto.BoardDto;
import com.web.boardscreating.model.Board;

public class BoardMapper {


    public static BoardDto mapToBoardDto(Board board) {
        BoardDto boardDto = BoardDto.builder()
                .id(board.getId())
                .name(board.getName())
                .build();
        return boardDto;
    }

    public static Board mapToBoard(BoardDto boardDto) {
        Board board = Board.builder()
                .id(boardDto.getId())
                .name(boardDto.getName())
                .build();
        return board;
    }

}
