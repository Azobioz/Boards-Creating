package com.web.boardscreating.service;

import com.web.boardscreating.model.Element;

public interface ElementService {

    Element findElementById(Long id);
    void saveElement(Element element);
    void deleteElementById(Long id);
    void setElementType(String elementType, Long id);
}
