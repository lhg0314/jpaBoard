package com.board.mine.repository;

import java.util.Arrays;
import java.util.List;


import org.springframework.stereotype.Repository;

import com.board.mine.entity.BoardEntity;
import com.board.mine.entity.CommentEntity;
import com.board.mine.entity.QBoardEntity;
import com.board.mine.entity.QCommentEntity;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class BoardRepositoryQuerydslImpl implements BoardRepositoryQuerydsl{
	
	private final JPAQueryFactory queryFactory;

	@Override
	public List<BoardEntity> getBoardList() {
		
		QBoardEntity qboard = QBoardEntity.boardEntity;
		JPAQuery<BoardEntity> query = queryFactory.selectFrom(qboard);
		List<BoardEntity> result = query.fetch();
		
		System.out.println("목록 조회 결과 :" + result);
		
		return result;
		
	

	}

	@Override
	public BoardEntity getBoardDetail(Long id) {
		// TODO Auto-generated method stub
		QBoardEntity qboard = QBoardEntity.boardEntity;
		QCommentEntity qcomment = QCommentEntity.commentEntity;
		
		List<Tuple> boardEntity = queryFactory
				.select(qboard,qcomment)
				.from(qboard)
				.innerJoin(qboard.comments,qcomment)
				.where(qboard.id.eq(id))
				.fetch();
		System.out.println("꺼내보자");
		for(Tuple tu : boardEntity) {
			System.out.println(tu);
			BoardEntity title = tu.get(qboard);

			System.out.println(title);
			System.out.println(tu.get(qcomment).getContents());
		
			
		}
		
		return null;
	}

}
