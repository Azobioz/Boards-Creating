package com.web.boardscreating.service;


import com.web.boardscreating.dto.BoardDto;
import com.web.boardscreating.model.Board;

import java.util.List;

import com.web.boardscreating.dto.BoardDto;
import com.web.boardscreating.model.Board;

public interface BoardService {

    BoardDto save(BoardDto boardDto);
    List<BoardDto> findAll();
    BoardDto findById(Long id);


}
