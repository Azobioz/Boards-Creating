package com.web.boardscreating.service;

import com.web.boardscreating.dto.BoardDto;
import com.web.boardscreating.model.Board;
import com.web.boardscreating.repository.BoardRepository;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.web.boardscreating.mapper.BoardMapper.*;

@Builder
@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public void saveBoard(BoardDto boardDto) {
        Board board = mapToBoard(boardDto);
        boardRepository.save(board);
    }

    public void deleteBoardById(Long boardId) {
        boardRepository.deleteById(boardId);
    }


    public void editBoardById(Long id, BoardDto boardDto) {
        Board editedBoard = boardRepository.findById(id).get();
        editedBoard.setName(boardDto.getName());
        boardRepository.save(editedBoard);
    }

    public List<BoardDto> getAllBoards() {
       List<Board> boards = boardRepository.findAll();
       return boards.stream().map(board -> mapToBoardDto(board))
               .collect(Collectors.toList());
    }

    public BoardDto findBoardById(Long id) {
        Optional<Board> board = boardRepository.findById(id);
        return mapToBoardDto(board.get());
    }

}
