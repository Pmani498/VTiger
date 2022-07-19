package org.vtiger.compaign;

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
import org.tyss.genericUtility.ExcelUtility;
import org.tyss.genericUtility.FileUtility;
import org.tyss.genericUtility.IpathConstants;
import org.tyss.genericUtility.JavaUtility;
import org.tyss.genericUtility.WebdriverUtility;

public class CreateCampaignWithProductTest {
	WebDriver driver;

	public static void main(String[] args) throws EncryptedDocumentException, IOException, InterruptedException {
		// Creating objects for GenericUtility
		ExcelUtility excelUtility = new ExcelUtility();
		JavaUtility javaUtility = new JavaUtility();
		FileUtility fileUtility = new FileUtility();
		WebdriverUtility webdriverUtility = new WebdriverUtility();
		CreateCampaignWithProductTest csc =new CreateCampaignWithProductTest();
		// Initialize data from Property File
		fileUtility.initializethePropertyFile(IpathConstants.VTIGERPROPERTYFILEPATH);

		// Generate the Random number
		int randomnumber = javaUtility.getRandomNumber();

		// Get the control for particular sheet in excel
		excelUtility.initializeExcelFile(IpathConstants.VTIGEREXELFILEPATH);

		// Fetch the data from Property File
		CreateCampaignWithProductTest ccp=new CreateCampaignWithProductTest();
		String url = fileUtility.getDataFromProperty("url");
		String username = fileUtility.getDataFromProperty("username");
		String password = fileUtility.getDataFromProperty("password");
		String timeouts = fileUtility.getDataFromProperty("timeout");
		String browser = fileUtility.getDataFromProperty("browser");

		// Fetch the data from Excel Sheet
		String expectedProductname = excelUtility.getDataFromExcel("Campaign", 6, 1) + randomnumber;
		String expectedCampaignname = excelUtility.getDataFromExcel("Campaign", 4, 1) + randomnumber;

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
		// Login the Application

		driver.findElement(By.cssSelector("[name='user_name']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@name='user_password']")).sendKeys(password, Keys.TAB, Keys.ENTER);
		driver.findElement(By.xpath("//a[.='Products']")).click();
		driver.findElement(By.cssSelector("[title='Create Product...']")).click();
		driver.findElement(By.xpath("//input[@name='productname']")).sendKeys(expectedProductname);
		driver.findElement(By.cssSelector("[title='Save [Alt+S]']")).click();
		TakesScreenshot ts=(TakesScreenshot)driver;
		File src=ts.getScreenshotAs(OutputType.FILE);
		File trg=new File("./erroshots/"+csc.getClass().getSimpleName()+"_"+javaUtility.getCurrentDate("dd_MM_yyyy_HH_mm_ss")+".png");
	try {
		FileUtils.copyFile(src,trg);
	} catch (IOException e) {
		e.printStackTrace();   
	}
		/*
		 * WebElement compaignModule =
		 * driver.findElement(By.xpath("//a[text()='More']"));
		 * webdriverUtility.mouseOverElement(compaignModule);
		 * driver.findElement(By.xpath("//a[text()='Campaigns']")).click();
		 * driver.findElement(By.cssSelector("[title='Create Campaign...']")).click();
		 * driver.findElement(By.name("campaignname")).sendKeys(expectedCampaignname);
		 * driver.findElement(By.xpath(
		 * "//input[@name='product_name']/following-sibling::img")).click();
		 * webdriverUtility.windowPopup("Products", url);//url contains value have to
		 * mention driver.findElement(By.xpath("//input[@id='search_txt']")).sendKeys(
		 * expectedProductname);
		 * driver.findElement(By.xpath("//input[@name='search']")).click();
		 * driver.findElement(By.linkText(expectedProductname)).click();
		 * webdriverUtility.windowPopup("Campaigns", url);//url contains value have to
		 * mention driver.findElement(By.cssSelector("[title='Save [Alt+S]']")).click();
		 * String actualProductname =
		 * driver.findElement(By.xpath("//td[@id='mouseArea_Product']")).getText();
		 * String actualname = actualProductname.trim(); String actualCompaignname =
		 * driver.findElement(By.xpath("//span[@id='dtlview_Campaign Name']")).getText()
		 * ; // Validate the Campaign creation if
		 * (actualname.equals(expectedProductname)
		 * &&((actualCompaignname.equals(expectedCampaignname)))) {
		 * javaUtility.printStatement("Product creation is Validated");
		 * excelUtility.setDataIntoExcel("Campaign", 3, 2, "Pass");
		 * excelUtility.provideDataToExcelPass(IpathConstants.VTIGEREXELFILEPATH);
		 * 
		 * } else { javaUtility.printStatement("Product creation is not Validated");
		 * excelUtility.setDataIntoExcel("Campaign", 3, 2, "Fail");
		 * excelUtility.provideDataToExcelPass(IpathConstants.VTIGEREXELFILEPATH); } //
		 * Closing the workbook and driver WebElement administratorIcon =
		 * driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		 * webdriverUtility.mouseOverElement(administratorIcon);
		 * driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		 * excelUtility.workbookclose(); webdriverUtility.closeBrowser();
		 */
	}
}
