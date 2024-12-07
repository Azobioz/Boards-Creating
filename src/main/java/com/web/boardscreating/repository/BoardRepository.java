package com.web.boardscreating.repository;

import com.web.boardscreating.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
