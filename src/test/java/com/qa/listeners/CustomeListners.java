package com.qa.listeners;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.qa.base.TestBase;
import com.qa.utilities.Testutility;
import com.relevantcodes.extentreports.LogStatus;

public class CustomeListners extends TestBase implements ITestListener{

	public void onTestStart(ITestResult result) 
	{
	     System.out.println("TestCase started ----" +result.getName());
	}

	public void onTestSuccess(ITestResult result) 
	{
		
		test.log(LogStatus.PASS, result.getName().toUpperCase()+" Pass");
		rep.endTest(test);
		rep.flush();
		   try {
				Testutility.takeScreenShoot(result.getMethod().getMethodName()+"_TestCasesuccess");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
	} 

	public void onTestFailure(ITestResult result) 
	{
		test.log(LogStatus.FAIL, result.getName().toUpperCase()+" failed with the expection : "+ result.getThrowable());
		test.log(LogStatus.FAIL, test.addScreenCapture(Testutility.imgPath));
		rep.endTest(test);
		rep.flush();
		
	          try {
				Testutility.takeScreenShoot(result.getMethod().getMethodName());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}

	public void onTestSkipped(ITestResult result)
	{
		// TODO Auto-generated method stub
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result)
	{
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context)
	{
		test = rep.startTest(context.getName().toUpperCase());
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

}
