package com.qa.HubSpotBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.codehaus.plexus.util.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.aventstack.extentreports.utils.FileUtil;

public class HubSpotBase {
	
	
	WebDriver driver;
	Properties prop;
	public static boolean highlightElement;
	
	
	public WebDriver init_driver(String browserName) {
		highlightElement = prop.getProperty("highlight").equals("yes") ? true : false;
		
		System.out.println(" Browser name is : " + browserName);
		
		if(browserName.equals("chrome")) {
			
			if(prop.getProperty("headless").equals("yes")) {
			 ChromeOptions co = new ChromeOptions();
			co.addArguments("--headless");
			driver = new ChromeDriver(co); 
		}
			else {
				driver = new ChromeDriver();
			}
		}
		else if(browserName.equals("firefox"))
		{
			driver=new FirefoxDriver();
		}
		
		else
		{
			System.out.println("Invalid Browser Name :"+browserName);
		}
		
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS); 
		//driver.get("url");
		
		return driver;
		
		
	}
	
	public Properties inint_properties() {
		
		prop = new Properties();
		String path = null;
		String env = null;
		try {
			
			env = System.getProperty("env");

			if (env.equals("qa")) {
			path = "./src/main/java/com/qa/HubSpotConfig/qa.config.properties";
			} else if (env.equals("stage")) {
			path = "./src/main/java/com/qa/HubSpotConfig/stage.config.properties";
			}
			} catch (Exception e) {
			path = "./src/main/java/com/qa/HubSpotConfig/HubSpotConfig";
			}

		
	try {
		FileInputStream ip = new FileInputStream(path);
		prop.load(ip);
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		System.out.println("Some issues with config properties...Please correct your config ");
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return prop;
	
		
	}

	
	public String getScreenshot() {
	File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	String path =System.getProperty("user.dir")+ "/ScreenShots" + System.currentTimeMillis()+ ".png";
	File destination = new File(path);
	try {
		FileUtils.copyFile(src, destination);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		System.out.println("Screenshot captured failed....");

	}
	return path;
	}
	
	
	/**public  void takeScreenshotAtEndOfTest() throws IOException {
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		FileUtils.copyFile(scrFile, new File(currentDir + "/ScreenShots/" + System.currentTimeMillis() + ".png"));
		
	}**/
	
}
