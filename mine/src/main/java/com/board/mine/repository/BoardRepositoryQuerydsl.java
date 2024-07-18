package com.board.mine.repository;

import java.util.List;

import com.board.mine.entity.BoardEntity;

public interface BoardRepositoryQuerydsl {
	
	public List<BoardEntity> getBoardList();
	

	public BoardEntity getBoardDetail(Long id);

}
