package com.board.mine.dto;

import java.time.LocalDateTime;

import com.board.mine.entity.BoardEntity;
import com.board.mine.entity.CommentEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CommentDto {
	
    private Long id;
    private String writer;
    private String content;
    private Long boardId;
    private LocalDateTime createdDate;
    
    public CommentEntity toEntity(){
    	CommentEntity boardEntity = CommentEntity.builder()
                .id(id)
                .writer(writer)
                .contents(content)
                .build();
        return boardEntity;
    }

    @Builder
    public CommentDto(Long id, String title, String content, String writer, LocalDateTime createdDate, Long boardId) {
        this.id = id;
        this.writer = writer;
        this.content = content;
        this.createdDate = createdDate;
        this.boardId = boardId;

    }

}
