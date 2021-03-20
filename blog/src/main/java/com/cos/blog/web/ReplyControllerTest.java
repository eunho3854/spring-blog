package com.cos.blog.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.domain.post.Post;
import com.cos.blog.domain.post.PostRepository;
import com.cos.blog.domain.reply.ReplyRepository;
import com.cos.blog.web.dto.CMRespDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ReplyControllerTest {

	private final PostRepository postRepository;

	@GetMapping("/test/post/{id}")
	public CMRespDto<?> testPost(@PathVariable int id) {
		
		postRepository.findAll();
		
		Post post = postRepository.findById(id).get();
		return new CMRespDto<>(1,"성공", post);
	}
	
}
