package com.via.loginRegister;



import java.util.Scanner;


import org.openqa.selenium.WebDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.via.utils.BasePage;

public class Login extends BasePage  {

	String message;
	
	public Login(WebDriver driver) {
		super(driver);
	}
	
	
	
	public void loginEmailIdPassword(String username, String password,ExtentTest log){
			// this function enters the required field for customer login and check for error message if any
		
			log.info("Typing email ID in the enter email Id field");
			k.type(prop.getProperty("EmailID"), username);							// enter the email
			
			log.info("Typing password in the enter password field");
			k.type(prop.getProperty("Password"), password);							//enter the password
			
			log.info("Click on login buttom");
			k.click(prop.getProperty("Login"));										//click on login button
										
		}
	
	
	
	public void loginFacebook(String username, String password, ExtentTest log) throws Exception{
			//  this function selects customer login via facebook and enters the field details and login 
	
		
		log.info("Click to sign in via facebook");
		k.click(prop.getProperty("facebookSignUp"));										//click on login via facebook
		
		log.info("navigate to the facebook tab");
		driver.switchTo().window(driver.getWindowHandles().toArray()[1].toString());		// switch the driver to facebook login tab
		
		try{
		if(k.getElement(prop.getProperty("EmailIDFb")).isDisplayed()){ 						//check if the login page has enter email field
			facebookLogin(username, password, log);											// to login in facebook
			return;	}																		// return to testng
		}catch(Exception e){
		}
		
		try{
		if(k.getElement(prop.getProperty("exception1")).isDisplayed()){						//check if the login page has suggesting messages
			k.click(prop.getProperty("exception1"));										//click on the suggesting to proceed to login tab
			facebookLogin(username, password,log);											//to login in facebook
			return;}
		}catch(Exception e){
		}
		
		try{
			if(k.getElement(prop.getProperty("exception3")).isDisplayed()){					//check if the login page has suggesting messages
				k.click(prop.getProperty("exception3"));										//click on the suggesting to proceed to login tab
				Object[] window =driver.getWindowHandles().toArray();
				driver.switchTo().window(window[(window.length)-1].toString());
				facebookLogin(username, password,log);												//to login in facebook
				return;}
		}catch(Exception e){		
		}
		
		
		try{
			if(k.getElement(prop.getProperty("exception2")).isDisplayed()){						//check if the login page has some suggestion message
				k.click(prop.getProperty("exception2"));										// click on the suggestion to enter userid and pssword
				Object window[]= driver.getWindowHandles().toArray();
				driver.switchTo().window(window[(window.length-1)].toString()); 
				facebookLogin(username, password,log);											//to login in the facebook
				return;}
		}catch(Exception e){
		}
	
}
	public void facebookLogin(String username, String password, ExtentTest log) throws Exception{
			
		Scanner sc=new Scanner(System.in);		
		String otp;
		
		log.info("Clear the username field");
		k.getElement(prop.getProperty("EmailIDFb")).clear();				// clears the username tab
		
		log.info("Typing the username");
		k.type(prop.getProperty("EmailIDFb"), username );					//enter the username in the facebook login tab
		
		log.info("clear the password field");
		k.getElement(prop.getProperty("PasswordFb")).clear();				//clears the password fields
		
		log.info("Typing the password");
		k.type(prop.getProperty("PasswordFb"), password);					//enters the password in the password in facebook login tab
		
		log.info("Click the login button");
		
			try{
			k.click(prop.getProperty("LoginFb"));							//check for the click button and click on the login
			}catch(Exception e){
			k.click(prop.getProperty("loginFb1"));							//check for the click button and click on the login button
			}
			try{
			int time=Integer.parseInt(prop.getProperty("explicit.wait"));
			k.click(prop.getProperty("continueButtom"));
			k.click(prop.getProperty("chooseOtpOption"));
			k.click(prop.getProperty("continueButtom"));
			new WebDriverWait(driver, time).until(ExpectedConditions.visibilityOf(k.getElement(prop.getProperty("otpSent"))));
			k.click(prop.getProperty("continueButtom"));
			System.out.println("enter the otp");
			otp=sc.next();
			k.type(prop.getProperty("enterOtpFb"), otp);
			Thread.sleep(Integer.parseInt(prop.getProperty("thread.sleep")));
			k.click(prop.getProperty("continueButtom"));
			Thread.sleep(Integer.parseInt(prop.getProperty("thread.sleep")));			
			k.click(prop.getProperty("continueButtom"));
			}catch(Exception e){//System.out.println(e.getMessage());
			return;}	
	}

	public void googleLogin(String username, String password, ExtentTest log){
			// this function logins via google account
		
			
			log.info("click on login via google ");
			k.click(prop.getProperty("loginViaGoogle"));
			
			log.info("navigate to the google tab");
			Object window[]= driver.getWindowHandles().toArray();
			driver.switchTo().window(window[(window.length)-1].toString());		// switch the driver to google login tab
			try{
			log.info("click on signin Via another account");
			k.click(prop.getProperty("anotherAccount"));
			}catch(Exception e){}
			try{
			log.info("typing mail id");
			k.type(prop.getProperty("emailIdGoogle"), username);
			
			log.info("click on next button");
			k.click(prop.getProperty("nextButtom"));
			}catch(Exception e){}
			
			try{
			int time= Integer.parseInt(prop.getProperty("explicit.wait"));
			new WebDriverWait(driver, time).until(ExpectedConditions.visibilityOf(k.getElement(prop.getProperty("forgotPassword1"))));
			
			log.info("typing password");
			k.type(prop.getProperty("passwordGoogle"), password);
			//new WebDriverWait(driver, time).until(ExpectedConditions.visibilityOf(k.getElement(prop.getProperty("forgotPassword1"))));
			Thread.sleep(Integer.parseInt(prop.getProperty("thread.sleep")));
			log.info("click on next button");
			k.click(prop.getProperty("nextButton"));
			Thread.sleep(Integer.parseInt(prop.getProperty("thread.sleep")));
			return;
			}
			catch(Exception e){
				//System.out.println(e.getMessage());
			}

	}
	
	public ResetPasswordEnterMail forgotPassword(ExtentTest log){
		//this function navigates to the forgot password tab
		
		
		
		log.info("click to open the login tab");
																//to click open the log In tab										
		
		log.info("click to open the forgot password tab");
		k.click(prop.getProperty("forgotPassword"));							//to open the forgot password tab
		
		return new ResetPasswordEnterMail(driver);								// return the instance of forgotpassword POM
		
	}
	
	public SignUp createNewAccount(ExtentTest log){
		//this function navigates to the SignUp tab
		
	
		
		log.info("click to open the login tab");
															//to click open the login Tab
		
		log.info("click to open the forgot password tab");
		k.click(prop.getProperty("signUpBotton"));								//to click open the create new account? SignUp tab
		
		
		return new SignUp(driver);												//returns the instance of SignUp POM
	
	}
 

}
