package com.simplilearn.workshop;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class O9PropertyFileApplication {

	public static void main(String[] args) {
		SpringApplication.run(O9PropertyFileApplication.class, args);
	}
	
	@Value("${message}")
	private String message;
	
	@RequestMapping("/")
	public String greetings() {
		return message;
	}
}
