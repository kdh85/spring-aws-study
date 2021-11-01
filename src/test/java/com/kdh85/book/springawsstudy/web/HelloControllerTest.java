package com.kdh85.book.springawsstudy.web;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = HelloController.class)
class HelloControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void return_hello_test() throws Exception {
		String hello = "hello";

		mockMvc.perform(get("/hello"))
			.andExpect(status().isOk())
			.andExpect(content().string(hello));
	}

	@Test
	void return_helloResponseDto_test() throws Exception {
		String name = "hello";
		int amount = 1000;

		mockMvc.perform(get("/hello/dto")
				.param("name", name)
				.param("amount", String.valueOf(amount)))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.name", is(name)))
			.andExpect(jsonPath("$.amount", is(amount)));
	}
}