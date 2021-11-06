package com.kdh85.book.springawsstudy.config.auth.dto;

import com.kdh85.book.springawsstudy.domain.user.User;

import lombok.Getter;

@Getter
public class SessionUser {

	private final String name;
	private final String email;
	private final String picture;

	public SessionUser(User user) {
		this.name    = user.getName();
		this.email   = user.getEmail();
		this.picture = user.getPicture();
	}
}
