package org.vtiger.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	@FindBy(xpath = "//input[@name='user_name']")
	private WebElement userNameTextField;
	@FindBy(xpath = "//input[@name='user_password']")
	private WebElement passwordTextField;
	@FindBy(xpath = "//input[@id='submitButton']")
	private WebElement loginButton;
	
	public LoginPage(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}
	/**
	 * This method is used to login the application
	 * @param userName
	 * @param password
	 */
	public void loginAction(String userName ,String password)
	{
		userNameTextField.sendKeys(userName);
		passwordTextField.sendKeys(password);
		loginButton.click();
	}
}
