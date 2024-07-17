package com.board.mine.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "board")
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SequenceGenerator (
	name = "BOARD_SEQ_GENERATOR",
    sequenceName = "BOARD_SEQ",	//매핑할 데이터 베이스 스퀀스 이름
    initialValue=1, allocationSize=1)
public class BoardEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,
		generator="BOARD_SEQ_GENERATOR")
	@Column(name="BOARD_ID")
	private Long id;
	
	@Column(name="WRITER", length = 10, nullable = false)
	private String writer;//작성자
	
	@Column(name="TITLE", length= 20, nullable = false)
	private String title;
	
	@Column(name="CONTENTS", length= 500, nullable = false)
	private String contents;
	
	@CreatedDate
	@Column(name="CREATE_DATE", columnDefinition="DATE", nullable = true, updatable=false)
	private LocalDateTime createDate;
	
	
	@LastModifiedDate
	@Column(name="UPDATE_DATE", columnDefinition="DATE", nullable = true)
	private LocalDateTime updateDate;
	
	
	
	@Builder
    public BoardEntity(Long id, String writer, String title, String contents) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.contents = contents;
    }

}
