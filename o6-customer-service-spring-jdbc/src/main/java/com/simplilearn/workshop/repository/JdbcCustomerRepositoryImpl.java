package com.simplilearn.workshop.repository;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.simplilearn.workshop.model.Customer;

@Repository(value = "customerRepository")
public class JdbcCustomerRepositoryImpl implements CustomerRepository {

	private static final String SQL_CUSTOMERS_COUNT = "select COUNT(*) from customers";
	private static final String SQL_CUSTOMERS_SELECT = "select * from customers";
	
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;

	
	@Autowired
	public JdbcCustomerRepositoryImpl(DataSource dataSource) {
		System.out.println("spring called a repository constructor to assemble a dataSource bean");
		this.dataSource = dataSource;
		// create an object of JdbcTemplate
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	

	@Override
	public List<Customer> findAll() {
//		return jdbcTemplate.query(SQL_CUSTOMERS_SELECT,new CustomerMapper());
		//Java SE 8 Lambda Expression
		return jdbcTemplate.query(SQL_CUSTOMERS_SELECT,(rs,rowNum)-> new Customer(rs.getLong("ID"),rs.getString("NAME"), rs.getString("EMAIL"), rs.getString("PHONE")));
	}

	@Override
	public Integer findNoOfCustomers() {
		return jdbcTemplate.queryForObject(SQL_CUSTOMERS_COUNT, Integer.class);
	}

}

//class CustomerMapper implements RowMapper<Customer>{
//
//	@Override
//	public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
//		Customer customer = new Customer(rs.getLong("ID"),rs.getString("NAME"), rs.getString("EMAIL"), rs.getString("PHONE"));
//		return customer;
//	}
//	
//}
