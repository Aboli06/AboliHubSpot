package com.qa.HubSpotUIPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.qa.HubSpotBase.HubSpotBase;
import com.qa.HubSpotUtil.AppConstants;
import com.qa.HubSpotUtil.ElementsUtil;

public class HubSpotHomePage extends HubSpotBase {
	
	WebDriver driver;
	ElementsUtil elementUtil;
	By header = By.xpath("//h1[@class='private-page__title']");
	By account = By.id("account-menu");
	By userNameLink = By.xpath("//a[@id='account-menu']");
	By username = By.xpath("//div[@class='user-info-name']");
	By mainContactsLink = By.id("nav-primary-contacts-branch");
	By childContactsLink = By.id("nav-secondary-contacts");
	
	public HubSpotHomePage(WebDriver driver) {
		this.driver=driver;
		elementUtil = new ElementsUtil(driver);
		
		
	}
	
	public String getHomePageTitle() {
		elementUtil.waitForTitle(AppConstants.HOME_PAGE_TITLE);
		return elementUtil.getPageTitle();
		
	}
	
	public String getHomePageHeader() {
		elementUtil.waitForElementVisible(header);
		return elementUtil.doGetText(header);
		
		
	
	}
	
	public String getUserName() {
		
		elementUtil.doClickBy(userNameLink);
		return elementUtil.doGetText(username);
		
	}
	
	public void clickOnContacts() {
		elementUtil.waitForElementPresent(mainContactsLink);
		elementUtil.doClickBy(mainContactsLink);

		elementUtil.waitForElementPresent(childContactsLink);
		elementUtil.doClickBy(childContactsLink);

		}

		public ContactsPage goToContactsPage() {
		clickOnContacts();
		return new ContactsPage(driver);
		}
	
	
}
