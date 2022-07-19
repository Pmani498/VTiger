package org.vtiger.TestNG;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class DatabasePractice {

	public static void main(String[] args) throws SQLException {
		// Create object for driver class which is given by database vendor
		Driver driver = new Driver();
		// Register the driver to jdbc
		DriverManager.registerDriver(driver);
		// Establish the connection
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mani", "root", "root");
		// Create statement
		Statement statement = connection.createStatement();
		// Execute query
		ResultSet result = statement.executeQuery("select * from sdet36;");
		// Iterate the data
		while (result.next()) {
			System.out.println(result.getString(1) + " " + result.getString("empName"));
		}
		//Close connection
		connection.close();
	}

}
