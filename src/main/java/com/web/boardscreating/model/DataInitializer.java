package com.web.boardscreating.model;

import com.web.boardscreating.model.Element;
import com.web.boardscreating.repository.ElementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private ElementRepository elementRepository;

    @Override
    public void run(String... args) throws Exception {
        if (elementRepository.count() == 0) {
            for (Element.Element_Type type : Element.Element_Type.values()) {
                Element element = new Element();
                element.setElement_Type(type);
                elementRepository.save(element);
            }
        }
    }
}