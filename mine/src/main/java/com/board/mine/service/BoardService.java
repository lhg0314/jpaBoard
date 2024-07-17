package com.board.mine.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.board.mine.dto.BoardDto;
import com.board.mine.entity.BoardEntity;
import com.board.mine.repository.BoardRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class BoardService {
	private BoardRepository boardRepository;

	@Transactional
	public List<BoardDto> getBoardlist() {
		List<BoardEntity> boardEntities = boardRepository.findAll();
		List<BoardDto> boardDtoList = new ArrayList<>();
		System.out.println(boardEntities);
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

		System.out.println("----------dddddd---------");
		System.out.println(boardDtoList);
		return boardDtoList;
	}
	@Transactional
	public BoardDto getPost(Long id) {
		Optional<BoardEntity> boardEntityWrapper = boardRepository.findById(id);
		BoardEntity boardEntity = boardEntityWrapper.get();

		BoardDto boardDTO = BoardDto.builder()
				.id(boardEntity.getId())
				.title(boardEntity.getTitle())
				.content(boardEntity.getContents())
				.writer(boardEntity.getWriter())
				.createdDate(boardEntity.getCreateDate())
				.build();

		return boardDTO;
	}

	@Transactional
	public Long savePost(BoardDto boardDto) {
		return boardRepository.save(boardDto.toEntity()).getId();
	}

	@Transactional
	public void deletePost(Long id) {
		boardRepository.deleteById(id);
	}


}