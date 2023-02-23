package com.datn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class BookstoreDatn2022Application  {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreDatn2022Application.class, args);
	}

}
