package com.web.boardscreating.dto;

import com.web.boardscreating.model.Board;
import com.web.boardscreating.model.Element;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ElementDto {

    private Long id;
    Element.Element_Type element_Type;
    Set<Board> elements = new HashSet<>();

    public ElementDto(String whatType) {
        this.element_Type = Element.Element_Type.valueOf(whatType);
    }

    public void addElements(Board board) {
        elements.add(board);
    }

}
