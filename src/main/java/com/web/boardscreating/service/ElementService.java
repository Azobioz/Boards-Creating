package com.web.boardscreating.service;

import com.web.boardscreating.model.Element;

import com.web.boardscreating.model.Element_Type;
import com.web.boardscreating.repository.ElementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ElementService {

    @Autowired
    private ElementRepository elementRepository;

    public ElementService(ElementRepository elementRepository) {
        this.elementRepository = elementRepository;
    }

    public Element findElementById(Long id) {
        return elementRepository.findById(id).get();
    }

    public  void saveElement(Element element) {
        elementRepository.save(element);
    }

    public void deleteElementById(Long id) {
        elementRepository.deleteById(id);
    }

    public void setElementType(String elementType, Long id) {
        Element element = findElementById(id);
        element.setElementType(Element_Type.valueOf(elementType));
    }
}
