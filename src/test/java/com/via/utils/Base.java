package com.via.utils;


import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

import org.testng.annotations.AfterTest;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;


@Listeners({ com.via.utils.TestReport.class })
public class Base {
	public static WebDriver driver;
	public Properties prop;
	protected static ExtentTest testLog;
	protected static ExtentReports extentReporter;
	public KeyWords k;

	
	
	@BeforeTest 		// Pre-Conditions
	public void beforeTest() throws Exception {
		prop = PropertyReader.getInstance();
		driver = HelperFunctions.setBrowser(prop.getProperty("browser"));
		driver.manage()
		.timeouts()
		.implicitlyWait(Integer.parseInt(prop.getProperty("implicit.wait")),TimeUnit.SECONDS);
	}

	@AfterTest			 // Post- COndition
	public void afterTest() {
		k= new KeyWords(driver);
		k.closeBrowser();
		 
	}

}
