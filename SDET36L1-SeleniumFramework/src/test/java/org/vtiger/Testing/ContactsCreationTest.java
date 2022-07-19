package org.vtiger.Testing;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ContactsCreationTest {

	public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
		WebDriver  driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("http://localhost:8888");
		
		driver.findElement(By.cssSelector("[name='user_name']")).sendKeys("admin",Keys.TAB,"root",Keys.TAB,Keys.ENTER);
		driver.findElement(By.xpath("//a[text()='Contacts']")).click();
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		String expectedContactName="Testyantra";
		driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(expectedContactName);
		driver.findElement(By.cssSelector("[title='Save [Alt+S]']")).click();
		String actualContactName=driver.findElement(By.xpath("//span[@id='dtlview_Last Name']")).getText();
		if(actualContactName.equals(expectedContactName))
		{
			System.out.println("Contact creation is Validated");
		}
		else
		{
			System.out.println("Contact creation is Not validated");
		}
		WebElement administratorIcon=driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions act=new Actions(driver);
		act.moveToElement(administratorIcon).perform();
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		driver.quit();
		
	}

}
