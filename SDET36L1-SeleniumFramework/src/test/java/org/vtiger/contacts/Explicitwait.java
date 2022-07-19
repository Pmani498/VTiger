package org.vtiger.contacts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Explicitwait {
	@FindBy(xpath="//span[@class='small']")
	private WebElement explicit;

	public Explicitwait(WebDriver driver) {
		
		PageFactory.initElements(driver,this);
	}

	public WebElement explicitWait()
	{
		return explicit;
	}

}
