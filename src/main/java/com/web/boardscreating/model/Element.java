package com.web.boardscreating.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Builder
@Entity
@AllArgsConstructor
@Table(name="element")
public class Element {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    Element_Type element_Type;
    @ManyToMany(mappedBy = "inBoard")
    Set<Board> elements = new HashSet<>();


    public Element() {

    }


    public enum Element_Type {
        BLOCK, CIRCLE, TRIANGLE, STICKY_NOTE
    }



}

