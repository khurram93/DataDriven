package com.qa.testcases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.utilities.Testutility;

public class BankManagerTest extends TestBase
{
   
	@Test
	public void bankManagerButtonClick()
	{
		Testutility.click(OR.getProperty("Bank_manager_login"));
		//log.debug("Successfully clicked on BankManager button");
		Assert.assertTrue(driver.findElement(By.cssSelector(OR.getProperty("HomeButton"))).isDisplayed(), "Failed to navigate to managerScreen");
	}
	
	
}
