package org.vtiger.practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class DatabaseValidate {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
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
				//Validate the data
				int count=0;
				while(result.next())
				{
					if(result.getString("empName").equals("Vicky")) {
						System.out.println("Data is present in the database");
						count++;
						break;
					}
				}
				if(count==0)
				{
					System.out.println("Data is not present in the dtabase");
				}
				//Close connection
				connection.close();

	}

}
