package com.simplilearn.workshop.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.simplilearn.workshop.model.Customer;

@Controller
public class CustomerController {
	
	private static final String BASE_URL = "http://localhost:8000/customers";
	
	private RestTemplate restTemplate;
	
	@Autowired
	public CustomerController(RestTemplateBuilder builder) {
		this.restTemplate = builder.build();
	}
	
	@GetMapping(path = "/customers/list")
	public ModelAndView listOfCustomers() {
		// make a REST request
		ResponseEntity<List<Customer>> responseEntity = restTemplate.exchange(BASE_URL, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Customer>>() {
				});

		List<Customer> customers = responseEntity.getBody();

		ModelAndView modelAndView = new ModelAndView("list-customers");

		modelAndView.addObject("customers", customers);

		return modelAndView;
	}
	
	@GetMapping(path = "/customers/showFormForAdd")
	public ModelAndView showFormForAddCustomer() {
		ModelAndView modelAndView = new ModelAndView("customer-form");
		Customer theCustomer = new Customer();
		modelAndView.addObject("customer", theCustomer);
		return modelAndView;
	}
	
	@PostMapping(path = "/customers/saveCustomer")
	public ModelAndView saveCustomer(@ModelAttribute("customer") Customer theCustomer) {
		ModelAndView modelAndView = new ModelAndView("redirect:/customers/list");
		restTemplate.postForObject(BASE_URL, theCustomer, Customer.class);
		return modelAndView;
	}
	
	@GetMapping(path = "/customers/showFormForUpdate")
	public ModelAndView showFormForUpdate(@RequestParam("customerId") Integer theId, Model theModel) {
		
		Map<String, Integer> params = new HashMap<String, Integer>();
		params.put("theId", theId);
		Customer theCustomer = restTemplate.getForObject(BASE_URL+"/{theId}", Customer.class,params);
		theModel.addAttribute("customer", theCustomer);
		return new ModelAndView("customer-form");
	}
	
	@GetMapping(path="/customers/delete")
	public ModelAndView deleteCustomerById(@RequestParam("customerId") Integer theId) {
		Map<String, Integer> params = new HashMap<String, Integer>();
		params.put("theId", theId);
		restTemplate.delete(BASE_URL+"/{theId}",params);
		
		ResponseEntity<List<Customer>> responseEntity = restTemplate.exchange(BASE_URL, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Customer>>() {
				});

		List<Customer> customers = responseEntity.getBody();

		ModelAndView modelAndView = new ModelAndView("list-customers");

		modelAndView.addObject("customers", customers);

		return modelAndView;
				
	}
	
}
