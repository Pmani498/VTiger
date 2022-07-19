package org.vtiger.practice;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Dynamicxpath {

	public static void main(String[] args) {
	WebDriverManager.chromedriver().setup();
	WebDriver driver =new ChromeDriver();
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    driver.get("https://www.hotstar.com/in");
    List<WebElement>list=driver.findElements(By.xpath("//a[text()='Latest & Trending']/../../../div[2]/div/div/div/div"));
    Iterator<WebElement>itr=list.iterator();
    System.out.println(list.size());
    List<WebElement>list2=driver.findElements(By.xpath("//a[text()='Latest & Trending']/../../../div[2]/div/div/div/div/div/div/div/article/a"));
    Iterator<WebElement>itr2=list.iterator();
    while(itr.hasNext() && itr2.hasNext())
    {
    	String s2=itr.next().getAttribute("data-index")+" "+itr2.next().getAttribute("to").split("/")[3].replaceAll("-"," " );
    	System.out.println(s2);
    }
	}
}
