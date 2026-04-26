package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

//Build CartPage.java with locators and methods: getCartItems(), removeItemFromCart(), getCartTotal(). 

public class CartPage {
	
	private WebDriver driver;
	
	public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
	
	@FindBy(xpath="//a[@data-test='shopping-cart-link']")
	private WebElement cartLocator;
	
	@FindBy(xpath="//div[@data-test='inventory-item-name']")
	private List<WebElement> cartItmensLoc;
	
	@FindBy(xpath="//div[@class='inventory_item_price']")
	private List<WebElement> cartPriceLoc;
	
	@FindBy(id="checkout")
	private WebElement checkoutOption;
	
	
	public List<String> getCartItems(){
		List<String> items=new ArrayList<>();
		
		for(WebElement item:cartItmensLoc) {
			items.add(item.getText());
		}
		
		return items;
	}
	
	public void removeItemFromCart(String name) {
		String path = "//div[@class='inventory_item_name' and text()='" + name + "']/ancestor::div[@class='cart_item_label']//button";
		driver.findElement(By.xpath(path)).click();
	}
	
	public double getCartTotal() {
		double cartTotal=0;
		List<Double> cartprice=new ArrayList<>();
		for(WebElement value:cartPriceLoc) {
			String priceText = value.getText().replace("$", "");
            cartTotal += Double.parseDouble(priceText);
		}
		return cartTotal;
	}
	
	public void clickOnCheckOut() {
		checkoutOption.click();
	}

}
