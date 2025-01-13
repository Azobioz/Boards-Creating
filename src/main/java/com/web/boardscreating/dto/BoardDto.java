package com.web.boardscreating.dto;

import com.web.boardscreating.model.BoardElements;
import com.web.boardscreating.model.Element;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import static com.web.boardscreating.mapper.BoardMapper.mapToBoard;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardDto {

    private Long id;
    private String name;
    private List<BoardElements> boardElements;

    public void addInBoardElements(Element element) {
        BoardElements boardElements = new BoardElements(null, mapToBoard(this), element);
        this.boardElements.add(boardElements);
    }
}
