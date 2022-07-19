package org.vtiger.practice;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.tyss.genericUtility.WebdriverUtility;

import com.mysql.cj.jdbc.Driver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateProjectRmgy {

	public static void main(String[] args) throws SQLException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		WebdriverUtility webdriverUtility = new WebdriverUtility();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("http://localhost:8084");
		Random random = new Random();
		int randomnumber = random.nextInt(1000);
		String projectName = "Vtiger CRM" + randomnumber;
		System.out.println("Project name  is " + projectName);
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys("rmgyantra");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("rmgy@9999");
		driver.findElement(By.xpath("//button[text()='Sign in']")).click();
		System.out.println("Successfully Login");
		driver.findElement(By.xpath("//a[text()='Projects']")).click();
		driver.findElement(By.xpath("//span[.='Create Project']")).click();
		driver.findElement(By.xpath("//input[@name='projectName']")).sendKeys(projectName);
		driver.findElement(By.xpath("//input[@name='createdBy']")).sendKeys("Mohan Gowda");
		WebElement ProjectstatusDropdown = driver
				.findElement(By.xpath("//label[.='Project Status ']/following-sibling::select"));
		String visibleText = "Created";
		webdriverUtility.handleSelectDropdown(ProjectstatusDropdown, visibleText);
		// Select dropdown=new Select(ProjectstatusDropdown);

		driver.findElement(By.xpath("//input[@value='Add Project']")).click();
		System.out.println();
		Driver dbdriver = new Driver();
		DriverManager.registerDriver(dbdriver);
		ResultSet result = DriverManager.getConnection("jdbc:mysql://localhost:3306/Projects", "root", "root")
				.createStatement().executeQuery("select * from project;");
		while (result.next()) {
			if (result.getString("project_name").equals(projectName)) {
				System.out.println("Project is created");
				System.out.println("Actual Project name is " + result.getString("project_name"));
			}
		}
		driver.quit();
	}

}
