package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

//Build CheckoutPage.java: fillShippingInfo(), clickContinue(), getOrderSummary()


public class CheckoutPage {
	
	private WebDriver driver;
	
	public CheckoutPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);

	}
	
	@FindBy(id="first-name")
	private WebElement firstNameLoc;
	
	@FindBy(id="last-name")
	private WebElement lastNameLoc;
	
	@FindBy(id="postal-code")
	private WebElement zipCode;
	
	@FindBy(id="continue")
	private WebElement continueOption;
	
	@FindBy(xpath="//div[@class='summary_info']/div[@data-test='payment-info-label']")
	private WebElement paymentInfoheading;
	
	@FindBy(xpath="//div[@class='summary_info']/div[@data-test='payment-info-value']")
	private WebElement paymentInfo;
	
	@FindBy(xpath="//div[@class='summary_info']/div[@data-test='shipping-info-label']")
	private WebElement shippingInfoHeading;
	
	@FindBy(xpath="//div[@class='summary_info']/div[@data-test='shipping-info-value']")
	private WebElement shippingInfo;
	
	@FindBy(xpath="//div[@class='summary_info']/div[@data-test='total-info-label']")
	private WebElement TotalInfoHeading;
	
	@FindBy(xpath="//div[@class='summary_info']/div[@data-test='subtotal-label']")
	private WebElement subTotal;
	
	@FindBy(xpath="//div[@class='summary_info']/div[@data-test='tax-label']")
	private WebElement taxInfo;
	
	@FindBy(xpath="//div[@class='summary_info']/div[@data-test='total-label']")
	private WebElement totalInfo;
	
	public CheckoutPage fillShippingInfo(String firstName, String LastName, String zipcode) {
		firstNameLoc.sendKeys(firstName);
		lastNameLoc.sendKeys(LastName);
		zipCode.sendKeys(zipcode);
		return this;
	}
	
	public void clickContinue() {
		continueOption.click();
	}
	
	public String getOrderSummary() {		
		return new StringBuilder()
	            .append("Payment: ").append(paymentInfo.getText()).append("\n")
	            .append("Shipping: ").append(shippingInfo.getText()).append("\n")
	            .append(subTotal.getText()).append("\n") 
	            .append(taxInfo.getText()).append("\n")
	            .append(totalInfo.getText())
	            .toString();	
	}
	
	
	

}
