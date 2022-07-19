package org.vtiger.practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class DatabasePracticeUpdatedata {

	public static void main(String[] args) throws SQLException {
		//Create the object for driver class which is given by database vendor
		Driver driver=new Driver();
		//Register the driver to jdbc
		DriverManager.registerDriver(driver);
		//Establish the connection
		Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/mani","root","root");
		//create statement
		Statement statement=connection.createStatement();
		//Execute query
		int result=statement.executeUpdate("insert into sdet36 values(104,'Sourav',975364835,'Sourav88@gmail.com');");
		if(result==1)
		{
			System.out.println("Data entered into database");
		}
	}
}
