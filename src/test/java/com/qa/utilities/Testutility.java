package com.qa.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.DataProvider;

import com.qa.base.TestBase;
import com.relevantcodes.extentreports.LogStatus;

public class Testutility extends TestBase
{
      public static void click (String location)
      {
    	  driver.findElement(By.cssSelector(location)).click();
    	 test.log(LogStatus.INFO, "Clicking on : "+location);
      }
      
      public static void sendkeys(String location , String input)
      {
    	  driver.findElement(By.cssSelector(location)).sendKeys(input);
    	  test.log(LogStatus.INFO, "Sending the values to location : "+ location + "With values " +input);
      }
      
      private static String TEST_DATA_SHEETPATH = System.getProperty("user.dir")+ "\\src\\test\\excel\\testData.xlsx";
     private static Workbook book;
     private static Sheet sheet;
     
      public static Object[][] getTestData(String sheetName)
      {
    	FileInputStream file = null;
    	
    	try 
    	{
    		file = new FileInputStream(TEST_DATA_SHEETPATH);
    	}
    	  catch(FileNotFoundException e)
    	{
    		  e.printStackTrace();
    	}
    	
    	try 
    	{
    		book = WorkbookFactory.create(file);
    	}
    	catch (InvalidFormatException e)
    	{
    		e.printStackTrace();
    	}
    	  catch (IOException e)
    	{
    		  e.printStackTrace();
    	}
    	sheet = book.getSheet(sheetName);
    	
    	Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
    	System.out.println(sheet.getLastRowNum());
    	for (int row = 1; row <=sheet.getLastRowNum() ;row++)
    	{
    		for (int col =0; col < sheet.getRow(row).getLastCellNum() ;col++)
    	 	{ 
    			data[row-1][col] = sheet.getRow(row).getCell(col).toString();
    		//	System.out.println(data[row-1][col]);
    		}
    	}
    	 
    	return data;
      }
      
      @DataProvider(name ="dp")
      public static Object[][] getdataFromExcelSheet(Method m)
      {
    	 Object[][] data = getTestData(m.getName());
    	 return data;
      }
      
      
      public static String imgPath; 
      public static void takeScreenShoot(String screenShotname) throws IOException
      {
    	  File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    	  imgPath = "C:\\Users\\Khurram\\eclipse-workspace\\selenium\\DataDrivenFramework\\src\\test\\ScreenShots\\"+screenShotname+".png";
    	 FileHandler.copy(file, new File(imgPath));
      }
      
      
}
