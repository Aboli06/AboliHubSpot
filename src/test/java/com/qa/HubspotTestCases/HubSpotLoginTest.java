package com.qa.HubspotTestCases;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.HubSpotBase.HubSpotBase;
import com.qa.HubSpotUIPages.HubSpotHomePage;
import com.qa.HubSpotUIPages.HubSpotLoginPage;
import com.qa.HubSpotUtil.AppConstants;
import com.qa.HubSpotUtil.Credentials;

import junit.framework.Assert;

public class HubSpotLoginTest {
	
	HubSpotBase base;
	Properties prop ;
	WebDriver driver;
	HubSpotLoginPage hubLogin ;
	Credentials userCred;
	
	
	@BeforeTest
	public void setup() {
		
		base = new HubSpotBase();
		prop = base.inint_properties();
		String browserName = prop.getProperty("browser");
		driver = base.init_driver(browserName);
		driver.get(prop.getProperty("url"));
		hubLogin = new HubSpotLoginPage(driver);
		userCred = new Credentials(prop.getProperty("username"), prop.getProperty("password"));
		
		
		
	}
	
	@Test(priority=1)
	
	public void verifyLoginPageTitle() {
	String title = hubLogin.getTitle();
	System.out.println("Login Page Title is :" + title);
	Assert.assertEquals(title, AppConstants.LOGIN_PAGE_TITLE);
	
	}
	
	@Test(priority =2)
	public void verifySignUpLink() {
		Assert.assertTrue(hubLogin.checkSignUpLink());
	}
	
	@Test(priority=3)
	public void loginTest() {
		HubSpotHomePage homePage = hubLogin.doLogin(userCred);
		/**String user  = homePage.getUserName();
		Assert.assertEquals(user, prop.getProperty("accountname"));**/
	}
	
	 
	
	@DataProvider
	public Object[][] InvalidCredetials() {
		
		Object data[][] = {
				{"test1@t@gmail.com", "t@12345"},
				{"test3@t@gmail.com", " "},
				{" ","D2@1234566"},
				{" ", " "},
				{"test","test"}
		};
		
		return data ;
	}
	
	@Test(priority=4 , dataProvider = "InvalidCredetials" ,enabled=false)
	public void loginTestInvalidCredetials1(String username , String pwd) {
		userCred.setappPassword(pwd);
		userCred.setappUserName(username);
		hubLogin.doLogin(userCred);
		Assert.assertTrue(hubLogin.checkLoginErrorMessage());
		
	} 
	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
