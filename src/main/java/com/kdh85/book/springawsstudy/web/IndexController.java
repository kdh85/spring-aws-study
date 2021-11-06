package com.kdh85.book.springawsstudy.web;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.kdh85.book.springawsstudy.config.auth.dto.SessionUser;
import com.kdh85.book.springawsstudy.service.posts.PostsService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
public class IndexController {

	private final PostsService postsService;
	private final HttpSession httpSession;

	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("posts", postsService.findAllDesc());
		SessionUser user = (SessionUser)httpSession.getAttribute("user");

		if(user != null){
			log.debug("user name ={}",user.getName());
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
