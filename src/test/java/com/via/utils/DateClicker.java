package com.via.utils;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DateClicker extends BasePage{         
	public DateClicker(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	KeyWords key;
	
	public void clickDate(String date,String getmonth,String nextclick,String getalldates) {
	//	prop = new Properties();
		
		String[] d_arr = date.split("-"); 
	       String newdate =d_arr[0]+"-"+d_arr[1]+" "+d_arr[2];    
			
		String day = newdate.split("-")[0];       
		String month = newdate.split("-")[1];
		System.out.println(month);
	 while (true) {		 
		 String text = driver.findElement(By.xpath(prop.getProperty(getmonth))).getText();
		 System.out.println(text);
		 if(text.equals(month)) {
			 break;
		 }
		 else {
			driver.findElement(By.xpath(prop.getProperty(nextclick))).click();
		 }	 
	 }
	 List<WebElement> alldates = driver.findElements(By.xpath(prop.getProperty(getalldates)));
	 for (WebElement el:alldates) {
		String d = el.getAttribute("data-date");
		if(d.equals(day)) {
			el.click();
		    break;
		}	  
	 }	
	}
}
