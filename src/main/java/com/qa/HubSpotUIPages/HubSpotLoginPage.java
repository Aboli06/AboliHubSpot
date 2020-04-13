package com.qa.HubSpotUIPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.HubSpotBase.HubSpotBase;
import com.qa.HubSpotUtil.AppConstants;
import com.qa.HubSpotUtil.Credentials;
import com.qa.HubSpotUtil.ElementsUtil;

public class HubSpotLoginPage extends HubSpotBase {
	
	WebDriver driver;
	ElementsUtil elementUtil ;
	
	
	
	By userName = By.id("username");
	By passWord = By.id("password");
	By loginButton = By.id("loginBtn");
	By signUpLink = By.linkText("Sign up");
	By errorMessage = By.xpath("//div[@class='private-alert__inner']/h5");
	
	
	public HubSpotLoginPage(WebDriver driver) {
		this.driver=driver;
		elementUtil = new ElementsUtil(driver);
	}
	
	
	
	public String getTitle() {
		elementUtil.waitForTitle(AppConstants.LOGIN_PAGE_TITLE);
		return elementUtil.getPageTitle();
				
	}
	
	
	public boolean checkSignUpLink() {
	
		elementUtil.waitForElementPresent(signUpLink);
		return elementUtil.diIsDisplayed(signUpLink);

		
	}
	
	public HubSpotHomePage doLogin(Credentials userCred) {
		/*driver.findElement(this.userName).sendKeys(username);
		driver.findElement(passWord).sendKeys(password);
		driver.findElement(loginButton).click(); */
		
		elementUtil.waitForElementPresent(userName);
		elementUtil.doSendKeys(userName, userCred.getappUserName());
		elementUtil.doSendKeys(passWord, userCred.getappPassword());
		elementUtil.doClickBy(loginButton);
		
		
		return new HubSpotHomePage(driver);
	}

	
	public boolean checkLoginErrorMessage() {
		return elementUtil.diIsDisplayed(errorMessage);
	}
	
}
