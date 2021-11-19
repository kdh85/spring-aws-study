package com.kdh85.book.springawsstudy.config.auth;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.kdh85.book.springawsstudy.domain.user.Role;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final CustomOAuth2UserService customOAuth2UserService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable()//h2-console 화면을 사용하기 위해 비활성화 해야하는 옵션.
			.headers().frameOptions().disable()//h2-console 화면을 사용하기 위해 비활성화 해야하는 옵션.
			.and()
			.authorizeRequests()//URL별 권한 관리를 설정하는 옵션의 시작점.
			.antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**","/profile").permitAll()//전체 열람 가능.
			.antMatchers("/api/v1/**").hasRole(Role.USER.name())//USER 권한만 가능.
			.anyRequest().authenticated()//나머지 URL들을 모두 인증사용자들에게만 허용.
			.and()
			.logout()//로그아웃 기능에 대한 설정 시작점.
			.logoutSuccessUrl("/")//로그아웃 성공시 이동.
			.and()
			.oauth2Login()//Oauth2 로그인 기능에 대한 설정 시작점.
			.userInfoEndpoint()//OAuth2 로그인 성공 이후 사용자 정보를 가져올 때의 설정.
			.userService(customOAuth2UserService);//로그인 성공 시 후속 조치를 진행할 서비스 인터페이스의 구현체 등록.
	}
}
