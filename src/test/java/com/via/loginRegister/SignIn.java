package com.via.loginRegister;



import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;
import com.via.utils.BasePage;


public class SignIn extends BasePage{
	
	public SignIn(WebDriver driver) {
		super(driver);
	}
	// this function clears the alert messages that pop-up in the page and navigate to the login page
	public Login openSignIn(ExtentTest log){
		
		/*  log.info("Cancel the allert message");
		  k.click(prop.getProperty("alert"));		*/							//click cancel on the allert
		  
		 log.info("click to open the login tab");
		  k.click(prop.getProperty("SignIn"));									//click sign In to navigate to login page
	
		  return new Login(driver);
	}
	
	
}
