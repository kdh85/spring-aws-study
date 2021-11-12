package com.kdh85.book.springawsstudy.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.kdh85.book.springawsstudy.config.auth.LoginUser;
import com.kdh85.book.springawsstudy.config.auth.dto.SessionUser;
import com.kdh85.book.springawsstudy.service.posts.PostsService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
public class IndexController {

	private final PostsService postsService;

	@GetMapping("/")
	public String index(Model model, @LoginUser SessionUser user) {
		model.addAttribute("posts", postsService.findAllDesc());

		if(user != null){
			log.debug("login user name ={}",user.getName());
			model.addAttribute("loginUserName", user.getName());
		}
		return "index";
	}

	@GetMapping("/posts/save")
	public String postsSave() {
		return "posts-save";
	}

	@GetMapping("/posts/update/{id}")
	public String postsUpdate(@PathVariable Long id, Model model) {
		model.addAttribute("post", postsService.findById(id));
		return "posts-update";
	}
}
