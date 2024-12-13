package com.web.boardscreating.dto;

import com.web.boardscreating.model.Element;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardDto {

    private Long id;
    private String name;
    private List<Element> inBoard = new ArrayList<>();


    public void addInBoard(Element element) {
        inBoard.add(element);
    }
}
