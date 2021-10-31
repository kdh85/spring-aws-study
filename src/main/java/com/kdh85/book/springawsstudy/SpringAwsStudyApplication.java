package com.kdh85.book.springawsstudy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SpringAwsStudyApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringAwsStudyApplication.class, args);
	}

}
