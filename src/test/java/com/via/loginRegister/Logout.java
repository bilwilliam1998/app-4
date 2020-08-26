package com.via.loginRegister;




import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;
import com.via.utils.BasePage;


public class Logout extends BasePage{
	
	public Logout(WebDriver driver) {
		super(driver);
	}
	public void logoutFunction(ExtentTest log){
			// this function logout if the login is successful else throws no such element found exception
			//driver.navigate().refresh();
			
		/*	log.info("Click to cancel the alert message");
			k.click(prop.getProperty("alert"));		*/							// click to cancel the alert message
			
			log.info("Click to open the welccome tab");
			k.click(prop.getProperty("Welcome"));								//click to welcome tab 
			
			log.info("click to logout");
			k.click(prop.getProperty("logout1"));								// click on logout 
	}
	
	
	public void facebookLogout(ExtentTest log){											//to logout from facebook
		// this functions logouts from facebook
		
			log.info("Open https://www.facebook.com");
			k.getURL(prop.getProperty("facebookUrl"));						//open http://www.facebook.com
			
			log.info("click the open more options tab");
			k.click(prop.getProperty("DropDownFb"));						// click to open the more options tab
			
			log.info("Click the logout button");
			k.click(prop.getProperty("Logout"));							//click on logout
	
	}
	
	
	
	public void googleLogout(ExtentTest log){
		// this function logouts from google
		
		log.info("open http://account.google.coom");
		k.getURL(prop.getProperty("googleAccountUrl"));
		
		//new WebDriverWait(driver, Integer.parseInt(prop.getProperty("explicit.wait"))).until(ExpectedConditions.visibilityOf(k.getElement(prop.getProperty("moreOptions"))));
		log.info("click to open more option");
		System.out.println(prop.getProperty("moreOptionsGoogle"));
		k.click(prop.getProperty("moreOptionsGoogle"));
		
		//System.out.println("clicked");
		log.info("click to logout");
		System.out.println(prop.getProperty("signOut"));
		k.click(prop.getProperty("signOutGoogle"));
		//System.out.println("logout");
	}
	
	
	public void tabClose(String parentWindow)throws Exception{
				try{
				Set<String> windows=driver.getWindowHandles();
				Iterator<String> it=windows.iterator();
				while(it.hasNext()){
					//System.out.println("inside while"+" "+it.toString());
					if(!(it.next().equals(parentWindow))){
						//System.out.println("inside if"+" "+it.toString());
						driver.switchTo().window(it.next());
						//System.out.println("closed");
						driver.close();
						windows.remove(it.toString());
					}
				}}catch(Exception e){
					
				}
		
			/*try{
				Thread.sleep(6000);
				Object window[]= driver.getWindowHandles().toArray();
				driver.switchTo().window(window[(window.length-1)].toString()); 
				driver.close();
				return;
	  		}catch(Exception e){
	  			driver.switchTo().window(parentWindow);
	  			return;
	  		}*/
	}
}


