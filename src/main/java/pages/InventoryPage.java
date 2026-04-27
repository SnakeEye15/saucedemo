package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import base.BaseTest;

//Create InventoryPage.java. Add locators and methods: getProductNames(), addProductToCart(String name), 
//getSortDropdown(), selectSortOption(String option)


public class InventoryPage extends BasePage{
	
	@FindBy(xpath="//div[@class='inventory_item_name ']")
	private List<WebElement> allInventoryItems;
	
	@FindBy(xpath="//select[@class='product_sort_container']")
	private WebElement dropDownOption;
	
	@FindBy(xpath="//span[@class=\"shopping_cart_badge\"]")
	private WebElement shoppingCartBadge;
	
	@FindBy(xpath="//a[@class='shopping_cart_link']")
	private WebElement shoppingcartOption;
	
	
	public InventoryPage(){
		super();
	}
	
	//method to return all product available
	public List<String> getProductNames(){
		List<String> product=new ArrayList<>();
		
		
		for(WebElement element:allInventoryItems) {
			waitOn().waitForElementVisible(element);
			product.add(element.getText());
		}
		return product;	
	}
	
	//Method to add product to cart
	public InventoryPage addProductToCart(String name) {
		// Always get driver from BaseTest to ensure thread-safety
		String xpath = String.format("//div[text()='%s']/ancestor::div[@class='inventory_item_description']//button", name);
		WebElement btn = BaseTest.getDriver().findElement(By.xpath(xpath));
		waitOn().waitForElementClickable(btn).click();
		return this; 
	}
	
	//Method to get the DropDown Option
	public WebElement getSortDropdown() {
		waitOn().waitForElementVisible(dropDownOption);
		return dropDownOption;
	}
	
	
	//Method to select the dropDown option via visibility of text
	public void selectSortOption(String option) {
		Select sel=new Select(dropDownOption);
		sel.selectByVisibleText(option);
	}
	
	
	//Method to get the Shopping cart count
	public String getCartCount() {
		waitOn().waitForElementVisible(shoppingCartBadge);
		return shoppingCartBadge.getText();
	}
	
	//Method to click on shopping cart 
	public CartPage clickOnCart() {
		waitOn().waitForElementClickable(shoppingcartOption);
		shoppingcartOption.click();
		return new CartPage(); // Flow: Inventory -> Cart
	}
	

}
