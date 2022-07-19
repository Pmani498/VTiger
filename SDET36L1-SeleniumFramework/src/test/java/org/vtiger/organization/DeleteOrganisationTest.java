package org.vtiger.organization;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DeleteOrganisationTest {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		FileInputStream fis = new FileInputStream("./src/test/resources/CommonData.properties");
		Properties properties = new Properties();
		properties.load(fis);
		// Get the control for particular sheet in excel
		FileInputStream fisExcel = new FileInputStream("./src/test/resources/TestDatafile.xlsx");
		Workbook workbook = WorkbookFactory.create(fisExcel);
		Sheet sheet = workbook.getSheet("Contacts");
		int randomNumber = new Random().nextInt(1000);
		String url = properties.getProperty("url").trim();
		String username = properties.getProperty("username").trim();
		String password = properties.getProperty("password").trim();
		String timeouts = properties.getProperty("timeout").trim();
		String browser = properties.getProperty("browser").trim();
		// Fetch the actual ContactName
		String expectedOrganizationname = sheet.getRow(12).getCell(1).getStringCellValue() + randomNumber;
		// Convert string to Long
		long longTimeout = Long.parseLong(timeouts);
		// launching the browser in run time based on browser key
		WebDriver driver=null;
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
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(longTimeout));
		//create object for an explicit wait
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(longTimeout));
		driver.get(url);
		//Login to the Application
		driver.findElement(By.cssSelector("[name='user_name']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@name='user_password']")).sendKeys(password, Keys.TAB, Keys.ENTER);
		//create Organization
		WebElement compaignModule=driver.findElement(By.xpath("//a[text()='More']"));
		Actions act=new Actions(driver);
		act.moveToElement(compaignModule).perform();
		driver.findElement(By.xpath("//a[text()='Organizations']")).click();
		driver.findElement(By.cssSelector("[title='Create Organization...']")).click();
		driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys(expectedOrganizationname);
		driver.findElement(By.cssSelector("[title='Save [Alt+S]']")).click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[@class='small']"))));
		driver.findElement(By.xpath("//a[@class='hdrLink']")).click();
		
	}

}
