package com.simplilearn.workshop.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.simplilearn.workshop.model.Customer;

@Repository(value = "customerRepository")
public class JdbcCustomerRepositoryImpl implements CustomerRepository {

	private static final String SELECT_ALL_CUSTOMERS = "select * from customers";
	private static final String SELECT_CUSTOMER_BY_ID = "select * from customers where id = ?";
	private static final String INSERT_A_CUSTOMER = "insert into customers (id,firstname,lastname,email) values(?,?,?,?)";
	private static final String DELETE_CUSTOMER_BY_ID = "delete * customers where id = ?";

	private static Integer customerCount = 3;

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(this.dataSource); // create an object of JdbcTemplate
	}

	// create an inner class to map a record to java object
	public class CustomerRowMapper implements RowMapper<Customer> {

		@Override
		public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
			Customer theCustomer = new Customer();
			theCustomer.setId(rs.getInt("id"));
			theCustomer.setFirstName(rs.getString("firstname"));
			theCustomer.setLastName(rs.getString("lastname"));
			theCustomer.setEmail(rs.getString("email"));
			return theCustomer;
		}

	}

	@Override
	public List<Customer> getCustomers() {
		List<Customer> customers = jdbcTemplate.query(SELECT_ALL_CUSTOMERS, new CustomerRowMapper());
		return customers;
	}

	@Override
	public Customer saveCustomer(Customer theCustomer) {
		jdbcTemplate.update(INSERT_A_CUSTOMER, ++customerCount, theCustomer.getFirstName(), theCustomer.getLastName(),
				theCustomer.getEmail());
		return getCustomer(customerCount);
	}

	@Override
	public Customer getCustomer(Integer theId) {
		return jdbcTemplate
				.queryForObject(
						SELECT_CUSTOMER_BY_ID, (rs,
								rowNum) -> Optional.of(new Customer(rs.getInt("id"), rs.getString("firstname"),
										rs.getString("lastname"), rs.getString("email"))),
						new Object[] { theId })
				.get();

	}

	@Override
	public Customer deleteCustomer(Integer theId) {
		jdbcTemplate.update(DELETE_CUSTOMER_BY_ID,theId);
		return null;
	}

}
