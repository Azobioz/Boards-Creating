package com.web.boardscreating.controller;


import com.web.boardscreating.dto.BoardDto;
import com.web.boardscreating.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/boards")
public class BoardsController {

    public BoardService boardService;

    @Autowired
    public BoardsController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping
    public List<BoardDto> boardListForm() {
        return boardService.getAllBoards();
    }

    @PostMapping("/create")
    public String createNewBoard(@RequestBody BoardDto board) {
        boardService.saveBoard(board);
        return "new board created";
    }

    @GetMapping("/{boardId}")
    public BoardDto createBoardForm(@PathVariable Long boardId) {
        return boardService.findBoardById(boardId);
    }

    @DeleteMapping("/{boardId}/delete")
    public String deleteBoard(@PathVariable Long boardId) {
        boardService.deleteBoardById(boardId);
        return "board + " + boardId + " deleted";
    }

}
