package com.web.boardscreating.controller;


import com.web.boardscreating.dto.BoardDto;
import com.web.boardscreating.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/boards")
public class BoardsController {

    public BoardService boardService;

    @Autowired
    public BoardsController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping
    public List<BoardDto> boardListForm(Model model) {
        List<BoardDto> boardList = boardService.findAll();
        model.addAttribute("boards", boardList);
        return boardList;
    }

    @PostMapping("/create")
    public String createNewBoard() {

        BoardDto board = new BoardDto();
        board.setName("Board");
        board = boardService.save(board);

        return "redirect:/boards/" + board.getId();
    }

    @GetMapping("/{boardId}")
    public String createBoardForm(Model model, @PathVariable Long boardId) {
        BoardDto boardDto = boardService.findById(boardId);

        model.addAttribute("board", boardDto);
        return "board";
    }

}
