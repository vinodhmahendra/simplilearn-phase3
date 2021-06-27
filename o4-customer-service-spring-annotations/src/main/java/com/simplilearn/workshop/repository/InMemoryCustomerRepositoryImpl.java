package com.simplilearn.workshop.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.simplilearn.workshop.model.Customer;

@Repository(value = "customerRepository")
public class InMemoryCustomerRepositoryImpl implements CustomerRepository {

	private static List<Customer> customers_list = new ArrayList<Customer>();

	// Add three customers
	static {
		customers_list.add(new Customer(101L, "vinodh", "vinodh@pivotal.com", "123-5555"));
		customers_list.add(new Customer(102L, "bhavya", "bhavya@mindtree.com", "222-88888"));
		customers_list.add(new Customer(103L, "samarth", "samarth@google.com", "444-55555"));

	}

	@Override
	public List<Customer> findAll() {
		return customers_list;
	}

}
