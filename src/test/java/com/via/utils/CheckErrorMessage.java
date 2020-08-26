package com.via.utils;

import org.openqa.selenium.WebDriver;

public class CheckErrorMessage extends BasePage{

	public CheckErrorMessage(WebDriver driver) {
		super(driver);
	}
	public String errorMessage() throws Exception{
	
			if(k.getElement(prop.getProperty("ERROR")).isDisplayed()){		//check if any error message pop-ups
			return k.getElement(prop.getProperty("ERROR")).getText();}		// return the error message
			return "no error message";
	}
	
	
}
