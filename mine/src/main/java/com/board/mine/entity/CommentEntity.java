package com.board.mine.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "comments")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class) // 추가
@SequenceGenerator (
		name = "COMMENT_SEQ_GENERATOR",
		sequenceName = "COMMENT_SEQ",	//매핑할 데이터 베이스 스퀀스 이름
		initialValue=1, allocationSize=1)
public class CommentEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,
	generator="COMMENT_SEQ_GENERATOR")
	@Column(name="COMMENT_ID")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BOARD_ID", nullable = false) 
	private BoardEntity board;

	@Column(name="WRITER", length = 10, nullable = false)
	private String writer;//작성자 

	@Column(name="CONTENTS", length= 500, nullable = false)
	private String contents;

	@CreatedDate
	@Column(name="CREATE_DATE", columnDefinition="DATE", nullable = true, updatable=false)
	private LocalDateTime createDate;
	




}
