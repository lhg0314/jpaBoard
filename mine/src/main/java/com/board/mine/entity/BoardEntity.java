package com.board.mine.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "board")
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class) // 추가
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

	@OneToMany(mappedBy = "board", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@OrderBy("id asc")
	private List<CommentEntity> comments = new ArrayList<CommentEntity>();
	
	
	public void update(String title, String contents) {
		this.title = title;
		this.contents = contents;	
	}

	public void addComments(CommentEntity child) {
		comments.add(child);
		child.setBoard(this);
    }


}
