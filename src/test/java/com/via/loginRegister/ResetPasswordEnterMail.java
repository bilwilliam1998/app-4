package com.via.loginRegister;




import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;
import com.via.utils.BasePage;


public class ResetPasswordEnterMail extends BasePage {

	public ResetPasswordEnterMail(WebDriver driver) {
		super(driver);
	}
	
	public ResetPasswordEnterOTPSubmit forgotPassword(String email, ExtentTest log){
				//this function enters the mail id/ number and click on send otp & navigate to enter OTP page
		
		log.info("Typing the mail id");
		k.type(prop.getProperty("registeredMailId"),email);				//enters the valis mail/number
		
		log.info("Click on send OTP");
		k.click(prop.getProperty("sendOTP"));							// click on send otp
	
		return new ResetPasswordEnterOTPSubmit(driver);
		
	}

	public Login tryLogin(ExtentTest log){
		
			log.info("clicking on try login once again");
			k.click(prop.getProperty("tryLoginAgain"));				//click on try login button navigate to login page
		
			return new Login(driver);							//Returns the objeect of login pge
	}	

}
