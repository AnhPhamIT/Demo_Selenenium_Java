package common.functions;

import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * Create WebDriver bases on data in excel file
 */

public class DriverFactory {

	
	public static WebDriver createDriver() throws Exception {
		ReadExcel dataBuilder = new ReadExcel();
		
		String remote = dataBuilder.getConfigData("Remote");
		String version = dataBuilder.getConfigData("Version");
		String seleniumHub = dataBuilder.getConfigData("Selenium Hub");
		String browser = dataBuilder.getConfigData("Browser");
		String URL = dataBuilder.getConfigData("URL");
		WebDriver driver;
		if (remote.toLowerCase().equals("false")) {
			switch (browser) {
			case "chrome":
				driver = new ChromeDriver();
				break;
			case "ie":
				driver = new InternetExplorerDriver();
				break;
			default:
				driver = new FirefoxDriver();
				break;
			}
		} else {
			DesiredCapabilities capabilities = new DesiredCapabilities();
			URL SeleniumGridURL = new URL(seleniumHub);
			capabilities.setBrowserName(browser);
			capabilities.setPlatform(Platform.WINDOWS);
			capabilities.setVersion(version);
			driver = new RemoteWebDriver(SeleniumGridURL, capabilities);
		}
		driver.manage().window().maximize();
		driver.get(URL);
		return driver;

	}

}
