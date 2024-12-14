package com.web.boardscreating.service.serviceimpl;

import com.web.boardscreating.model.Element;

import com.web.boardscreating.repository.ElementRepository;
import com.web.boardscreating.service.ElementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ElementServiceImpl implements ElementService {

    @Autowired
    private ElementRepository elementRepository;

    public ElementServiceImpl(ElementRepository elementRepository) {
        this.elementRepository = elementRepository;
    }


    @Override
    public Element findElementById(Long id) {
        return elementRepository.findById(id).get();
    }

    @Override
    public  void saveElement(Element element) {
        elementRepository.save(element);
    }

    @Override
    public void deleteElementById(Long id) {
        elementRepository.deleteById(id);
    }

    @Override
    public void setElementType(String elementType, Long id) {
        Element element = findElementById(id);
        element.setElement_Type(Element.Element_Type.valueOf(elementType));
    }
}
