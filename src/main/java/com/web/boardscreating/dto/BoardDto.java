package com.web.boardscreating.dto;

import com.web.boardscreating.model.Element;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@Builder
public class BoardDto {

    private Long id;
    private String name;
    private Set<Element> inBoard;

    public BoardDto(Long id, String name, Set<Element> inBoard) {
        this.id = id;
        this.name = name;
        this.inBoard = new HashSet<>();
    }

    public void addInBoard(Element element) {
        inBoard.add(element);
    }
}
