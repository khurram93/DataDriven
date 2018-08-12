package com.qa.utilities;

import java.io.File;

import com.qa.base.TestBase;
import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager extends TestBase
{
       public static ExtentReports extent;
       
       public static ExtentReports getInstance()
       {
    	   if (extent == null)
    	   {
    		   extent = new ExtentReports(System.getProperty("user.dir")+"//target//surefire-reports//extentReport.html" ,true,DisplayOrder.OLDEST_FIRST);
    		   extent.loadConfig(new File(System.getProperty("user.dir")+"\\src\\test\\resources\\extentReport\\extentReportConfig.xml"));
    	   }
    	   return extent;
       }
}
