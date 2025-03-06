package com.web.boardscreating.mapper;

import com.web.boardscreating.dto.BoardDto;
import com.web.boardscreating.model.Board;

public class BoardMapper {


    public static BoardDto mapToBoardDto(Board board) {
        BoardDto boardDto = BoardDto.builder()
                .id(board.getId())
                .name(board.getName())
                .boardElements(board.getBoardElements())
                .userId(board.getUser() != null ? board.getUser().getId() : null)
                .build();
        return boardDto;
    }

    public static Board mapToBoard(BoardDto boardDto) {
        Board board = Board.builder()
                .id(boardDto.getId())
                .name(boardDto.getName() != null && !boardDto.getName().isEmpty() ? boardDto.getName() : "Board")
                .boardElements(boardDto.getBoardElements())
                .build();
        return board;
    }

}
