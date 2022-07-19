package org.tyss.genericUtility;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class JavaUtility {
	WebDriver driver;
	/**
	 * This class contains java reusable methods
	 * @author Manigandan Palluru
	 *
	 */

		/**
		 * This method is used to generate Random Number
		 * @return
		 */

		public int getRandomNumber()
		{
			return new Random().nextInt(1000);
		}
		/**
		 * This method is used to convert the String to Long data type
		 * @param stringData
		 * @return
		 */
		public long convertStringToLong(String stringData)
		{
			return Long.parseLong(stringData);
		}
		/**
		 * This method is used to get current date in specified strategy
		 * @param strategy
		 * @return
		 */
		public String getCurrentDate(String strategy)
		{
			return new SimpleDateFormat(strategy).format(new Date());
		}
		/**
		 * This method is used to split the String based on Strategy
		 * @param value
		 * @param strategy
		 * @return
		 */
		public String[] SplitString(String value, String strategy)
		{

			return value.split(strategy);
		}
		/**
		 * This method is used to print the value in Console 
		 * @param statement
		 */
		public void printStatement(String statement)
		{
			System.out.println(statement);

		}
		/**This method is used to take the current page screenshot
		 * @param currentClass
		 * @param javaUtility
		 */
		public void takesScreenShortPage(Object currentClass,JavaUtility javaUtility)
		{
			TakesScreenshot ts=(TakesScreenshot)driver;
			File src=ts.getScreenshotAs(OutputType.FILE);
			File trg=new File("./erroshots/"+currentClass.getClass().getSimpleName()+"_"+javaUtility.getCurrentDate("dd_MM_yyyy_HH_mm_ss")+".png");
		try {
			FileUtils.copyFile(src,trg);
		} catch (IOException e) {
			e.printStackTrace();   
		}
		
		}
	}
