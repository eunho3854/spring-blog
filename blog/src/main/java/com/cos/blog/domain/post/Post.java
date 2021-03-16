package com.cos.blog.domain.post;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import com.cos.blog.domain.reply.Reply;
import com.cos.blog.domain.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Post {

	@Id // Primary Key
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; // 시퀀스, auto_increment
	
	@Column(nullable = false, length = 100)
	private String title;
	
	@Lob // 대용량 데이터
	private String content;
	
	@ColumnDefault("0")
	private int count;
	
	@ManyToOne // Post가 Many, User가 One
	@JoinColumn(name = "userId")
	private User user;
	
	// 양방향 매핑
	// 댓글 리스트
	// "post"는 변수명, mappedBy -> 난 연관관계의 주인이 아니야 !
	// 내가 list를 select할 때 reply를 다 들고오는 게 아니라 상세보기 할 때 reply를 보기 때문에 LAZY
	// 게시글이 삭제되면 관려된 댓글을 다 삭제 CascadeType.REMOVE
	// post가 무한 참조되므로 @JsonIgnoreProperties 사용, dto 써도 됨.
	// 내가 post를 다이렉트로 select할 때는 사용하지 않는다 @OrderBy
	@OneToMany(mappedBy = "post", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE) // Post가 Many, User가 One
	@JoinColumn(name = "replyId")
	@JsonIgnoreProperties({"post"})
	@OrderBy("id desc")
	private List<Reply> replys;
	
	@CreationTimestamp
	private Timestamp createDate;
	
}
