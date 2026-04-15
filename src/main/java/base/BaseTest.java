package base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import Utils.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	
	protected WebDriver driver;
	private String browser;
	
	@BeforeMethod
	@Parameters("browser")
	public void initDriver(@Optional("") String browser) {
		
		browser=(browser!=null || !browser.isEmpty()) ? browser : ConfigReader.getBrowser();
		
		this.browser=browser; 
		
		
		switch(browser.toLowerCase()) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
			break;
			
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
			break;
			
		case "edge":
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();
			break;
		
		case "safari":
			WebDriverManager.safaridriver().setup();
			driver=new SafariDriver();
			break;
			
		default:
			throw new RuntimeException("Browser not supported: "+browser);
	
		}
		
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		
		driver.get(ConfigReader.getURL());
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(ConfigReader.getImplicitWait()));

	}
	
	
	//getter method for browser name
	public String getBrowser() {
		return browser;
	}
	
	//getter method for WebDriver
	public WebDriver getDriver() {
		return driver;
	}
	
	@AfterMethod
	public void tearDown() {
		if(driver!=null) {
			driver.quit();
		}
	}

}
