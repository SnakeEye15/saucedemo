package Utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.*;

import base.BaseTest;

public class Listeners  implements ITestListener{

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		Object TestClass=result.getInstance();
		
		WebDriver driver= BaseTest.getDriver();
		
		String screenshotBase64 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
		
		// Attach to Extent Report using the static getter from BaseTest
        BaseTest.getExtentTest().fail("Test Failed: " + result.getThrowable(), 
           MediaEntityBuilder.createScreenCaptureFromBase64String(screenshotBase64).build());
	}
	
	

}
