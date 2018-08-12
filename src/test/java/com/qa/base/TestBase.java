package com.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.LogManager;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.qa.utilities.ExtentManager;
import com.qa.utilities.Testutility;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class TestBase 
{
 
	
public static WebDriver driver;
public static Properties config = new Properties();
public static Properties OR = new Properties();
public static FileInputStream fis;
public static Logger log = Logger.getLogger("devpinoyLogger");
public ExtentReports rep = ExtentManager.getInstance();
public static ExtentTest test;

@BeforeSuite
public void setUp ()
{
	if (driver == null)
	{
		try {
			//C:\Users\Khurram\eclipse-workspace\selenium\DataDrivenFramework\src\test\properties\config.properties
			fis = new FileInputStream(System.getProperty("user.dir")+"//src//test//resources//config.properties");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			config.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try
		{
			fis = new FileInputStream(System.getProperty("user.dir")+"//src//test//resources//OR.properties");
		}catch (FileNotFoundException e)
		{
			e.getMessage();
		}
		try {
			OR.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	if (config.getProperty("browser").equals("chrome"))
	{
		System.setProperty("webdriver.chrome.driver", "D:\\SeleniumPratice\\ChromeDriver\\chromedriver.exe");
		
		driver = new ChromeDriver();
		//log.debug("Chrome browser is launched");
	}
	else if (config.getProperty("browser").equals("FF"))
	{
		System.setProperty("webdriver.gecko.driver", "D:\\SeleniumPratice\\geckodriver\\geckodriver.exe");
		
		driver = new FirefoxDriver();
	}
	else if (config.getProperty("browser").equalsIgnoreCase("IE"))
	{
		System.setProperty("webdriver.ie.driver" , "D:\\SeleniumPratice\\IEDriver\\IEDriverServer.exe");
		
		driver = new InternetExplorerDriver();
	}
	else if (config.getProperty("opera").equals(""))
	{
		System.setProperty("webdriver.opera.driver", "D:\\SeleniumPratice\\OperaDriver\\operadriver_win64\\operadriver.exe");
		
		driver = new OperaDriver();
	}
	else System.out.println("Please enter the right browser name");

	driver.get(config.getProperty("URL"));
	driver.manage().deleteAllCookies();
	driver.manage().window().maximize();
	driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
}

@AfterSuite
public void tearDown()
{
	driver.quit();
	//log.debug("Drivers quit");
}
}
