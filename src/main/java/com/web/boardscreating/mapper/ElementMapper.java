package com.web.boardscreating.mapper;

import com.web.boardscreating.dto.ElementDto;
import com.web.boardscreating.model.Element;

public class ElementMapper {

    public static Element mapToElement(ElementDto elementDto) {
        Element element = Element.builder()
                .id(elementDto.getId())
                .elementType(elementDto.getElementType())
                .boardElements(elementDto.getBoardElements())
                .build();
        return element;
    }

    public static ElementDto mapToElementDto(Element element) {
        ElementDto elementDto = ElementDto.builder()
                .id(element.getId())
                .elementType(element.getElementType())
                .boardElements(element.getBoardElements())
                .build();
        return elementDto;
    }

}
