package tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import Utils.ConfigReader;
import base.BaseTest;
import pages.CartPage;
import pages.CheckoutPage;
import pages.InventoryPage;
import pages.LoginPage;

//Write complete checkout end-to-end test: 
//login → add 2 products → go to cart → verify items → checkout → fill info → verify order confirmation page. 
//This is your flagship test
public class EndToEndTest2 extends BaseTest{
	
	@Test(description="Validate complete flow of application till order confirmation page.")
	public void completeTest() {

		//step1: Login to application
		LoginPage login = new LoginPage();
		InventoryPage inventory=login.loginUser(ConfigReader.getUsername(),ConfigReader.getPassword());
		
		//Step2: getting products and adding to cart
		List<String> products=inventory.getProductNames();
		inventory.addProductToCart(products.get(1));
		inventory.addProductToCart(products.get(2));
		//verify cart values
		Assert.assertEquals(inventory.getCartCount(), "2");
		
		//Step3: Go to cart
		CartPage cart=inventory.clickOnCart();
		List<String> items=cart.getCartItems();
		
		//verify items
		Assert.assertEquals(items.size(), 2, "Cart should contain 2 items");
        Assert.assertTrue(items.contains(products.get(0)), "First product missing from cart");
        
        //step4: Move to checkout page
        CheckoutPage checkout=cart.clickOnCheckOut();
        
        //step5:  fill checkoutInfo
        checkout.fillShippingInfo(ConfigReader.getFirstName(), ConfigReader.getLastName(), ConfigReader.getZipCode()).clickContinue();
        
     // 6. Final Verification - Verify the Order Summary details
        String orderSummary = checkout.getOrderSummary();
        Assert.assertTrue(orderSummary.contains("Total"), "Order summary should display the total price");
        
        
		
		
	}

}
