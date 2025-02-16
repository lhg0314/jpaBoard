package com.board.mine.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.board.mine.entity.BoardEntity;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
	
	 @Query("select be from BoardEntity be where be.writer= :writer and be.id = :id")
	    List<BoardEntity> findwriter(@Param("writer") String writer, @Param("id") long id);
}