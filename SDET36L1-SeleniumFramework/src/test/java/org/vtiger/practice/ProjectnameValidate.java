package org.vtiger.practice;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.mysql.cj.jdbc.Driver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ProjectnameValidate {

	public static void main(String[] args) throws SQLException {
		Random random=new Random();
		int randomnumber=random.nextInt(1000);
		String expectedprojectName="Vtiger CRM"+randomnumber;
		System.out.println("Expected Project name  is "+expectedprojectName);
		Driver dbdriver=new Driver();
		DriverManager.registerDriver(dbdriver);
		DriverManager.getConnection("jdbc:mysql://localhost:3306/projects","root","root")
		.createStatement().executeUpdate("insert into project values('TY_PROJ_5"+randomnumber+"','Mohan Gowda','23/06/2022','"+expectedprojectName+"','completed',12)");
		System.out.println("Project added into database successfully");
		WebDriverManager.chromedriver().setup();
        WebDriver  driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("http://localhost:8084");
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys("rmgyantra");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("rmgy@9999");
		driver.findElement(By.xpath("//button[text()='Sign in']")).click();
		System.out.println("Successfully Login");
		driver.findElement(By.xpath("//a[.='Projects']")).click();
		List<WebElement> listOfProjects=driver.findElements(By.xpath("//table[@class='table table-striped table-hover']/tbody/tr/td[2]"));
		for(WebElement project:listOfProjects)
		{
			String actualProjectName=project.getText();
			if(actualProjectName.equals(expectedprojectName))
			{
				System.out.println("Project is present in the list of projects page");
				System.out.println("Actual Project name is "+actualProjectName);
				break;
			}
	}
		driver.findElement(By.xpath("//a[.='Logout']")).click();
		driver.quit();
	}
}
