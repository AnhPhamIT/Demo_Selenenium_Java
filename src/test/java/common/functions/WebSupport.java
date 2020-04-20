package common.functions;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * WebSupport to support perform common actions on WebElement.
 * 
 * @parameter Webdriver driver
 */
public class WebSupport {

	WebDriver driver;
	WebDriverWait wait;
	Actions act;

	public WebSupport(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(this.driver, 10);
		this.act = new Actions(driver);
	}

	/**
	 * Support to click on WebElement. Flow: Wait for elm is clickable then click
	 */
	public void clickOnElement(String xpath) {
		WebElement elm = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
		act.moveToElement(elm).build().perform();
		elm.click();
	}

	/**
	 * Support to send keys to WebElement. Flow: Wait for elm is visible then send
	 * keys
	 */
	public void sendKeysToElement(String xpath, String keys) {
		WebElement elm = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		act.moveToElement(elm).build().perform();
		elm.clear();
		elm.sendKeys(keys);
	}

	/**
	 * Support to get text from WebElement. Flow: Wait for elm is visible then get
	 * text
	 */
	public String GetText(String xpath) {
		WebElement elm = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		act.moveToElement(elm).build().perform();
		return elm.getText();
	}
	
	/**
	 * Support to select Dropdownlist from WebElement. Flow: Wait for elm is visible then get
	 * text
	 */
	public void selectDropDown(String xpath, String keys){
		WebElement elem = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
		act.moveToElement(elem).perform();
		elem = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		Select select = new Select(elem);
		select.selectByVisibleText(keys);
	}
	
	public boolean isElementDisplayed(String xpath){
		try {
			wait = new WebDriverWait(driver, 2);
			WebElement ele= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
			return ele.isDisplayed();
		} catch (org.openqa.selenium.NoSuchElementException | org.openqa.selenium.StaleElementReferenceException | org.openqa.selenium.TimeoutException e ) {
			return false;
		}
	}
	
	public void waitForElementToBeGone(String xpath, int timeout){
		if(isElementDisplayed(xpath)){
			wait = new WebDriverWait(driver, timeout);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(xpath)));
		}
	}
	
	public WebElement waitForElementDisplayed(String xpath){
		return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
	}
	
	public List<WebElement> waitForListElementDisplayed(String xpath){
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(xpath)));
	}
}
