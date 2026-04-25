package base;

import java.lang.reflect.Method;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import Utils.ConfigReader;
import Utils.ExtentManager;
import io.github.bonigarcia.wdm.WebDriverManager;
public class BaseTest {
	
	protected static ThreadLocal<WebDriver> driver =new ThreadLocal<>();
	private String browser;
	
	protected static ExtentReports extent;
    protected static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    
    
    @BeforeSuite
    public void setUpReport() {
        extent = ExtentManager.getInstance();
    }
	
	@BeforeMethod
	@Parameters("browser")
	public void initDriver(@Optional("") String browser, Method method) {
		
		ExtentTest extentTest = extent.createTest(method.getName());
        test.set(extentTest);
		
		browser=(browser!=null && !browser.isEmpty()) ? browser : ConfigReader.getBrowser();
		
		this.browser=browser; 
		
		
		switch(browser.toLowerCase()) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver.set(new ChromeDriver());
			break;
			
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver.set(new FirefoxDriver());
			break;
			
		case "edge":
			WebDriverManager.edgedriver().setup();
			driver.set(new EdgeDriver());
			break;
		
		case "safari":
			WebDriverManager.safaridriver().setup();
			driver.set(new SafariDriver());
			break;
			
		default:
			throw new RuntimeException("Browser not supported: "+browser);
	
		}
		
		driver.get().manage().deleteAllCookies();
		driver.get().manage().window().maximize();
		
		driver.get().get(ConfigReader.getURL());
		
		driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(ConfigReader.getImplicitWait()));

	}
	
	
	//getter method for browser name
	public String getBrowser() {
		return browser;
	}
	
	//getter method for WebDriver
	public static WebDriver getDriver() {
		return driver.get();
	}
	
	@AfterMethod
    public void tearDown(ITestResult result) {
        // 3. Log results to the report based on TestNG status
        if (result.getStatus() == ITestResult.FAILURE) {
            test.get().fail("Test Failed: " + result.getThrowable());
            // Optional: You can add logic here to take a screenshot and attach to report
        } else if (result.getStatus() == ITestResult.SKIP) {
            test.get().skip("Test Skipped: " + result.getThrowable());
        } else {
            test.get().pass("Test Passed");
        }

        // 4. Close the driver
        if (driver != null) {
        	getDriver().quit();
            driver.remove();
        }
        
        // Remove the thread-local instance to prevent memory leaks
        test.remove();
    }
	
	@AfterSuite
    public void tearDownReport() {
        // 5. Write everything to the HTML file
        if (extent != null) {
            extent.flush();
        }
    }
	
	public static ExtentTest getExtentTest() {
	    return test.get();
	}
}
