package com.web.boardscreating.service;


import com.web.boardscreating.dto.BoardDto;

import java.util.List;

public interface BoardService {

    void saveStudent(BoardDto boardDto);
    List<BoardDto> getAllBoards();
    BoardDto findBoardById(Long id);


}
