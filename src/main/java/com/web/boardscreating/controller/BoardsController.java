package com.web.boardscreating.controller;


import com.web.boardscreating.dto.BoardDto;
import com.web.boardscreating.model.Board;
import com.web.boardscreating.repository.BoardReporitory;
import com.web.boardscreating.service.BoardService;
import com.web.boardscreating.service.serviceimpl.BoardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class BoardsController {

    public BoardService boardService;

    @Autowired
    public BoardsController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/boards")
    public String boardListForm(Model model) {
        List<BoardDto> boardList = boardService.findAll();
        model.addAttribute("boards", boardList);
        return "board-list";
    }

    @PostMapping("/boards/create")
    public String createNewBoard(Model model) {

        BoardDto board = new BoardDto();
        board.setName("default");
        board = boardService.save(board);

        return "redirect:/boards/" + board.getId();
    }

    @GetMapping("/boards/{boardId}")
    public String createBoardForm(Model model, @PathVariable Long boardId) {
        BoardDto boardDto = boardService.findById(boardId);

        model.addAttribute("board", boardDto);
        return "board";
    }

}
