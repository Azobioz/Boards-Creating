package com.web.boardscreating.service.serviceimpl;

import com.web.boardscreating.dto.BoardDto;
import com.web.boardscreating.model.Board;
import com.web.boardscreating.repository.BoardReporitory;
import com.web.boardscreating.service.BoardService;
import lombok.Builder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.web.boardscreating.mapper.BoardMapper.*;

@Builder
@Service
public class BoardServiceImpl implements BoardService {

    private BoardReporitory boardReporitory;

    public BoardServiceImpl(BoardReporitory boardReporitory) {
        this.boardReporitory = boardReporitory;
    }

    @Override
    public BoardDto save(BoardDto boardDto) {
        Board board = mapToBoard(boardDto);
        boardReporitory.save(board);
        BoardDto newBoardDto = BoardDto.builder()
                .id(board.getId())
                .name(board.getName())
                .build();
        return newBoardDto;
    }

    @Override
    public List<BoardDto> findAll() {
       List<Board> boards = boardReporitory.findAll();
       return boards.stream().map(board -> mapToBoardDto(board))
               .collect(Collectors.toList());
    }

    @Override
    public BoardDto findById(Long id) {
        Optional<Board> board = boardReporitory.findById(id);
        return mapToBoardDto(board.get());
    }

}
