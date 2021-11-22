package com.kdh85.book.springawsstudy.config.auth.dto;

import java.util.Map;

import com.kdh85.book.springawsstudy.domain.user.Role;
import com.kdh85.book.springawsstudy.domain.user.User;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
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
		log.debug("registrationId={}",registrationId);
		if ("naver".equals(registrationId)) {
			return ofNaver("id",attributes);
		}

		if ("kakao".equals(registrationId)) {
			return ofKakao("id",attributes);
		}

		return ofGoogle(userNameAttributeName, attributes);
	}

	private static OAuthAttributes ofKakao(String userNameAttributeName, Map<String, Object> attributes) {
		log.debug("attributes = {}",attributes);
		Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
		log.debug(" kakao_account = {}",kakaoAccount);
		Map<String, Object> profile = (Map<String, Object>)kakaoAccount.get("profile");
		log.debug(" profile = {}",profile);
		return OAuthAttributes.builder()
			.name((String) profile.get("nickname"))
			.email((String) kakaoAccount.get("email"))
			.picture((String) profile.get("profile_image"))
			.attributes(attributes)
			.nameAttributeKey(userNameAttributeName)
			.build();
	}

	@SuppressWarnings("unchecked")
	private static OAuthAttributes ofNaver(String userNameAttributeName, Map<String, Object> attributes) {
		log.debug("attributes = {}",attributes);
		Map<String, Object> response = (Map<String, Object>) attributes.get("response");

		return OAuthAttributes.builder()
			.name((String) response.get("name"))
			.email((String) response.get("email"))
			.picture((String) response.get("profile_image"))
			.attributes(response)
			.nameAttributeKey(userNameAttributeName)
			.build();
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

	public User toEntity() {
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
