//Build LoginPage.java using POM pattern. All locators as private @FindBy WebElements. 
//All actions as public methods (enterUsername, enterPassword, clickLogin). NO findElement calls in test classes

package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class LoginPage extends BasePage {
	
	@FindBy(id="user-name")
	private WebElement usernameField;
	
	@FindBy(id="password")
	private WebElement passwordFiled;
	
	@FindBy(id="login-button")
	private WebElement loginButton;
	
	@FindBy(xpath="//div[text()=\\\"Swag Labs\\\"]")
	private WebElement dashboardTitle;
	
	@FindBy(xpath="//h3[@data-test='error']")
	private WebElement errorMessage;
	
	//Constructor to initialize the elements
	public LoginPage() {
		super();
		
	}
	
	//public method for User name
	public void enterUsername(String username) {
		waitOn().waitForElementClickable(usernameField).sendKeys(username);
	}
	
	//public method for password
	public void enterPassword(String password) {
		waitOn().waitForElementClickable(passwordFiled).sendKeys(password);
	}

	//public method to click on login option
	public void clickLoginButton() {
		waitOn().waitForElementClickable(loginButton).click();
	}
	
	//Complete End to End method for login functionality
	public InventoryPage loginUser(String user,String pass) {
		enterUsername(user);
		enterPassword(pass);
		clickLoginButton();
		return new InventoryPage();
	}
	
	//get the dash board text
	public String getDashboardText() {
		return waitOn().waitForElementVisible(dashboardTitle).getText();
	}
	
	//Is error message displayed
	public boolean isErrorDisplayed() {
		return waitOn().waitForElementVisible(errorMessage).isDisplayed();
	}
}



