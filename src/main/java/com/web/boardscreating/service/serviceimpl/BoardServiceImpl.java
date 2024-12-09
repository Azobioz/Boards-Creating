package com.web.boardscreating.service.serviceimpl;

import com.web.boardscreating.dto.BoardDto;
import com.web.boardscreating.model.Board;
import com.web.boardscreating.repository.BoardRepository;
import com.web.boardscreating.service.BoardService;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.web.boardscreating.mapper.BoardMapper.*;

@Builder
@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardRepository boardRepository;

    public BoardServiceImpl(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Override
    public void saveBoard(BoardDto boardDto) {
        Board board = mapToBoard(boardDto);
        boardRepository.save(board);
    }

    @Override
    public List<BoardDto> getAllBoards() {
       List<Board> boards = boardRepository.findAll();
       return boards.stream().map(board -> mapToBoardDto(board))
               .collect(Collectors.toList());
    }

    @Override
    public BoardDto findBoardById(Long id) {
        Optional<Board> board = boardRepository.findById(id);
        return mapToBoardDto(board.get());
    }

}
