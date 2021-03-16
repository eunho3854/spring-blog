package com.cos.blog.domain.reply;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import com.cos.blog.domain.post.Post;
import com.cos.blog.domain.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Reply {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false, length = 200)
	private String content;
	
	// 외래키의 주인은 자식
	// 하나의 게시글에 여러개의 댓글
	// 연관 관계가 유저, 포스트
	
	// 유저
	@ManyToOne // Post가 Many, User가 One
	@JoinColumn(name = "userId")
	private User user;
	
	// 포스트
	@ManyToOne // Post가 Many, User가 One
	@JoinColumn(name = "postId")
	private Post post;
	
	@CreationTimestamp
	private Timestamp createDate;
}
