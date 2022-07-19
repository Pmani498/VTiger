package org.vtiger.documents;

import java.awt.AWTException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.tyss.genericUtility.ExcelUtility;
import org.tyss.genericUtility.FileUtility;
import org.tyss.genericUtility.IpathConstants;
import org.tyss.genericUtility.JavaUtility;
import org.tyss.genericUtility.WebdriverUtility;

public class CreateDocumentTest {
	public static void main(String[] args) throws EncryptedDocumentException, IOException, InterruptedException, AWTException {
		//Creating objects for GenericUtility
				ExcelUtility excelUtility= new ExcelUtility();
				JavaUtility javaUtility= new JavaUtility();
				FileUtility fileUtility= new FileUtility();
				WebdriverUtility webdriverUtility= new WebdriverUtility();

				//Initialize data from Property File
				fileUtility.initializethePropertyFile(IpathConstants.VTIGERPROPERTYFILEPATH);

				//Generate the Random number
				int randomnumber=javaUtility.getRandomNumber();

				// Get the control for particular sheet in excel
				excelUtility.initializeExcelFile(IpathConstants.VTIGEREXELFILEPATH);


				//Fetch the data from Property File

				String url = fileUtility.getDataFromProperty("url");
				String username = fileUtility.getDataFromProperty("username");
				String password = fileUtility.getDataFromProperty("password");
				String timeouts = fileUtility.getDataFromProperty("timeout");
				String browser = fileUtility.getDataFromProperty("browser");

				//Fetch the data from Excel Sheet
				String expectedTitlename=excelUtility.getDataFromExcel("Document", 2, 1)+randomnumber;


				//Runtime polymorphism
				WebDriver driver = webdriverUtility.setupDriver(browser);

				//pre-setting for browser
				webdriverUtility.maximizebrowser();
				long timeout = javaUtility.convertStringToLong(timeouts);
				webdriverUtility.implicitwait(timeout);

				//creating object for Action class
				webdriverUtility.initializeActions();

				// navigate the application
				webdriverUtility.openApplication(url);
				//login to the app
		
		driver.findElement(By.cssSelector("[name='user_name']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@name='user_password']")).sendKeys(password, Keys.TAB, Keys.ENTER);
		driver.findElement(By.xpath("//a[.='Documents']")).click();
		driver.findElement(By.cssSelector("[title='Create Document...']")).click();
		driver.findElement(By.xpath("//input[@name='notes_title']")).sendKeys(expectedTitlename);
		WebElement frameid=driver.findElement(By.xpath("//iframe"));
		driver.switchTo().frame(frameid);
		driver.findElement(By.xpath("//body[@class='cke_show_borders']")).sendKeys("Testyantra is located in Kathriguppe");
		driver.switchTo().parentFrame();
		JavascriptExecutor jse=(JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,2000);");
		driver.findElement(By.id("filename_I__")).sendKeys("F:\\Mani\\Eclipse\\Selenium\\SDET36L1-SeleniumFramework\\src\\test\\resources\\Exceptions.pdf");
		String upload=driver.findElement(By.xpath("//input[@name='filename_hidden']")).getText();
		System.out.println(upload);
		driver.findElement(By.xpath("//input[@accesskey='S']")).click();
		String actualTitlename=driver.findElement(By.xpath("//span[@id='dtlview_Title']")).getText();
		if(actualTitlename.equals(expectedTitlename))
		{
			System.out.println("Document Title is Validated");
		}
		else
		{
			System.out.println("Document Title is not Validated");
		}
		/*String actuanotename=driver.findElement(By.linkText("//a[contains(@onclick,'dldCntIncrease')]")).getText();
		if(actuanotename.equals(expectedUploadedfilename))
		{
			System.out.println("Note is Validated");
		}
		else
		{
			System.out.println("Uploaded not Validated");
		}*/
		WebElement element=driver.findElement(By.xpath("//span[contains(text(),'Updated today')]"));
		webdriverUtility.waitTillElementVisible(element);
		String actualUploadedfilename=driver.findElement(By.xpath("//a[@onclick='javascript:dldCntIncrease(327);']/../../../tr[2]/td[2]")).getText();
		System.out.println(actualUploadedfilename);
		if(actualUploadedfilename.equals("Exceptions.pdf"))
		{
			javaUtility.printStatement("Uploaded file name is Validated");
		excelUtility.setDataIntoExcel("ContactCreation", 2, 2, "Pass");
		excelUtility.provideDataToExcelPass(IpathConstants.VTIGEREXELFILEPATH);
	}
	else
	{
		javaUtility.printStatement("Uploaded file name is not Validated");
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


