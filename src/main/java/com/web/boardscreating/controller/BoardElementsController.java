package com.web.boardscreating.controller;

import com.web.boardscreating.dto.BoardDto;
import com.web.boardscreating.model.Element;
import com.web.boardscreating.service.BoardService;
import com.web.boardscreating.service.ElementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/boards/{boardId}/element")
public class BoardElementsController {

    @Autowired
    private BoardService boardService;
    @Autowired
    private ElementService elementService;

    public BoardElementsController(BoardService boardService, ElementService elementService) {
        this.boardService = boardService;
        this.elementService = elementService;
    }


    @PostMapping("/add/block")
    public String createBlock(@PathVariable Long boardId) {

        BoardDto boardDto = boardService.findBoardById(boardId);
        Element blockElement = new Element();
        elementService.saveElement(blockElement);
        elementService.setElementType("BLOCK", blockElement.getId());
        boardDto.addInBoard(blockElement);
        boardService.saveBoard(boardDto);
        return "Block is created";
    }

}
