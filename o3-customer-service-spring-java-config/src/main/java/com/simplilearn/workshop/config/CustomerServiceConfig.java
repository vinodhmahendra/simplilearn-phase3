package com.simplilearn.workshop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.simplilearn.workshop.repository.CustomerRepository;
import com.simplilearn.workshop.repository.InMemoryCustomerRepositoryImpl;
import com.simplilearn.workshop.services.CustomerService;
import com.simplilearn.workshop.services.CustomerServiceImpl;

@Configuration
public class CustomerServiceConfig {

//	<bean name='customerRepository' class="InMem..">

	//default method name === bean name
	@Bean
	public CustomerRepository customerRepository() {
		return new InMemoryCustomerRepositoryImpl();
	}
	
	@Bean
	public CustomerService customerService() {
		CustomerServiceImpl customerServiceImpl = new CustomerServiceImpl();
		customerServiceImpl.setCustomerRepository(customerRepository()); // invoke s setter method
		return customerServiceImpl;
	}
	
}
