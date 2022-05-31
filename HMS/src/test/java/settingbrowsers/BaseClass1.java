package settingbrowsers;

// alg alg package mai hai isliye driver initialize nhi ho rha hai nullPointerException

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

public class BaseClass1 {
	
	public WebDriver driver;
	
	public static WebDriver openChromeBrowser() {
		System.setProperty("webdriver.chrome.driver", "c://webdrivers//chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		return driver;
	}
	
	public static WebDriver openFirefoxBrowser() {
		System.setProperty("webdriver.gecko.driver", "c://webdrivers//geckodriver.exe");
		return new FirefoxDriver();
	}
	
	public static WebDriver openEdgeBrowser() {
		System.setProperty("webdriver.edge.driver", "c://webdrivers//edgedriver.exe");
		return new EdgeDriver();
	}
	
	@Parameters ("browser")
	@BeforeTest
	public void openBrowser(String browser) {
		if(browser.equals("chrome")) {
			driver = openChromeBrowser();
		}
		else if(browser.equals("firefox")) {
			driver = openFirefoxBrowser();
		}
		else if(browser.equals("edge")) {
			driver = openEdgeBrowser();
		}
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
//		driver.manage().window().maximize();
	}
	
	@AfterTest
	public void closeBrowser() {
		driver.quit();
		driver = null;
		System.gc();
	}


}
