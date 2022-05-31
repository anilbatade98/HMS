package tests;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.EncryptedDocumentException;
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

import pom.pack.AdminDashboard;
import pom.pack.AdminLogin;
import pom.pack.HospitalManagementSystem;
import settingbrowsers.BaseClass;
import utilities.Utility;

public class ValidateAdminDashboard extends BaseClass {
	
	WebDriver driver;
	AdminDashboard adm;
	AdminLogin admLogin;
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
		admLogin = new AdminLogin(driver);
		adm = new AdminDashboard(driver); 
	}
		
	@BeforeMethod 
	public void openAdminAccount() throws EncryptedDocumentException, IOException {
		driver.get("http://localhost/HOSPITAL/index.html");
		hms.openAdminLogin();
		admLogin.loginAdmin(Utility.getExlData("admin", 1, 0),Utility.getExlData("admin", 1, 1));	
	}
	
	@Test
	public void checkBrokenLinks() throws IOException {
		testID = "008";
		adm.checkBrokenLinks();
		Assert.fail(); // forcefully failed test
	}
	
	@Test 
	public void openAddNewDoctor() {
		testID = "009";
		adm.openAddDoctor();
		SoftAssert asrt = new SoftAssert();
		asrt.assertEquals(driver.getTitle(), "Admin | Add Doctor");
		asrt.assertEquals(driver.getCurrentUrl(), "http://localhost/HOSPITAL/hms/admin/add-doctor.php");
	}
	
	@AfterMethod 
	public void logout(ITestResult result) throws IOException {
		if(ITestResult.FAILURE == result.getStatus()) {
			Utility.captureScreenshot(driver, "Admin", testID);
		}
		adm.logout();
	}
	
	@AfterClass
	public void deleteObj() {
		hms = null;
		adm = null;
		admLogin = null;
		testID = null;
	}
	
	@AfterTest
	public void closeBrowser() {
		driver.quit();
		driver = null;
		System.gc();
	}
	
}

