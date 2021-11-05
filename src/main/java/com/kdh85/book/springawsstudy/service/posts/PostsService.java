package com.kdh85.book.springawsstudy.service.posts;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kdh85.book.springawsstudy.domain.posts.Posts;
import com.kdh85.book.springawsstudy.domain.posts.PostsRepository;
import com.kdh85.book.springawsstudy.web.dto.PostsListResponseDto;
import com.kdh85.book.springawsstudy.web.dto.PostsResponseDto;
import com.kdh85.book.springawsstudy.web.dto.PostsSaveRequestDto;
import com.kdh85.book.springawsstudy.web.dto.PostsUpdateRequestDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PostsService {

	private final PostsRepository postsRepository;

	@Transactional
	public Long save(PostsSaveRequestDto requestDto) {
		return postsRepository.save(requestDto.toEntity()).getId();
	}

	@Transactional
	public Long update(Long id, PostsUpdateRequestDto requestDto) {

		Posts posts = postsRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));
		posts.update(requestDto.getTitle(), requestDto.getContent());
		return id;
	}

	public PostsResponseDto findById(Long id) {
		Posts posts = postsRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));
		return new PostsResponseDto(posts);
	}

	@Transactional(readOnly = true)
	public List<PostsListResponseDto> findAllDesc() {
		return postsRepository.findAllDesc()
			.stream()
			.map(PostsListResponseDto::new)
			.collect(Collectors.toList());
	}

	@Transactional
	public void delete(Long id) {
		Posts posts = postsRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));
		postsRepository.delete(posts);
	}
}
