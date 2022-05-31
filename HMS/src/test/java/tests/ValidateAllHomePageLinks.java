package tests;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

// To validate all links on Home page

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pom.pack.HospitalManagementSystem;
import settingbrowsers.BaseClass;
import utilities.Utility;

public class ValidateAllHomePageLinks extends BaseClass {
	
	WebDriver driver;
	HospitalManagementSystem hms;
	String testID;
	
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
	
	@BeforeClass
	public void createObj() {
		hms = new HospitalManagementSystem(driver);
		
	}
	
	@BeforeMethod
	public void opemHomePage() {
		driver.get("http://localhost/HOSPITAL/index.html");
		
	}
	
	@Test (priority=2)
	public void checkPatientloginPage() {
		testID = "003";
		hms.openPatientLogin();
		Assert.assertEquals(driver.getTitle(), "User-Login");
	}
	
	@Test(priority = 3,dependsOnMethods = "checkAdminLogin")
	public void checkDoctorLogin() {
		testID = "004";
		hms.openDoctorLogin();
		SoftAssert softasert = new SoftAssert();
		softasert.assertEquals(driver.getTitle(), "Doctor Login");
		softasert.assertEquals(driver.getCurrentUrl(), "http://localhost/HOSPITAL/hms/doctor/");
		softasert.assertAll();
	}
	
	@Test(priority = 2)
	public void checkAdminLogin() {
		testID = "005";
		hms.openAdminLogin();
		SoftAssert asrt = new SoftAssert();
		asrt.assertEquals(driver.getTitle(), "Admin-Login");
		asrt.assertEquals(driver.getCurrentUrl(), "http://localhost/HOSPITAL/hms/admin/");
		asrt.assertAll();
//		Assert.fail(); // forcefully failed any test
	}
	
	@Test(priority = 5,enabled = true)
	public void checkContactUs() {
		testID = "006";
		hms.openContactPage();
//		Assert.fail();
		SoftAssert asrt = new SoftAssert();
		asrt.assertEquals(driver.getTitle(), "HMS | Contact us");
		asrt.assertEquals(driver.getCurrentUrl(), "http://localhost/HOSPITAL/contact.php");
	}
	
	@AfterMethod 
	public void backToHome(ITestResult result) throws IOException {
		if(ITestResult.FAILURE == result.getStatus()) {
			Utility.captureScreenshot(driver, "links", testID);
		}
		driver.navigate().back();
	}
	
	@AfterClass
	public void deleteObj() {
		hms = null;
		testID = null;
	}
	
	@AfterTest
	public void closeBrowser() {
		driver.quit();
		driver = null;
		System.gc();
	}

}
