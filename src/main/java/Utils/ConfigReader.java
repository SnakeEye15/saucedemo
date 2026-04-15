package Utils;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
	
	private static Properties properties;
	
	// Static block executes automatically when class is first loaded by JVM
		// Used to initialize framework configuration at startup
	static {
		try {
			properties=new Properties();
			// ClassLoader loads file from classpath instead of local filesystem
			InputStream stream=ConfigReader.class.getClassLoader().getResourceAsStream("config.properties");
			
			//if stream is already null than it should throw error and fail first only
			if(stream==null) {
				throw new RuntimeException("config.properties file not found");
			}
			
			
			//To load all data
			properties.load(stream);
			
		}catch(Exception e) {
			throw new RuntimeException("Failed to load config.properties", e);
		}
	}
	
	//Getter method for browser 
	public static String getBrowser() {
		
		String browser=properties.getProperty("browser");
		
		if(browser==null|| browser.isEmpty()) {
			throw new RuntimeException("Browser is not mentioned in config file");
		}
		return browser;
	}
	
	
	//Getter method for URL
	public static String getURL() {
		String url=properties.getProperty("baseUrl");
		
		if(url==null||url.isEmpty()) {
			throw new RuntimeException("URL is not defined in config.properties file");
		}
		return url;
	}

	
	//Getter method for implicit wait
	public static int getImplicitWait() {
		return Integer.parseInt(properties.getProperty("implicitWait"));
		
	}
	
	//Getter method for Explicit wait
		public static int getExplicitWait() {
			return Integer.parseInt(properties.getProperty("explicitWait"));
			
		}
	
}
