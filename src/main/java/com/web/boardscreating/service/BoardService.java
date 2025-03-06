package com.web.boardscreating.service;

import com.web.boardscreating.dto.BoardDto;
import com.web.boardscreating.model.Board;
import com.web.boardscreating.model.UserEntity;
import com.web.boardscreating.repository.BoardRepository;
import com.web.boardscreating.repository.UserRepository;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.web.boardscreating.mapper.BoardMapper.*;

@Builder
@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private UserRepository userRepository;

    public BoardService(BoardRepository boardRepository, UserRepository userRepository) {
        this.boardRepository = boardRepository;
        this.userRepository = userRepository;
    }

    public void saveBoard(BoardDto boardDto) {
        Board board = mapToBoard(boardDto);
        if (boardDto.getUserId() != null) {
            UserEntity user = userRepository.findById(boardDto.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            board.setUser(user);
        }
        boardRepository.save(board);
    }

    public void deleteBoardById(Long boardId) {
        boardRepository.deleteById(boardId);
    }


    public void editBoardById(Long id, BoardDto boardDto) {
        Board editedBoard = boardRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Board not found"));
        editedBoard.setName(boardDto.getName() != null && !boardDto.getName().isEmpty() ? boardDto.getName() : "Board");
        if (boardDto.getUserId() != null) {
            UserEntity user = userRepository.findById(boardDto.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            editedBoard.setUser(user);
        }
        boardRepository.save(editedBoard);
    }

    public List<BoardDto> getAllBoardsFromUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("User not authenticated");
        }
        String username = authentication.getName();
        UserEntity user = userRepository.findByUsername(username);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        List<Board> boards = boardRepository.findByUserId(user.getId());
        return boards.stream()
                .map(board -> mapToBoardDto(board))
                .collect(Collectors.toList());
    }

    public BoardDto findBoardById(Long id) {
        Optional<Board> board = boardRepository.findById(id);
        return mapToBoardDto(board.get());
    }

}
