package com.web.boardscreating.model;

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
            for (Element_Type type : Element_Type.values()) {
                Element element = new Element();
                element.setElementType(type);
                elementRepository.save(element);
            }
        }
    }
}