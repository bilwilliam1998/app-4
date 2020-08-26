package com.via.loginRegister;



import java.util.Scanner;


import org.openqa.selenium.WebDriver;


import com.aventstack.extentreports.ExtentTest;
import com.via.utils.BasePage;


public class ResetPasswordEnterOTPSubmit extends BasePage {
	
	Scanner sc= new Scanner(System.in);
	public ResetPasswordEnterOTPSubmit(WebDriver driver) {
		super(driver);
	}
	
	public void enterOtpPassword(String password, ExtentTest log) throws Exception{
			//this function enter the otp and new password and click on reset password
		
		//String message = null;
		
		/*log.info("Getting input from the console");						
		System.out.println("Enter the OTP");							
		String otp= sc.next();														//enter the otp manually
	
		Thread.sleep(6000);
	*/	
		//String otp=getOTP(log);
		
		log.info("Getting input from the console");	
		System.out.println("Enter the OTP");
		
		String otp=sc.next();
		
		log.info("Typing the OTP");													
		k.type(prop.getProperty("enterOtp"), otp);									//enter the otp in the field
		
		log.info("Typing the new Password");
		k.type(prop.getProperty("newPassword"), password);							//enter the new password
	
		log.info("Click the reset password button");
		k.click(prop.getProperty("resetPasswordBtn"));								//click on reset password	
		}
		
	
	/*public String getOTP(ExtentTest log) throws InterruptedException{
		
		String otp= null;
		int i=0;
		Thread.sleep(3000);
		do{
			log.info("Getting input from the console");	
			System.out.println("Enter the OTP");	
			otp=sc.next();
			sc.w
		 while(!sc.hasNext()){
			 System.out.println("Resend OTP Y/N?");
				String resendOtp=sc.next();
				if(resendOtp.equalsIgnoreCase("y")){
					k.click(prop.getProperty("resendOtp"));
					System.out.println("Enter the OTP");							
					otp= sc.next();	}
				else{
					System.out.println("no otp sent");
				}
		 	} i++;	} while(i==5);
		
		return otp;
	}*/
	
	
	public Login tryLogin(ExtentTest log){
		
		log.info("clicking on try login once again");
		k.click(prop.getProperty("tryLoginAgain"));							//click on try login button navigate to login page
	
		return new Login(driver);											//Returns the objeect of login pge
	}	
	
	
}
