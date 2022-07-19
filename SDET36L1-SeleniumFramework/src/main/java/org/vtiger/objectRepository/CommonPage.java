package org.vtiger.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CommonPage {
public CommonPage(WebDriver driver)
{
	PageFactory.initElements(driver,this);
}
	@FindBy(xpath="//a[.='More']")
	private WebElement moreTab;
	@FindBy(xpath="//a[.='Campaigns']")
	private WebElement campaignTab;
	
}

