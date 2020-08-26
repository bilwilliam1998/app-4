package com.via.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class KeyWords {

	WebDriver driver;
	WebElement element;
	
	public KeyWords(WebDriver driver) {
		this.driver=driver;
	}
	
	
	//get url of website
	public void getURL(String URL) {
		driver.get(URL);

	}
	
	
	//get type and value of locator for WebElement
	public WebElement getElement(String locator) {
		
			return driver.findElement(By.xpath(locator));	
	}
	
	
	
	//  click on required element
	public void click(String locator) {
		
		element = getElement(locator);
		element.click();

	}
	
	
	
	// type on fields or sendkeys
	public void type(String locator,String data) {
		
		element = getElement(locator);
		element.sendKeys(data);
		
	}
	
	
	
	// dropdowns handling
	public void selectFromDropDown(String locator, String data) {
		
		Select s = new Select(getElement(locator));
		s.selectByVisibleText(data);   //select using visibletext on screen

	}
	
	
	
	// get snap of page
	public  void getSnap(String fileName) {
		TakesScreenshot tc=(TakesScreenshot) driver;
		File scFile=tc.getScreenshotAs(OutputType.FILE);
		try {
			FileHandler.copy(scFile, new File(fileName));
		} catch (IOException e) {
			System.out.println("Error with Snap");
		}
	}
	
	
	
	
	// handle search bar
	public void searchFligth(String from , String to) {

		getElement("//input[@id='source']").clear();
		// type suggestion
		getElement("//input[@id='source']").sendKeys(from);
		//choose initial suggestion
	    getElement("//li[@class='ui-menu-item']//span[contains(text(),'"+from+"')]").click();
	    
		getElement("//input[@id='destination']").clear();
		getElement("//input[@id='destination']").sendKeys(to);
		getElement("//li[@class='ui-menu-item']//span[contains(text(),'"+to+"')]").click();

	}
	
	
	
	
	// select departure date
	  public void deparureDate(String date) throws Exception {
	    	String day = date.split("-")[0];
	    	String month = date.split("-")[1];
	    	
		 while (true) {		 
			 String text = driver.findElement(By.xpath("//div[@class='container']//div[3]//div[1]//span[2]")).getText();	 
			 if(text.equals(month)) {
				 break;
			 }
			 else {
				 driver.findElement(By.xpath("//span[contains(@class,'vc-month-box-head-cell vc-month-controls icon-Rightarrowthin vc-month-control-active js-next-month')]")).click();
			 }	 
		 }
		 List<WebElement> alldates = driver.findElements(By.xpath("//div[contains(@class,'container')]//div[3]//div[2]//div[@class='vc-cell ']"));
		 for (WebElement el:alldates) {
			String d = el.getAttribute("data-date");
			if(d.equals(day)) {
				el.click();
			    break;
			}	  
	     }	
	  }

	  
	  
	  
	  
	  
	// close browser
	public void closeBrowser() {
		driver.quit();
	}

}