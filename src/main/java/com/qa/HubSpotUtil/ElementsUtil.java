package com.qa.HubSpotUtil;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.HubSpotBase.HubSpotBase;

public class ElementsUtil extends  HubSpotBase {

	WebDriver driver;
	WebDriverWait wait;
	JavaScriptUtil jsUtil;
	
	public ElementsUtil(WebDriver driver) {
		this.driver=driver;
		wait = new WebDriverWait(driver , AppConstants.DEFAULT_TIME_OUT);
		jsUtil = new JavaScriptUtil(driver);
		
	}
	
	
	public boolean waitForElementPresent(By locator) {
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		return true;
		
	}
	
	public boolean waitForElementVisible(By locator) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		return true;
		
	}
	
	public boolean waitForTitle(String title) {
		wait.until(ExpectedConditions.titleIs(title));
		return true;
	}
	
	public WebElement getElement(By locator) {
		WebElement element = null;
		try {
		 element = driver.findElement(locator);
		 if(highlightElement) {
		jsUtil.flash(element);	 
		 }
		}
		catch(Exception e) {
			System.out.println("Some exception got occured while creating the web element..." + locator.toString());
		}
		return element;
	}
	
	public void doClickBy(By locator)
	{
		try {
		getElement(locator).click();
	}
		catch(Exception e) {
			System.out.println("Some exception got occured while creating clicking on the web element.. ");
		}
	}
	
	public void doSendKeys(By locator, String value) {
		try {
		WebElement ele = getElement(locator);
		ele.clear();
		ele.sendKeys(value);
		}
		catch (Exception e ) {
			System.out.println("Some exception got occured while creating entering value in a field .t.. ");
		}
	}
	
	public boolean diIsDisplayed(By locator) {
		try {
	return	getElement(locator).isDisplayed();
		}
		catch(Exception e){
			System.out.println("Some exception got occured as elements is not displayed....");
			
		}
		return false;
		
	}
	
	public String doGetText(By locator) {
		try {
			
		return getElement(locator).getText();
		
		}
		catch(Exception e) {
			System.out.println("Some exception got occured while  getting text value from field ...");
		}
		return null;
		
	}
	
	public String getPageTitle() {
		try {
			return driver.getTitle();
			}
		catch (Exception e) {
			System.out.println("Some exception occured while getting title ");
		}
		return null;
	}
	
	
	
}
