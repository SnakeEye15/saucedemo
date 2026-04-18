package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

//Create InventoryPage.java. Add locators and methods: getProductNames(), addProductToCart(String name), 
//getSortDropdown(), selectSortOption(String option)


public class InventoryPage {
	
	private WebDriver driver;
	
	@FindBy(xpath="//div[@class='inventory_item_name ']")
	private List<WebElement> allInventoryItems;
	
	@FindBy(xpath="//select[@class='product_sort_container']")
	private WebElement dropDownOption;
	
	
	public InventoryPage(WebDriver driver){
		this.driver=driver;
		
		PageFactory.initElements(driver, this);
	}
	
	//method to return all product available
	public List<String> getProductNames(){
		List<String> product=new ArrayList<>();
		
		for(WebElement element:allInventoryItems) {
			product.add(element.getText());
		}
		return product;	
	}
	
	//Method to add product to cart
	public void addProductToCart(String name) {
		String path="//div[text()='" + name + "']/ancestor::div[@class='inventory_item_description']//button";
		driver.findElement(By.xpath(path)).click();
	}
	
	//Method to get the DropDown Option
	public WebElement getSortDropdown() {
		return dropDownOption;
	}
	
	
	//Method to select the dropDown option via visibility of text
	public void selectSortOption(String option) {
		Select sel=new Select(dropDownOption);
		sel.selectByVisibleText(option);
	}
	
	

}
