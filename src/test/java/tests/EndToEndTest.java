
package tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import Utils.ConfigReader;
import base.BaseTest;
import pages.InventoryPage;
import pages.LoginPage;

//Write end-to-end test: login → add product to cart → verify cart badge count. 
//Push full project to GitHub. Write README with: project description, tech stack, how to run

public class EndToEndTest extends BaseTest{
	
	@Test
	public void endToEndTest() {
		//To login into application 
		try {
		LoginPage login=new LoginPage(getDriver());
		login.loginUser(ConfigReader.getUsername(), ConfigReader.getPassword());
		
		
	}catch(Exception e) {
		throw new RuntimeException("Login Failed... due to "+e.getMessage());
	}
		
		InventoryPage inventory = new InventoryPage(getDriver());
		List<String> products=inventory.getProductNames();
		
		inventory.addProductToCart(products.get(1));
		inventory.addProductToCart(products.get(2));
		
		Assert.assertEquals(inventory.getCartCount(), "2");
		
	}
}
