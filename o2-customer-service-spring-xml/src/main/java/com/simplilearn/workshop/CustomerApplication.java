package com.simplilearn.workshop;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.simplilearn.workshop.model.Customer;
import com.simplilearn.workshop.services.CustomerService;
import com.simplilearn.workshop.services.CustomerServiceImpl;

public class CustomerApplication {

	public static void main(String[] args) {
		//create an instance of customer service
//		CustomerService customerService = new CustomerServiceImpl();
		
		ApplicationContext spring_container = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		CustomerService customerService = spring_container.getBean("customerService", CustomerService.class);
		
		List<Customer> list_of_customers = customerService.retrieveAllCustomers();
	
		list_of_customers.forEach(customer -> System.out.println(customer.getName()));
	
	}

}
