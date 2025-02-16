package com.board.mine.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
public class BoardDto {
    private Long id;
    private String writer;
    private String title;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime updateDate;
    private List<CommentDto> comments;

    public BoardEntity toEntity(){
        BoardEntity boardEntity = BoardEntity.builder()
                .id(id)
                .writer(writer)
                .title(title)
                .contents(content)
                .build();
        return boardEntity;
    }

    @Builder
    public BoardDto(Long id, String title, String content, String writer, LocalDateTime createdDate, LocalDateTime updateDate,List<CommentDto> comments ) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
        this.updateDate = updateDate;
        this.comments = comments;
    }
    
    public void addComment(CommentDto comment) {
    	this.comments.add(comment);
    }
}
