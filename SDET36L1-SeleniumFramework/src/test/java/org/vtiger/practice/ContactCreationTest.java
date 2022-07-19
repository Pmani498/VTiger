package org.vtiger.practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ContactCreationTest {
	//WebDriver driver=null;
	public static void main(String[] args) throws IOException {
		FileInputStream fis = new FileInputStream("./src/test/resources/CommonData.properties");
		Properties properties = new Properties();
		properties.load(fis);
		// Get the control for perticular sheet in excel
		FileInputStream fisExcel = new FileInputStream("./src/test/resources/TestDatafile.xlsx");
		Workbook workbook = WorkbookFactory.create(fisExcel);
		Sheet sheet = workbook.getSheet("Contacts");
		int randomNumber = new Random().nextInt(1000);
		String url = properties.getProperty("url").trim();
		String username = properties.getProperty("username").trim();
		String password = properties.getProperty("password").trim();
		String timeouts = properties.getProperty("timeout").trim();
		String browser = properties.getProperty("browser").trim();
		// Fetech the actual ContactName
		String expectedContactName = sheet.getRow(2).getCell(1).getStringCellValue() + randomNumber;
		// Convert string to Long
		long longTimeout = Long.parseLong(timeouts);
		// launching the browser in run time based on browser key
		WebDriver driver=null;
		// Run time polymorphism
		/*switch (browser) {
		case "chrome":
			WebDriverManager.chromedriver().setup();// Method chaining
			driver = new ChromeDriver();// Abstraction
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();// Method chaining
			driver = new FirefoxDriver();// Abstraction
			break;
		case "ie":
			WebDriverManager.iedriver().setup();// Method chaining
			driver = new InternetExplorerDriver();// Abstraction
			break;
		default:
			System.out.println("You entered wrong browser key in the property file");
		}*/
		if(browser.equals("chrome"))
		{
			WebDriverManager.chromedriver().setup();// Method chaining
			driver = new ChromeDriver();// Abstraction
		}
		else if(browser.equals("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();// Method chaining
			driver = new FirefoxDriver();// Abstraction
		}
		else if(browser.equals("ie"))
		{
			WebDriverManager.iedriver().setup();
			driver=new InternetExplorerDriver();
		}
		//Pre-setting for Browser
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(longTimeout));
		// Navigating the Application
		driver.get(url);
		// Login the Application
		driver.findElement(By.cssSelector("[name='user_name']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@name='user_password']")).sendKeys(password, Keys.TAB, Keys.ENTER);
		driver.findElement(By.xpath("//a[text()='Contacts']")).click();
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(expectedContactName);
		driver.findElement(By.cssSelector("[title='Save [Alt+S]']")).click();
		String actualContactName = driver.findElement(By.xpath("//span[@id='dtlview_Last Name']")).getText();
		System.out.println(actualContactName);
		// Validating the Contact name
		if (actualContactName.equals(expectedContactName)) {
			System.out.println("Contact creation is Validated");
		} else {
			System.out.println("Contact creation is Not validated");
		}
		// Logout from the Application
		WebElement administratorIcon = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions act = new Actions(driver);
		act.moveToElement(administratorIcon).perform();
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		// Close the workbook instance of excel
		workbook.close();
		// Close the Browser
		driver.quit();

	}

}
