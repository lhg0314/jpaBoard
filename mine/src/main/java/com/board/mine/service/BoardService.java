package com.board.mine.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.board.mine.dto.BoardDto;
import com.board.mine.dto.CommentDto;
import com.board.mine.entity.BoardEntity;
import com.board.mine.entity.CommentEntity;
import com.board.mine.repository.BoardRepository;
import com.board.mine.repository.BoardRepositoryQuerydsl;
import com.board.mine.repository.CommentRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class BoardService {
	private BoardRepository boardRepository;
	private BoardRepositoryQuerydsl boardRepositoryQuerydsl;
	private CommentRepository commentRepository;

	@Transactional
	public List<BoardDto> getBoardlist() {

		//List<BoardEntity> boardEntities = boardRepositoryQuerydsl.getBoardList();
		List<BoardEntity> boardEntities = boardRepository.findAll();
		List<BoardDto> boardDtoList = new ArrayList<>();

		for ( BoardEntity boardEntity : boardEntities) {
			BoardDto boardDTO = BoardDto.builder()
					.id(boardEntity.getId())
					.title(boardEntity.getTitle())
					.content(boardEntity.getContents())
					.writer(boardEntity.getWriter())
					.createdDate(boardEntity.getCreateDate())
					.updateDate(boardEntity.getUpdateDate())
					.build();

			boardDtoList.add(boardDTO);
		}

		
		
		return boardDtoList;
	}
	@Transactional
	public BoardDto getBoardDetail(Long id) {
		Optional<BoardEntity> boardEntityWrapper = boardRepository.findById(id);
		BoardEntity boardEntity = boardEntityWrapper.get();

		BoardDto boardDTO = BoardDto.builder()
				.id(boardEntity.getId())
				.title(boardEntity.getTitle())
				.content(boardEntity.getContents())
				.writer(boardEntity.getWriter())
				.createdDate(boardEntity.getCreateDate())
				.updateDate(boardEntity.getUpdateDate())
				.comments(new ArrayList<CommentDto>())
				.build();
		
		
		List<CommentEntity> commentList = commentRepository.findByBoard(boardEntity);
		
		for(CommentEntity comment : commentList) {
			CommentDto commentDto = CommentDto.builder()
					.id(comment.getId())
					.writer(comment.getWriter())
					.content(comment.getContents())
					.createdDate(comment.getCreateDate())
					.build();
			
			boardDTO.addComment(commentDto);
		}

		return boardDTO;
	}
	

	@Transactional
	public Long savePost(BoardDto boardDto) {
		return boardRepository.save(boardDto.toEntity()).getId();
	}
	
	@Transactional
	public Long saveComment(CommentDto commentDto) {
		Optional<BoardEntity> boardEntityWrapper = boardRepository.findById(commentDto.getBoardId());
		BoardEntity boardEntity = boardEntityWrapper.get();
		CommentEntity commententitiy = CommentEntity
				.builder()
				.board(boardEntity)
				.writer(commentDto.getWriter())
				.contents(commentDto.getContent())
				.build();
		return commentRepository.save(commententitiy).getId();
	}

	@Transactional
	public void deletePost(Long id) {
		boardRepository.deleteById(id);
	}


}