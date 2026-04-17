//Build LoginPage.java using POM pattern. All locators as private @FindBy WebElements. 
//All actions as public methods (enterUsername, enterPassword, clickLogin). NO findElement calls in test classes

package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	private WebDriver driver;
	
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
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	//public method for User name
	public void enterUsername(String username) {
		usernameField.sendKeys(username);
	}
	
	//public method for password
	public void enterPassword(String password) {
		passwordFiled.sendKeys(password);
	}

	//public method to click on login option
	public void clickLoginButton() {
		loginButton.click();
	}
	
	//Complete End to End method for login functionality
	public void loginUser(String user,String pass) {
		enterUsername(user);
		enterPassword(pass);
		clickLoginButton();
	}
	
	//get the dash board text
	public String getDashboardText() {
		return dashboardTitle.getText();
	}
	
	//Is error message displayed
	public boolean isErrorDisplayed() {
		return errorMessage.isDisplayed();
	}
}



