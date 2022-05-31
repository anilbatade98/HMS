package test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;

import pom.pack.HospitalManagementSystem;
import settingbrowsers.BaseClass;
import utilities.Utility;

public class TestScr extends BaseClass {

	WebDriver driver;
	HospitalManagementSystem hms;
	
//	@Parameters ("browser")
//	@BeforeTest
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

//	@BeforeClass 
	public void createObj() {
		hms = new HospitalManagementSystem(driver);
		driver.get("http://localhost/HOSPITAL/index.html");
	}
	
//	@Test
	public void test() throws IOException {
		hms.openAdminLogin();
		Utility.captureScreenshot(driver, "Test", "TC_001");
	}
	
//	@Test
	public void readExcelData() throws  IOException {
		System.out.println("data");
		String a = Utility.getExlData("admin", 1, 1);
		System.out.println(a);
	}
	
//	@AfterClass
	public void deleteObj() {
		hms = null;
	}
	
	
//	@AfterTest
	public void closeBrowser() {
		driver.quit();
		driver = null;
		System.gc();
	}
	
	public static void main(String[] args) throws EncryptedDocumentException, Exception {
		System.out.println("data");
		String a = Utility.getExlData("Patient_Login", 0, 0);
		System.out.println(a);
	}
	
}


