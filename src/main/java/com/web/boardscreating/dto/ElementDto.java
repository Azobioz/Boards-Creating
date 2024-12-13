package com.web.boardscreating.dto;

import com.web.boardscreating.model.Board;
import com.web.boardscreating.model.Element;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ElementDto {

    private Long id;
    Element.Element_Type element_Type;
    List<Board> elements = new ArrayList<>();

    public void addElements(Board board) {
        elements.add(board);
    }

}
