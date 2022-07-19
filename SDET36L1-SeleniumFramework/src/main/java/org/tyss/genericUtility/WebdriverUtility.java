package org.tyss.genericUtility;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * This class contains all WebDriver actions used in programs
 * 
 * @author DELL
 *
 */
public class WebdriverUtility {
	WebDriver driver;
	Actions act;
	WebDriverWait wait;

	/**
	 * This method is used to choose browser
	 * 
	 * @param browser
	 * @return
	 */
	public WebDriver setupDriver(String browser) {

		switch (browser) {
		case "edge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		default:
			System.out.println("You have entered wrong Browser in property file");
			break;
		}
		return driver;
	}

	/**
	 * This method is used to maximize browser
	 */
	public void maximizebrowser() {
		driver.manage().window().maximize();
	}

	/**
	 * This method is used to use implicit wait
	 * 
	 * @param longTimeout
	 */
	public void implicitwait(long longTimeout) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(longTimeout));
	}

	/**
	 * This method id used to initialize the Explicit wait or Webdriver wait
	 * 
	 * @param timeouts
	 * @param pollingTime
	 */
	public void intilizeExplicitWait(long timeout) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		// wait.pollingEvery(Duration.ofMillis(pollingTime));
		// wait.ignoring(Exception.class);
	}

	/**
	 * This method is used to wait until the element is visible
	 * 
	 * @param element
	 */
	public void waitTillElementVisible(WebElement element) {

		wait.until(ExpectedConditions.visibilityOf(element));
	}

	/**
	 * This method is used to wait until the element is Invisible
	 * 
	 * @param element
	 */
	public void waitTillElementInVisible(WebElement element) {

		wait.until(ExpectedConditions.invisibilityOf(element));
	}

	/**
	 * This method is used to select Url
	 * 
	 * @param url
	 */
	public void openApplication(String url) {
		driver.get(url);
	}

	/**
	 * This method is used for Actions
	 */

	public void initializeActions() {
		act = new Actions(driver);
	}

	/**
	 * This method is used for moving mouse to webElement
	 * 
	 * @param element
	 */
	public void mouseOverElement(WebElement element) {
		act.moveToElement(element).perform();
	}

	/**
	 * This method is used to perform right click action on current mouse location
	 */
	public void rightClickAction() {
		act.contextClick().perform();
	}

	/**
	 * This method is used to perform rightClickAction on particular webElement
	 * 
	 * @param element
	 */
	public void rightClickAction(WebElement element) {
		act.contextClick(element).perform();
	}

	/**
	 * This method is used to switch frame using Index
	 * 
	 * @param index
	 */
	public void switchFrame(int index) {
		driver.switchTo().frame(index);
	}

	/**
	 * This method is used to switch frame using nameOrId
	 * 
	 * @param nameOrId
	 */
	public void switchFrame(String nameOrId) {
		driver.switchTo().frame(nameOrId);
	}

	/**
	 * This method is used to switch frame using WebElement
	 * 
	 * @param element
	 */
	public void switchFrame(WebElement element) {
		driver.switchTo().frame(element);
	}

	/**
	 * This method is based to switch back from frame to parent webpage
	 * 
	 * @param strategy
	 */
	public void switchBackFromFrame(String strategy) {
		switch (strategy.toLowerCase().trim()) {
		case "default":
			driver.switchTo().defaultContent();
			break;
		case "parent":
			driver.switchTo().parentFrame();
			break;
		default:
			System.out.println("Please enter valid strategy either default or parent");
			break;
		}
	}

	/**
	 * This Method is used to handle Window Popup
	 */
	public void windowPopup(String partialText, String strategy) {

		Set<String> winIds = driver.getWindowHandles();
		for (String id : winIds) {
			driver.switchTo().window(id);
			if (strategy.equalsIgnoreCase("url")) {
				if (driver.getCurrentUrl().contains(partialText)) {
					break;
				}
			} else if (strategy.equalsIgnoreCase("title")) {
				if (driver.getTitle().contains(partialText)) {
					break;
				}
			}

		}

	}

	/**
	 * This method is used to handle <Select> tag Dropdown by using Visible text
	 * 
	 * @param dropdownElement
	 * @param visibleText
	 */
	public void handleSelectDropdown(WebElement dropdownElement, String visibleText) {
		Select select = new Select(dropdownElement);
		select.selectByVisibleText(visibleText);
	}

	/**
	 * This method is used to handle <Select> tag Dropdown by using value attribute
	 * 
	 * @param dropdownElement
	 * @param visibleText
	 */
	public void handleSelectDropdown(String value, WebElement dropdownElement) {
		Select select = new Select(dropdownElement);
		select.selectByValue(value);
	}

	/**
	 * This method is used to perform Dropdown by using index number
	 * 
	 * @param dropdownelement
	 * @param index
	 */
	public void handleSelectDropdown(WebElement dropdownelement, int index) {
		Select select = new Select(dropdownelement);
		select.selectByIndex(index);
	}

	/**
	 * This method is used to close All opened Browser
	 */
	public void closeBrowser() {
		driver.quit();
	}

	/**
	 * This method is used to close current browser
	 */
	public void closeTab() {
		driver.close();
	}

}