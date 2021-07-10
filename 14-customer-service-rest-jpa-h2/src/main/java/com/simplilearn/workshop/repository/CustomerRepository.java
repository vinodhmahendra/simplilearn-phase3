package com.simplilearn.workshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.simplilearn.workshop.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{
	
//	@Query(value = "complex sql")
//	List<Customer> meaningfulmethodname();

}
