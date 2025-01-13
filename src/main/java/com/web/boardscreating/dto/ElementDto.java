package com.web.boardscreating.dto;

import com.web.boardscreating.model.BoardElements;
import com.web.boardscreating.model.Element_Type;
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
    private Element_Type elementType;
    private List<BoardElements> boardElements = new ArrayList<>();


}
