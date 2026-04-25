package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;

//Write LoginTest.java. Test valid login, invalid login, empty fields. 
//Use @DataProvider annotation to pass multiple credential sets. Assert landing page title after valid login
public class LoginTest extends BaseTest{
	
	@Test(dataProvider="loginData")
	public void validateLogin(String user, String pass, String type) {
		LoginPage login=new LoginPage(getDriver());
		login.loginUser(user, pass);
		
		if(type.equals("valid")) {
	
		
		Assert.assertEquals(login.getDashboardText(), "Swag Labs");}
		else {
			Assert.assertTrue(login.isErrorDisplayed(), "Error message should be visible for " + type + " login.");
		}
	}
	
	@DataProvider(name = "loginData")
	public Object[][] getData() {
	    return new Object[][] {
	        { "standard_user", "secret_sauce", "valid" },   // Valid
	        { "invalid_user", "secret_sauce", "invalid" }, // Invalid User
	        { "standard_user", "wrong_pass", "invalid" },   // Invalid Pass
	        { "", "", "empty" }                            // Empty fields
	    };
	}
	

}
