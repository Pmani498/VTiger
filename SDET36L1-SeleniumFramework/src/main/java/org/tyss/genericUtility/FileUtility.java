package org.tyss.genericUtility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
	 * This class contains reusable methods for CSV file and Property file
	 * @author DELL
	 *
	 */
	public class FileUtility {
		Properties properties;
	/**
	 * This method is used for initializing the property file
	 * @param filepath
	 */
		public void initializethePropertyFile(String filepath)
		{
			FileInputStream fis;
			try {
				fis = new FileInputStream(filepath);
				properties=new Properties();
				properties.load(fis);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	/**
	 * This method is to fetch data from property file
	 * @param key
	 * @return
	 */
		public String getDataFromProperty(String key)
			{
				return properties.getProperty(key).trim();
			}
		

	}
