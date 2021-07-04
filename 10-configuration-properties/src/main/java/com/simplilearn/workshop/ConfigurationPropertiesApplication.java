package com.simplilearn.workshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simplilearn.workshop.model.PersonProperties;

@SpringBootApplication
@RestController
public class ConfigurationPropertiesApplication {

	final private PersonProperties personProperties;
	
	
	@Autowired
	public ConfigurationPropertiesApplication(PersonProperties personProperties) {
		super();
		this.personProperties = personProperties;
	}



	public static void main(String[] args) {
		SpringApplication.run(ConfigurationPropertiesApplication.class, args);
	}
	
	@RequestMapping("/")
	public String greetings() {
		return  personProperties.getGreeting() + " Spring Boot 2!" + personProperties.getFarewell() + " Spring Boot 2";
	}
	

}
