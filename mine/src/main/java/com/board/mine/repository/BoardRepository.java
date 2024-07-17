package com.board.mine.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.board.mine.entity.BoardEntity;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
}