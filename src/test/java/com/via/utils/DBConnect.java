package com.via.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.via.utils.Base;

public class DBConnect extends Base {
	@Test
	public void getConnection() throws Exception {
		Connection con = DriverManager.getConnection
				("jdbc:oracle:thin:@localhost:1521:orcl","system","Teja1999");
		ResultSet rs = con.createStatement().executeQuery("select * from login");
		PreparedStatement P=con.prepareStatement("UPDATE login SET STATUS=? WHERE UN=? ");
		while (rs.next() == true) {
			String user = rs.getString("UN");
			String pass = rs.getString("PWD");
			P.setString(2, user);
			System.out.println(user + "\t" + pass);
			driver.get("http://demo.frontaccounting.eu/");
			driver.findElement(By.name("user_name_entry_field")).clear();
			driver.findElement(By.name("password")).clear();
			driver.findElement(By.name("user_name_entry_field")).sendKeys(user);
			driver.findElement(By.name("password")).sendKeys(pass);
			driver.findElement(By.name("SubmitUser")).click();
			try {
				driver.findElement(By.linkText("Logout")).click();
				System.out.println("Login Pass");
				P.setString(1, "PASS");

			} catch (Exception E) {
				System.out.println("Login Failed");
				P.setString(1, "FAIL");
			}
			P.executeUpdate();
		}
	}
	
	
}
