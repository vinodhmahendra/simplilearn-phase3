package com.simplilearn.workshop;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableScheduling
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	private final KafkaTemplate<String, String> kafkaTemplate;

	@Autowired
	public Application(KafkaTemplate<String, String> kafkaTemplate) {
		super();
		this.kafkaTemplate = kafkaTemplate;
	}
	
	@Scheduled(fixedRate = 1000)
	public void sendMessage() {
		String timestamp = new SimpleDateFormat("HH:mm:ss").format(new Date());
		String message = "Hello World" + timestamp;
		kafkaTemplate.send("pgfsd-topic", message);
	}
	

}
