package com.simplilearn.workshop.services;

import java.util.List;

import com.simplilearn.workshop.model.Customer;

public interface CustomerService {
	public List<Customer> retrieveAllCustomers();
	public Integer countAllCustomers();
}
