package com.via.loginRegister;

import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.ExtentTest;
import com.via.utils.BasePage;

public class SignUp extends BasePage {
	
	Scanner sc= new Scanner(System.in);
	
	public SignUp(WebDriver driver) {
		super(driver);
	}
	
	public void createAccount(String mail, String password ,String number,String n, String name, ExtentTest log) {
			//this function enter the valid details in the registration field and click on create new account
		
		log.info("typing the mail id");
		k.type(prop.getProperty("signUpMail"), mail);						//enter the mail id
		
		log.info("typing the passwrd");
		k.type(prop.getProperty("signUpPassword"), password);				//enter the password
		
		log.info("typing the name");
		k.type(prop.getProperty("signUpName"), name);						// enter the name
		
		
		log.info("selecting the prefix from dropdown");
		Select s= new Select(driver.findElement(By.xpath(prop.getProperty("signUpPrefix"))));		//select the prefix of the phone number 
		s.selectByVisibleText(n);													
		
		log.info("enter the number");
		k.type(prop.getProperty("signUpnumber"), number);					//enter the phone no.

		log.info("click on create new account");
		k.click(prop.getProperty("signUpButtom"));							//click on create new account
	
		try{
			if(k.getElement(prop.getProperty("ERROR")).isDisplayed()){				//check for error
			System.out.println(k.getElement(prop.getProperty("ERROR")).getText());}
		 	} catch (Exception e) {}	
		
		
	}
	
	
	public Login tryLoginPage(ExtentTest log){
		
		log.info("clicking on try login once again");
		k.click(prop.getProperty("SignIn"));				//click on try login button navigate to login page
		
		return new Login(driver);							//Returns the objeect of login pge
	}
	
	

}
