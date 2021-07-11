package com.simplilearn.workshop.simplerpcconsumerkafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@KafkaListener(topics = "pgfsd-topic", groupId = "foo")
	public void process(String message) {
		System.out.println(message);
	}

}
