package com.qa.HubspotTestCases;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.HubSpotBase.HubSpotBase;
import com.qa.HubSpotUIPages.HubSpotHomePage;
import com.qa.HubSpotUIPages.HubSpotLoginPage;
import com.qa.HubSpotUtil.AppConstants;
import com.qa.HubSpotUtil.Credentials;

import junit.framework.Assert;

public class HubSpotHomeTest  {

	HubSpotBase base;
	Properties prop ;
	WebDriver driver;
	HubSpotLoginPage hubLogin ;
	HubSpotHomePage homePage;
	Credentials cred;
	
	
	@BeforeTest
	public void setup() throws InterruptedException {

		base = new HubSpotBase();
		prop = base.inint_properties();
		String browserName = prop.getProperty("browser");
		driver = base.init_driver(browserName);
		driver.get(prop.getProperty("url"));
		hubLogin = new HubSpotLoginPage(driver);
		cred = new Credentials(prop.getProperty("username"), prop.getProperty("password"));
		homePage = hubLogin.doLogin(cred);
		
	

	}
	
	@Test(priority=0)
	public void getHomePageTitleTest() {
	String actTitle = 	homePage.getHomePageTitle();
	System.out.println("Title is : " +actTitle);
		Assert.assertEquals(actTitle, AppConstants.HOME_PAGE_TITLE);
	}
	
	@Test(priority=1)
	public void homePageHeaderTest() {
		String header = homePage.getHomePageHeader();
		System.out.println("Home Page Header is : "+header);
		Assert.assertEquals(header, AppConstants.HOME_PAGE_HEADER);
		
	}
	
	@Test(priority =3)
	public void getUserNameTest(){
	String user=	homePage.getUserName();
	System.out.println(user);
	Assert.assertEquals(user, prop.getProperty("accountname"));
	}
	
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}