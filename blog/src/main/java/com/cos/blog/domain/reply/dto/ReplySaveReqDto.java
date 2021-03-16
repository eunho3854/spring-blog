package com.cos.blog.domain.reply.dto;

import com.cos.blog.domain.reply.Reply;
import com.cos.blog.domain.user.User;

import lombok.Data;

@Data
public class ReplySaveReqDto {
	private User user;
	private String content;
	private int postId;
	
	public Reply toEntity() {
		return Reply.builder()
				.content(content)
				.user(user)
				.build();
	}
}
