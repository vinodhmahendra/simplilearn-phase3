package com.simplilearn.workshop;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.simplilearn.workshop.utils.MySQLDatabaseUtils;

public class SelectCustomers {

	public static void main(String[] args) throws ClassNotFoundException,SQLException{
		// obtain a connection
		Connection connection = MySQLDatabaseUtils.getConnection();
		
		String sql_select = "select * from customers";
		
		//obtain a Statement from connection
		Statement statement = connection.createStatement();
		
		// obtain a ResultSet
		ResultSet rs = statement.executeQuery(sql_select);
		
		while ( rs.next()) {
			System.out.println(rs.getInt("ID")  +"\t" + rs.getString("NAME") + "\t" +rs.getString("EMAIL") + "\t" +rs.getString("PHONE"));
		}
		
		rs.close();
		statement.close();
		connection.close();

	}

}
