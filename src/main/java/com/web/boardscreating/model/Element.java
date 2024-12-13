package com.web.boardscreating.model;


import jakarta.persistence.*;
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
@Entity
@Table(name="element")
public class Element {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    Element_Type element_Type;


    @ManyToMany(mappedBy = "inBoard")
    List<Board> elements = new ArrayList<>();

    public enum Element_Type {
        BLOCK, CIRCLE, TRIANGLE, STICKY_NOTE
    }

    public void setElements(Board board) {
        elements.add(board);
    }

}

