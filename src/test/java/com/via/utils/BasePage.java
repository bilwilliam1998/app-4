package com.via.utils;

import java.util.Properties;

import org.openqa.selenium.WebDriver;

import com.via.loginRegister.SignIn;

public class BasePage {
	public WebDriver driver;
	public Properties prop;
	public KeyWords k;


	public BasePage(WebDriver driver) {
		this.driver = driver;
		prop = PropertyReader.getInstance();
		k=new KeyWords(driver);
		
	}
}
