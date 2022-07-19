package org.vtiger.products;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.tyss.genericUtility.ExcelUtility;
import org.tyss.genericUtility.FileUtility;
import org.tyss.genericUtility.IpathConstants;
import org.tyss.genericUtility.JavaUtility;
import org.tyss.genericUtility.WebdriverUtility;

public class CreateProductTest {
	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		// Creating objects for GenericUtility
		ExcelUtility excelUtility = new ExcelUtility();
		JavaUtility javaUtility = new JavaUtility();
		FileUtility fileUtility = new FileUtility();
		WebdriverUtility webdriverUtility = new WebdriverUtility();

		// Initialize data from Property File
		fileUtility.initializethePropertyFile(IpathConstants.VTIGERPROPERTYFILEPATH);

		// Generate the Random number
		int randomnumber = javaUtility.getRandomNumber();

		// Get the control for particular sheet in excel
		excelUtility.initializeExcelFile(IpathConstants.VTIGEREXELFILEPATH);

		// Fetch the data from Property File

		String url = fileUtility.getDataFromProperty("url");
		String username = fileUtility.getDataFromProperty("username");
		String password = fileUtility.getDataFromProperty("password");
		String timeouts = fileUtility.getDataFromProperty("timeout");
		String browser = fileUtility.getDataFromProperty("browser");

		// Fetch the data from Excel Sheet
		String expectedProductname = excelUtility.getDataFromExcel("Product", 2, 1) + randomnumber;

		// Runtime polymorphism
		WebDriver driver = webdriverUtility.setupDriver(browser);

		// Pre-setting for browser
		webdriverUtility.maximizebrowser();
		long timeout = javaUtility.convertStringToLong(timeouts);
		webdriverUtility.implicitwait(timeout);

		// creating object for Action class
		webdriverUtility.initializeActions();

		// navigate the application
		webdriverUtility.openApplication(url);
		driver.findElement(By.cssSelector("[name='user_name']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@name='user_password']")).sendKeys(password, Keys.TAB, Keys.ENTER);
		// Create Product
		driver.findElement(By.xpath("//a[.='Products']")).click();
		driver.findElement(By.cssSelector("[title='Create Product...']")).click();
		driver.findElement(By.xpath("//input[@name='productname']")).sendKeys(expectedProductname);
		driver.findElement(By.cssSelector("[title='Save [Alt+S]']")).click();
		// Validation of Product name
		String actualProductame = driver.findElement(By.xpath("//span[@id='dtlview_Product Name']")).getText();
		// Validate the Product creation
		if (actualProductame.equals(expectedProductname)) {
			javaUtility.printStatement("Product creation is Validated");
			excelUtility.setDataIntoExcel("Product", 2, 2, "Pass");
			excelUtility.provideDataToExcelPass(IpathConstants.VTIGEREXELFILEPATH);

		} else {
			javaUtility.printStatement("Product creation is Not Validated");
			excelUtility.setDataIntoExcel("Product", 2, 2, "Fail");
			excelUtility.provideDataToExcelPass(IpathConstants.VTIGEREXELFILEPATH);
		}

		// Closing the workbook and driver
		WebElement administratorIcon = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		webdriverUtility.mouseOverElement(administratorIcon);
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		excelUtility.workbookclose();
		webdriverUtility.closeBrowser();
	}

}
