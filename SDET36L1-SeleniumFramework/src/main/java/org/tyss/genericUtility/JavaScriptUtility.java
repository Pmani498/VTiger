package org.tyss.genericUtility;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 This class contains all the JS reusable Methods and actions
 */
public class JavaScriptUtility {
	  JavascriptExecutor jse;

	/**\
	 *  Initialize the javascript executor parameter driver
	 * @param driver
	 */
	public void intializeJsExecutor(WebDriver driver) {

		jse = (JavascriptExecutor) driver;
	}
	/**
	 * Navigate app by using JavascriptExecutor
	 * @param url
	 */
	public void navigateApp(String url)
	{
		jse.executeScript("window.location=arguments[0]",url);
	}
	/**
	 * This method is used to send the data to text field using Javascript executor
	 * @param element
	 * @param data
	 */
	 public void enterData(WebElement element,String data)
	 {
		 jse.executeScript("arguments[0].value=arguments[1]",element,data);
	 }
	 /**
	  * This  method is used to click on the element Using Javascript executor
	  * @param element
	  */
	 public void clickOnElement(WebElement element)
	 {
		 jse.executeScript("arguments[0].click()",element);
	 }
	 /**
	  * This  method is used to scroll till the end of the page
	  * @param strategy
	  */
	 public void scrollTillEnd(String strategy)
	 {
		 String sign=strategy.equalsIgnoreCase("up")?"-":"+";
		 jse.executeScript("window.scrollBy(0,"+sign+"documents.body.scrollHeight)");
	 }
	 /**
	  * This method is used to scroll till some position
	  * @param y_position
	  * @param strategy
	  */
	 public void scrollTillSomePosition(int y_position,String strategy)
	 {
		 String sign=strategy.equalsIgnoreCase("up") ?"-":"+";
		 jse.executeScript("window.scrollBy(0,"+sign+"aruments[0])",y_position);
	 }
	 /**
	  * This method is used to scroll till the element is visible
	  * @param element
	  */
	 public void scrollTillElement(WebElement element)
	 {
		 jse.executeScript("arguments[0].scrollIntoView()",element);
	 }
	 /**
	  * This method is used to highlight the element
	  * @param element
	  */
	 public void highlightElement(WebElement element)
	 {
		 jse.executeScript("arguments[0].setAttribute('style', 'border:5px solid red;background:yellow')",element);
	 }
	 
}















