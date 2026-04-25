package Utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
	
	private static ExtentReports extent;
	
	public static ExtentReports getInstance() {
        if (extent == null) {
            createInstance();
        }
        return extent;
    }
	
	private static void createInstance() {
		
		ExtentSparkReporter sparkReporter= new ExtentSparkReporter("reports/reports.html");
		
		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setDocumentTitle("Automation Report");
		sparkReporter.config().setReportName("Test Execution results");
		
		extent=new ExtentReports();
		extent.attachReporter(sparkReporter);
		
		
		
	}
	

}
