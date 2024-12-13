package com.web.boardscreating.repository;

import com.web.boardscreating.model.Element;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ElementRepository extends JpaRepository<Element, Long> {
}
