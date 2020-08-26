package com.via.testRunner;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.via.loginRegister.Login;
import com.via.loginRegister.Logout;
import com.via.loginRegister.ResetPasswordEnterMail;
import com.via.loginRegister.ResetPasswordEnterOTPSubmit;
import com.via.loginRegister.SignIn;
import com.via.loginRegister.SignUp;
import com.via.utils.Base;
import com.via.utils.ExcelReader;
import com.via.utils.KeyWords;

import java.util.Base64;
import java.util.Scanner;


import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
@Listeners()
public class LoginRegister extends Base{
	
	 
	 SignIn si;
	 Login  l;
	 KeyWords k ;
	 ResetPasswordEnterMail forgotPassword;
	 Logout out;
	 ResetPasswordEnterOTPSubmit otpPassword;
	 ResetPasswordEnterOTPSubmit resetPsd=null;
	 Scanner sc= new Scanner(System.in);
	 SignUp newAcc;

	 @BeforeClass
	 public void beforeMethod(){
			extentReporter = new ExtentReports();
			extentReporter.attachReporter(new ExtentHtmlReporter(".\\src\\test\\resources\\output\\"+prop.getProperty("testName1")+".html"));
	 }
	
	@Test(priority=1,dataProvider = "emailIdPassword", 
	    description="This Test method checks the customer login functionality using valid and invalid fields with data provider")

public void loginUsPsw(String username, String password,String result) {
	  
	  		driver.get(prop.getProperty("url"));
	  	
	  		si=new SignIn(driver);
	  		out= new Logout(driver);
	  		k =new KeyWords(driver); 
		
	  		l=si.openSignIn(testLog);
	  		l.loginEmailIdPassword(username, password, testLog);
	  		
	  		try{
	  		if(result.equals("pass")){
	  			out.logoutFunction(testLog);
	  		}
	  		else if(result.equals("fail")){
	  			System.out.println("invalid data");
	  		}
	  		}catch(Exception e){
	  			out.logoutFunction(testLog);
	  			throw new AssertionError("Test fails expected pass");
	  		}
	  }
 
  
 @Test(priority=2, dataProvider = "usernamePassword",
		description="This test method checks the customer login using Social media like facebook  with valid and invalid data using data provider")
  	public void loginViaFacebook(String username, String password,String result) throws Exception {
	  		
	  		driver.get(prop.getProperty("url"));
	 
	  		si=new SignIn(driver);
	  		out= new Logout(driver);
	  		k =new KeyWords(driver); 
	  		
	  		String parentWindow= driver.getWindowHandle();
	  		
	  		l=si.openSignIn(testLog);
	  		System.out.println(username+" "+password);
	  		l.loginFacebook(username, password,testLog);
	  		try{
	  			int time=Integer.parseInt(prop.getProperty("explicit.wait"));
	  		new WebDriverWait(driver,time).until(ExpectedConditions.numberOfWindowsToBe(1));
	  		}catch(Exception e){
	  		out.tabClose(parentWindow);
	  		}

	  		driver.switchTo().window(parentWindow);
	  		
	  		try{
	  			
		  		if(result.equals("pass")){
		  			driver.navigate().refresh();
		  			out.logoutFunction(testLog);
		  			out.facebookLogout(testLog);;
		  		}
		  		else if(result.equals("fail")){
		  			System.out.println("invalid data");
		  		}
		  		}catch(Exception e){
		  			out.logoutFunction(testLog);
		  			throw new AssertionError("Test fails expected pass");
		  		}
	  	
  	}
  
  @Test(priority=3, dataProvider = "googleUsernamePassword",
			description="This test method checks the customer login using Social media  google with valid and invalid data using data provider")
	  	public void loginViaGoogle(String username, String password, String result) throws Exception {
		  		
		  		driver.get(prop.getProperty("url"));
		 
		  		si=new SignIn(driver);
		  		out= new Logout(driver);
		  		k =new KeyWords(driver); 
		  		
		  		String parentWindow= driver.getWindowHandle();
		  		
		  		l=si.openSignIn(testLog);
		  		l.googleLogin(username, password, testLog);
		  		
		  	
		  		try{
		  			int time=Integer.parseInt(prop.getProperty("explicit.wait"));
			  		new WebDriverWait(driver, time).until(ExpectedConditions.numberOfWindowsToBe(1));
			  		}catch(Exception e){
			  		out.tabClose(parentWindow);
			  		}
		  		
		  		driver.switchTo().window(parentWindow);
		  		
		  		try{
		  			
			  		if(result.equals("pass")){
			  			
			  			driver.navigate().refresh();
			  			
			  			out.logoutFunction(testLog);
			  			
			  			out.googleLogout(testLog);
			  		}
			  		else if(result.equals("fail")){
			  			System.out.println("invalid data");
			  		}
			  		}catch(Exception e){
			  			//out.logoutFunction(testLog);
			  			throw new AssertionError("Test fails expected pass");
			  		}
		  	
	  	}
  

  
  
  
 @Test(priority=4, dataProvider = "emailIdOTP",
		 description="This test method checks reset password functionality with valid mail id, OTP and passwords")
	  public void resetPassword(String mailId, String newPassword,String result) {
	 			driver.get(prop.getProperty("url"));
	 			
	 			si= new SignIn(driver);
	 			out= new Logout(driver);
	  			k =new KeyWords(driver); 
	  			
	  			
	  			l=si.openSignIn(testLog);
	  			ResetPasswordEnterMail enterMail= l.forgotPassword(testLog);
	  			resetPsd = enterMail.forgotPassword(mailId, testLog);
	  			
	  			try{
	  				int time=Integer.parseInt(prop.getProperty("explicit.wait"));
	  				new WebDriverWait(driver, time).until(ExpectedConditions.visibilityOfElementLocated(
	  											By.xpath(prop.getProperty("checkVisibility"))));
	  				 resetPsd.enterOtpPassword(newPassword,testLog);
	  			}catch(Exception e){
	  				System.out.println("invalid number/email id");
	  			}
	  			try{
	  				if(result.equals("pass")){
		  			out.logoutFunction(testLog);}
	  			}catch(Exception e){
	  				throw new AssertionError("Test fails expected pass");
	  			}
	  				try{
	  					if(result.equals("fail")){
	  					k.click(prop.getProperty("SignIn"));
	  					int time=Integer.parseInt(prop.getProperty("explicit.wait"));
		  		  		new WebDriverWait(driver, time).until(ExpectedConditions.visibilityOfElementLocated(
		  		  											By.xpath("//span[contains(text(),'Customer Login')]")));
		  		  		System.out.println("invalid data");}
	  					}catch(Exception e){
	  						out.logoutFunction(testLog);
	  					throw new AssertionError("Test pass expected fail");
	  					}
	  				
	  				try{
	  					out.logoutFunction(testLog);
	  				}catch(Exception e){
	  					
	  				}
	  				
	  				
		
	    }

  
 @Test(priority=5, dataProvider = "registrationData",
		 description="This test method checks the Sign UP new account function with valid and invalid intputs to the fields")
	  public void registerAccount(String mail, String password, String name,String n,String number,String result ){
	 		
	 				driver.get(prop.getProperty("url"));
	 			
	 			si= new SignIn(driver);
	 			out= new Logout(driver);
	  			k =new KeyWords(driver); 
	  			
	  			l=si.openSignIn(testLog);
	  			newAcc= l.createNewAccount(testLog);
	  			newAcc.createAccount(mail, password, number, n, name, testLog);
	  			try{
	  				if(result.equals("pass")){
		  			out.logoutFunction(testLog);}
	  			}catch(Exception e){
	  				throw new AssertionError("Test fails expected pass");
	  			}
	  				try{
	  					if(result.equals("fail")){
	  					k.click(prop.getProperty("SignIn"));
		  				int time=Integer.parseInt(prop.getProperty("explicit.wait"));
			  		  	new WebDriverWait(driver, time).until(ExpectedConditions.visibilityOfElementLocated(
			  		  											By.xpath("//span[contains(text(),'Customer Login')]")));
	  		  				
	  		  			System.out.println("invalid data");}
	  				}catch(Exception e){
	  						out.logoutFunction(testLog);
	  					throw new AssertionError("Test pass expected fail");
	  				}
	  				try{
	  					out.logoutFunction(testLog);
	  				}catch(Exception e){
	  					
	  				}
	  				

	    }


 
	  @DataProvider
	  public Object[][] emailIdPassword() throws Exception  {
	  
	  
		  ExcelReader ex= new ExcelReader(".\\src\\test\\resources\\Input\\InputLoginRegister.xlsx");
			 Object data[][]= new Object[ex.getRowNum("CustomerLogin")-2][ex.getColumnNum("CustomerLogin")];
			// System.out.println(ex.getRowNum("CustomerLogin")+" "+ex.getColumnNum("CustomerLogin"));
			for(int i=1;i<(ex.getRowNum("CustomerLogin")-1);i++)
			{	
					data[i-1][0]=ex.getCellData("CustomerLogin",i,0);
					byte[] actbyte= Base64.getDecoder().decode(ex.getCellData("CustomerLogin",i,1));
					String password=new String(actbyte);
					data[i-1][1]=password;
					data[i-1][2]=ex.getCellData("CustomerLogin",i,2);
			}
		  return data;
	  }
	  

	  @DataProvider
	  public Object[][] usernamePassword() throws NullPointerException{
		  
		  ExcelReader ex= new ExcelReader(".\\src\\test\\resources\\Input\\InputLoginRegister.xlsx");
			 Object data[][]= new Object[ex.getRowNum("FacebookLogin")-2][ex.getColumnNum("FacebookLogin")];
			 //System.out.println(ex.getRowNum("FacebookLogin")+" "+ex.getColumnNum("FacebookLogin"));
			for(int i=1;i<(ex.getRowNum("FacebookLogin")-1);i++)
			{
					data[i-1][0]=ex.getCellData("FacebookLogin",i,0);
					byte[] actbyte= Base64.getDecoder().decode(ex.getCellData("FacebookLogin",i,1));
					String password=new String(actbyte);
					data[i-1][1]=password;
					data[i-1][2]=ex.getCellData("FacebookLogin",i,2);
			}
		  return data;
	  }
	  
	  
	  
	  @DataProvider
	  public Object[][] googleUsernamePassword() throws NullPointerException{
		  
		  ExcelReader ex= new ExcelReader(".\\src\\test\\resources\\Input\\InputLoginRegister.xlsx");
			 Object data[][]= new Object[ex.getRowNum("GoogleLogin")-2][ex.getColumnNum("GoogleLogin")];
			 System.out.println(ex.getRowNum("GoogleLogin")+" "+ex.getColumnNum("GoogleLogin"));
			for(int i=1;i<(ex.getRowNum("GoogleLogin")-1);i++)
			{		System.out.println(i);
					data[i-1][0]=ex.getCellData("GoogleLogin",i,0);
					byte[] actbyte= Base64.getDecoder().decode(ex.getCellData("GoogleLogin",i,1));
					String password=new String(actbyte);
					data[i-1][1]=password;
					data[i-1][2]=ex.getCellData("GoogleLogin",i,2);
			}
		  return data;
	  }
	  
	  @DataProvider
	  public Object[][] emailIdOTP() throws NullPointerException{ 
		  ExcelReader ex= new ExcelReader(".\\src\\test\\resources\\Input\\InputLoginRegister.xlsx");
			 Object data[][]= new Object[ex.getRowNum("ResetPassword")-2][ex.getColumnNum("ResetPassword")];
			 //System.out.println(ex.getRowNum("ResetPassword")+" "+ex.getColumnNum("ResetPassword"));
			for(int i=1;i<(ex.getRowNum("ResetPassword")-1);i++)
			{
					data[i-1][0]=ex.getCellData("ResetPassword",i,0);
					byte[] actbyte= Base64.getDecoder().decode(ex.getCellData("ResetPassword",i,1));
					String password=new String(actbyte);
					data[i-1][1]=password;
					data[i-1][2]=ex.getCellData("ResetPassword",i,2);
			}
		  return data;
	  }
	
	  
	  @DataProvider
	  public Object[][] registrationData() throws NullPointerException{
		  
		  ExcelReader ex= new ExcelReader(".\\src\\test\\resources\\Input\\InputLoginRegister.xlsx");
			 Object data[][]= new Object[ex.getRowNum("Registration")-2][ex.getColumnNum("Registration")];
			 //System.out.println(ex.getRowNum("Registration")+" "+ex.getColumnNum("Registration"));
			for(int i=1;i<(ex.getRowNum("Registration")-1);i++)
			{
					data[i-1][0]=ex.getCellData("Registration",i,0);
					byte[] actbyte= Base64.getDecoder().decode(ex.getCellData("Registration",i,1));
					String password=new String(actbyte);
					data[i-1][1]=password;
					data[i-1][2]=ex.getCellData("Registration",i,2);
					data[i-1][3]=ex.getCellData("Registration",i,3);
					data[i-1][4]=ex.getCellData("Registration",i,4);
					data[i-1][5]=ex.getCellData("Registration",i,5);
			}
		  return data;
	  }
 
	  
}
