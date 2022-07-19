package org.tyss.genericUtility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.mysql.cj.jdbc.Driver;

public class DatabaseUtility {
		Connection connection;
		Statement statement;
		ResultSet resultfetch;
		/**
		 * This method is to register and get connection with database
		 * @param url
		 * @param userName
		 * @param password
		 */
		public void registerDriver(String url, String userName, String password)
		{
			Driver driver;
			try {
				driver = new Driver();
				DriverManager.registerDriver(driver);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				connection=DriverManager.getConnection(url,userName,password);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		/**
		 * This method is used to modify data 
		 * @param sqlQuery
		 * @return
		 */
		public int modifyDataInDB(String sqlQuery)
		{
			try {
				statement = connection.createStatement();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int result=0;
			try {
				result = statement.executeUpdate(sqlQuery);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return result;
		}
		/**
		 * This method is used fetch data from dB
		 * @param table
		 */
		public void fetchtheDataFromDatabase(String table)
		{
			try {
			 resultfetch = statement.executeQuery(table);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public boolean verifyDataInDB(String query,String coloumnNumberorColoumnIndex, String expectedData )
		{
			List<String>list = getDataFromDB(query,coloumnNumberorColoumnIndex);
			System.out.println(list);
			boolean flag=false;
			for(String data:list)
			{
				if(data.equalsIgnoreCase(expectedData))
				{
					flag=true;
					break;
				}
			}
			return flag;
		}
		private List<String> getDataFromDB(String query, String coloumnNumberorColoumnIndex) {
			// TODO Auto-generated method stub
			return null;
		}
		/**
		 * This method is used to close connection of database
		 */
		public void closeConnection()
		{
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
