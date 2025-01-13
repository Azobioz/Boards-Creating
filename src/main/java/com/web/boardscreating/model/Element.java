package com.web.boardscreating.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="element")
public class Element {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    Element_Type element_type;

    @OneToMany(mappedBy = "element", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BoardElement> boardElements = new ArrayList<>();


}

