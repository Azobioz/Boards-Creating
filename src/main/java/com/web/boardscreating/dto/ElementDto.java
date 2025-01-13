package com.web.boardscreating.dto;

import com.web.boardscreating.model.Board;
import com.web.boardscreating.model.BoardElement;
import com.web.boardscreating.model.Element;
import com.web.boardscreating.model.Element_Type;
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
    private Element_Type element_Type;
    private List<BoardElement> boardElements = new ArrayList<>();


}
