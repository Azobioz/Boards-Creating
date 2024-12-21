package com.web.boardscreating.dto;

import com.web.boardscreating.model.BoardElement;
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
    private List<BoardElement> boardElements;

    public void addInBoardElements(Element element) {
        BoardElement boardElement = new BoardElement(null, mapToBoard(this), element);
        boardElements.add(boardElement);
    }
}
