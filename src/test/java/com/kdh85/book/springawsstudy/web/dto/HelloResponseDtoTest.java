package com.kdh85.book.springawsstudy.web.dto;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class HelloResponseDtoTest {

	@Test
	void return_HelloResponseDto_test() {

		String name = "hello";
		int amount = 1000;

		HelloResponseDto helloResponseDto = new HelloResponseDto(name, amount);

		assertThat(helloResponseDto.getName()).isEqualTo(name);
		assertThat(helloResponseDto.getAmount()).isEqualTo(amount);
	}
}