package com.Report;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.Utility.CaptureScreenshot;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtentReport {
	
/*	ExtentReports reports;
	ExtentHtmlReporter htmlReport;
	ExtentTest test;
	
	@BeforeTest
	public void startTest(){
		reports = new ExtentReports();
		
//		htmlReport = new ExtentHtmlReporter(System.getProperty("User.dir")+"//test-output//extentreport.html");
		htmlReport = new ExtentHtmlReporter("E://seleninmeasy//easybix//test-output//extentreport.html");
		reports.attachReporter(htmlReport);
	}
	

	@Test
	public void passTest()
	{
		test = reports.createTest("passTest");
		System.out.println("This my pass test");
		Assert.assertTrue(true);
		test.pass("first test passed");
	}
	
	@Test
	public void failTest()
	{
		test = reports.createTest("failTest");
		System.out.println("This my fail test");
		Assert.assertTrue(false);
		test.pass("first test passed");
	}
	
	@Test
	public void skipTest()
	{
		test = reports.createTest("skipTest");
		System.out.println("This my skip test");
		Assert.assertTrue(true);
		throw new SkipException("skip test forcefully");
	}
	
	@AfterMethod
	public void setTestResult(ITestResult result)
	{
		if(result.getStatus() == ITestResult.FAILURE){
			test.fail(result.getTestName());
			test.fail(result.getThrowable());
		}else if(result.getStatus() == ITestResult.SUCCESS){
			test.pass(result.getTestName());
		}else if(result.getStatus() == ITestResult.SKIP){
			test.skip("Test Case : " + result.getTestName() + "has been skiped");
		}
		
	}
	
	@AfterTest
	public void endTest(){
		reports.flush();
	}*/
	
	//Reporting in selenium
	
	ExtentReports reports;
	ExtentHtmlReporter htmlReport;
	ExtentTest test;
	WebDriver driver;

	@BeforeTest
	public void startTest() {
		reports = new ExtentReports();
//		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "//test-output//extentreport.html");
		htmlReport = new ExtentHtmlReporter("E://seleninmeasy//easybix//test-output//extentreport.html");
		reports.attachReporter(htmlReport);
	
	}

	@BeforeMethod
	public void openApplication() {
		System.setProperty("webdriver.chrome.driver", "E://Driver//chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		driver.get("https://www.facebook.com");
	}

	@Test
	public void verifyTittleTest() {
		test = reports.createTest("verifyTittleTest");
		String expetedTitle = "Facebook – log in or sign up";
		String pageTitle = driver.getTitle();
		Assert.assertEquals(pageTitle, expetedTitle);
	}

	@Test
	public void fillRegistrationformTest() {
		test = reports.createTest("fillRegistrationformTest");

		WebElement firstName = driver.findElement(By.cssSelector("input[id='u_0_2']"));
		WebElement lastName = driver.findElement(By.name("lastname"));
		WebElement submitButton = driver.findElement(By.id("u_0_m"));

		firstName.sendKeys("Anshul");
		lastName.sendKeys("Chauhan");
		submitButton.click();
		String expectedTitle = "FaceBook Home";
		String pageTitle = driver.getTitle();
		Assert.assertEquals(pageTitle, expectedTitle);
	}

	@AfterMethod
	public void setTestResult(ITestResult result) throws IOException {

		String screenShot = CaptureScreenshot.captureScreen(driver, result.getName());

		if (result.getStatus() == ITestResult.FAILURE) {
			test.log(Status.FAIL, result.getName());
			test.log(Status.FAIL,result.getThrowable());
			test.fail("Screen Shot : " + test.addScreenCaptureFromPath(screenShot));
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, result.getName());
			test.pass("Screen Shot : " + test.addScreenCaptureFromPath(screenShot));
		} else if (result.getStatus() == ITestResult.SKIP) {
			test.skip("Test Case : " + result.getName() + " has been skipped");

		}
 
		reports.flush();
		driver.close();
	}

	@AfterTest
	public void endTest() {
		
		
		reports.flush();
	}

}
