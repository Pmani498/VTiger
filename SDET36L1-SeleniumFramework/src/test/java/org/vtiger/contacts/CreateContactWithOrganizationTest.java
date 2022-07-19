package org.vtiger.contacts;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.tyss.genericUtility.ExcelUtility;
import org.tyss.genericUtility.FileUtility;
import org.tyss.genericUtility.IpathConstants;
import org.tyss.genericUtility.JavaScriptUtility;
import org.tyss.genericUtility.JavaUtility;
import org.tyss.genericUtility.WebdriverUtility;

public class CreateContactWithOrganizationTest {
	public static void main(String[] args) throws EncryptedDocumentException, IOException, InterruptedException {
		// Creating objects for GenericUtility
		ExcelUtility excelUtility = new ExcelUtility();
		JavaUtility javaUtility = new JavaUtility();
		FileUtility fileUtility = new FileUtility();
		WebdriverUtility webdriverUtility = new WebdriverUtility();
		JavaScriptUtility javaScriptUtility=new JavaScriptUtility();
		
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
		String expectedOrganizationName = excelUtility.getDataFromExcel("ContactCreation", 2, 1) + randomnumber;
		String expectedContactLastname=excelUtility.getDataFromExcel("Lastname", 2, 1)+randomnumber;


		// Runtime polymorphism
		WebDriver driver = webdriverUtility.setupDriver(browser);

		// pre-setting for browser
		webdriverUtility.maximizebrowser();
		long timeout = javaUtility.convertStringToLong(timeouts);
		webdriverUtility.implicitwait(timeout);
		

		// creating object for Action class
		webdriverUtility.initializeActions();

		// navigate the application
		driver.get("https://www.tirumala.org/");
		File source=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(source,new File("./Screenshot/ttd.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		webdriverUtility.openApplication(url);
		// Login the App
		//javaUtility.takesScreenShotPage(timeout, javaUtility);
		driver.findElement(By.cssSelector("[name='user_name']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@name='user_password']")).sendKeys(password, Keys.TAB, Keys.ENTER);
		//WebElement element=driver.findElement(By.xpath("//table[@class='loginWrapper']"));
		WebElement compaignModule = driver.findElement(By.xpath("//a[text()='More']"));
		Actions act = new Actions(driver);
		act.moveToElement(compaignModule).perform();
		driver.findElement(By.xpath("//a[text()='Organizations']")).click();
		driver.findElement(By.cssSelector("[title='Create Organization...']")).click();
		driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys(expectedOrganizationName);
		driver.findElement(By.cssSelector("[title='Save [Alt+S]']")).click();
		//WebElement element1=driver.findElement(By.xpath("//span[@class='small']"));
		
		Explicitwait explicitwait =new Explicitwait(driver);
		webdriverUtility.intilizeExplicitWait(timeout);
		webdriverUtility.waitTillElementVisible(explicitwait.explicitWait());
		
		// Contact creation
		driver.findElement(By.xpath("//a[.='Contacts']")).click();
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(expectedContactLastname);
		driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img")).click();
		webdriverUtility.windowPopup("Account", url);
		driver.findElement(By.xpath("//input[@class='txtBox']")).sendKeys(expectedOrganizationName);
		driver.findElement(By.xpath("//input[@name='search']")).click();
		driver.findElement(By.linkText(expectedOrganizationName)).click();
		webdriverUtility.windowPopup("Contacts", url);
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		String actualOrganization = driver.findElement(By.xpath("//td[@id='mouseArea_Organization Name']")).getText();
		String actualOrganizationname = actualOrganization.trim();
		String actualLastname = driver.findElement(By.xpath("//span[@id='dtlview_Last Name']")).getText();
		if (actualOrganizationname.equals(expectedOrganizationName) && actualLastname.equals(expectedContactLastname)) {
		javaUtility.printStatement("Contact is created with Organization is Validated");
		excelUtility.setDataIntoExcel("ContactCreation", 2, 2, "Pass");
		excelUtility.provideDataToExcelPass(IpathConstants.VTIGEREXELFILEPATH);

	}
	else
	{
		javaUtility.printStatement("Contact is created with Organization is not Validated");
		excelUtility.setDataIntoExcel("ContactCreation", 2, 2, "Fail");
		excelUtility.provideDataToExcelPass(IpathConstants.VTIGEREXELFILEPATH);
	}

	//Closing the workbook and driver
	WebElement administratorIcon=driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
	webdriverUtility.mouseOverElement(administratorIcon);
	driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
	excelUtility.workbookclose();
	webdriverUtility.closeBrowser();
	}
	

}
