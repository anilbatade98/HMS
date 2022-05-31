package settingbrowsers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BaseClass {
	
	
	public static WebDriver openChromeBrowser() {
		System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		return driver;
	}
	
	public static WebDriver openFirefoxBrowser() {
		System.setProperty("webdriver.gecko.driver", "src\\test\\resources\\geckodriver.exe");
		return new FirefoxDriver();
	}
	
	public static WebDriver openEdgeBrowser() {
		System.setProperty("webdriver.edge.driver", "src\\test\\resources\\edgedriver.exe");
		return new EdgeDriver();
	}

}
