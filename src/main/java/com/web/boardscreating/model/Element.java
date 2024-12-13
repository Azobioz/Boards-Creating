package com.web.boardscreating.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="element")
public class Element {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    Element_Type Element_Type;

    @ManyToMany(mappedBy = "inBoard")
    List<Board> elements = new ArrayList<>();

}

enum Element_Type {
    BLOCK, CIRCLE, TRIANGLE, STICKY_NOTE
}
