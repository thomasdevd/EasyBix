package com.Utility;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;


public class CaptureScreenshot {
private static final DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd SSS");
	
	public static String captureScreen(WebDriver driver, String fileName) throws IOException{
		
	/*	TakesScreenshot screen = (TakesScreenshot) driver;
		//File src = screen.getScreenshotAs(OutputType.FILE);
		String src = screen.getScreenshotAs(OutputType.BASE64);
		
		String dest = System.getProperty("user.dir")+"//Test-ScreenShots//"+screenName+".png";
		
		File target = new File(dest);
		FileUtils.copyFile(src, target);
		String dest = "data:image/jpg;base64, " + src ;
		
		return dest;
	}*/
		
		fileName = fileName + ".png";
		String directory = "E://seleninmeasy//easybix//test-output//Test-ScreenShots//"+fileName+".png";
		File sourceFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(sourceFile, new File(directory + fileName));
		return directory;
	}
		
		
	
	public static String generateFileName(ITestResult result){
		Date date = new Date();
		String fileName = result.getName()+ "_" + dateFormat.format(date);
		return fileName;	
	}
}
