package com.kdh85.book.springawsstudy.config.auth.dto;

import java.util.Map;

import com.kdh85.book.springawsstudy.domain.user.Role;
import com.kdh85.book.springawsstudy.domain.user.User;

import lombok.Builder;
import lombok.Getter;

@Getter
public class OAuthAttributes {

	private final Map<String, Object> attributes;
	private final String nameAttributeKey;
	private final String name;
	private final String email;
	private final String picture;

	@Builder
	public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name, String email,
						   String picture) {
		this.attributes       = attributes;
		this.nameAttributeKey = nameAttributeKey;
		this.name             = name;
		this.email            = email;
		this.picture          = picture;
	}

	public static OAuthAttributes of(String registrationId, String userNameAttributeName,
									 Map<String, Object> attributes) {
		return ofGoogle(userNameAttributeName, attributes);
	}

	private static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {
		return OAuthAttributes.builder()
			.name(String.valueOf(attributes.get("name")))
			.email(String.valueOf(attributes.get("email")))
			.picture(String.valueOf(attributes.get("picture")))
			.attributes(attributes)
			.nameAttributeKey(userNameAttributeName)
			.build();
	}

	public User toEntity(){
		return User.builder()
			.name(name)
			.email(email)
			.picture(picture)
			.role(Role.GUEST)
			.build();
	}

	@Override
	public String toString() {
		return "OAuthAttributes{" +
			   "attributes=" + attributes +
			   ", nameAttributeKey='" + nameAttributeKey + '\'' +
			   ", name='" + name + '\'' +
			   ", email='" + email + '\'' +
			   ", picture='" + picture + '\'' +
			   '}';
	}
}
