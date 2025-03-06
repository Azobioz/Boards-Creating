package com.web.boardscreating.controller;


import com.web.boardscreating.dto.BoardDto;
import com.web.boardscreating.dto.UserEntityDto;
import com.web.boardscreating.model.UserEntity;
import com.web.boardscreating.service.BoardService;
import com.web.boardscreating.service.MyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@Transactional
@RequestMapping("/boards")
public class BoardsController {

    private final BoardService boardService;
    private final MyUserService userService;

    @Autowired
    public BoardsController(BoardService boardService, MyUserService userService) {
        this.boardService = boardService;
        this.userService = userService;
    }

    @GetMapping
    public List<BoardDto> boardListForm() {
        return boardService.getAllBoardsFromUser();
    }

    @PostMapping("/create")
    public String createNewBoard(@RequestBody BoardDto board) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("User not authenticated");
        }
        String username = authentication.getName();
        UserEntityDto user = userService.findByUsername(username);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        board.setUserId(user.getId());
        boardService.saveBoard(board);
        return "new board is created";
    }

    @GetMapping("/{boardId}")
    public BoardDto createBoardForm(@PathVariable Long boardId) {
        return boardService.findBoardById(boardId);
    }

    @DeleteMapping("/{boardId}/delete")
    public String deleteBoard(@PathVariable Long boardId) {
        String  boardName = boardService.findBoardById(boardId).getName();
        boardService.deleteBoardById(boardId);
        return boardName + " is deleted";
    }

    @PutMapping("/{boardId}/edit")
    public String editBoard(@PathVariable Long boardId, @RequestBody BoardDto board) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("User not authenticated");
        }
        String username = authentication.getName();
        UserEntityDto user = userService.findByUsername(username); // Добавь UserRepository как поле
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        board.setUserId(user.getId());
        boardService.editBoardById(boardId, board);
        return boardService.findBoardById(boardId).getName() +  " is edited";
    }

}
