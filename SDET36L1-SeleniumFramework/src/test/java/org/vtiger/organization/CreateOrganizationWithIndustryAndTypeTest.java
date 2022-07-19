package org.vtiger.organization;

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

public class CreateOrganizationWithIndustryAndTypeTest {
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
				String expectedOrganizationname = excelUtility.getDataFromExcel("Organization", 5, 1) + randomnumber;
				String expectedIndustry = excelUtility.getDataFromExcel("Organization", 7, 1);
				String expectedTypeOfIndustry = excelUtility.getDataFromExcel("Organization", 9, 1);

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
				//Login the application
					driver.findElement(By.cssSelector("[name='user_name']")).sendKeys(username);
					driver.findElement(By.xpath("//input[@name='user_password']")).sendKeys(password, Keys.TAB, Keys.ENTER);
					WebElement compaignModule=driver.findElement(By.xpath("//a[text()='More']"));
					webdriverUtility.mouseOverElement(compaignModule);
					driver.findElement(By.xpath("//a[text()='Organizations']")).click();
					driver.findElement(By.cssSelector("[title='Create Organization...']")).click();
					driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys(expectedOrganizationname);
					WebElement industryDropdown=driver.findElement(By.xpath("//select[@name='industry']"));
					webdriverUtility.handleSelectDropdown(industryDropdown, "Education");
					WebElement typeDropdown=driver.findElement(By.xpath("//select[@name='accounttype']"));
					webdriverUtility.handleSelectDropdown(typeDropdown, "Press");
					driver.findElement(By.xpath("//input[@value='T']")).click();
					driver.findElement(By.cssSelector("[title='Save [Alt+S]']")).click();
					String actualOrganizationname=driver.findElement(By.xpath("//td[@id='mouseArea_Organization Name']")).getText();
					String actualIndustryname=driver.findElement(By.xpath("//span[@id='dtlview_Industry']")).getText();
					String actualTypeOfIndustry =driver.findElement(By.xpath("//td[@id='mouseArea_Type']")).getText();
					if(actualOrganizationname.equals(expectedOrganizationname) && actualIndustryname.equals(expectedIndustry) 
						&& actualTypeOfIndustry.equals(expectedTypeOfIndustry))
					{
					javaUtility.printStatement("Organization  with Industry and type creation is Validated");
					excelUtility.setDataIntoExcel("Organization", 4, 2, "Pass");
					excelUtility.provideDataToExcelPass(IpathConstants.VTIGEREXELFILEPATH);

				} else {
					javaUtility.printStatement("Organization  with Industry and type creation is not Validated");
					excelUtility.setDataIntoExcel("Organization", 4, 2, "Fail");
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
