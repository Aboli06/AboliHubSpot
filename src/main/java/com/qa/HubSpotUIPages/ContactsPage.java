package com.qa.HubSpotUIPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.HubSpotUtil.ElementsUtil;
import com.qa.HubSpotUtil.JavaScriptUtil;

public class ContactsPage {
	
	WebDriver driver;

	ElementsUtil elementUtil;

	JavaScriptUtil jsUtil;



	By createContactButton = By.xpath("//button[@type='button']//span[text()='Create contact']");

	By createContactFormButton = By.xpath("//button/span[text()='Create contact']");



	By email = By.xpath("//input[@data-field='email']");

	By firstName = By.xpath("//input[@data-field='firstname']");

	By lastName = By.xpath("//input[@data-field='lastname']");

	By jobTitle = By.xpath("//input[@data-field='jobtitle']");



	public  ContactsPage(WebDriver driver) {

		this.driver = driver;

		elementUtil = new ElementsUtil(driver);

		jsUtil = new JavaScriptUtil(driver);



	}



	public String getContactsPageTitle() {

	elementUtil.waitForTitle("Contacts");

	return elementUtil.getPageTitle();

	}



	public void createNewContact(String mail, String FN, String LN, String jobtitle) {

		// Thread.sleep(5000);

		elementUtil.waitForElementPresent(createContactButton);

		elementUtil.doClickBy(createContactButton);



		elementUtil.waitForElementPresent(email);

		elementUtil.doSendKeys(email, mail);



		elementUtil.doSendKeys(firstName, FN);



		elementUtil.doSendKeys(lastName, LN);



		elementUtil.doSendKeys(jobTitle, jobtitle);



		// elementUtil.doClick(createContactFormButton);

		jsUtil.clickElementByJS(elementUtil.getElement(createContactFormButton));



	}


	

}
