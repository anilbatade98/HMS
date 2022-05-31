package tests;
import java.io.IOException;
// wrp to to check user can fill contact form or not
import java.util.concurrent.TimeUnit;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.Alert;
//import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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

import pom.pack.ContactUs;
import pom.pack.HospitalManagementSystem;
import settingbrowsers.BaseClass;
import utilities.Utility;

public class TC_Contact_001 extends BaseClass {

	WebDriver driver;
	ContactUs contactUs;
	WebDriverWait wait;
	HospitalManagementSystem hms;
	Alert alrt;
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
		wait = new WebDriverWait(driver,5);
		hms = new HospitalManagementSystem(driver);
		contactUs = new ContactUs(driver);
	}

	@BeforeMethod
	public void openContactPage() throws EncryptedDocumentException, IOException {
		driver.get("http://localhost/HOSPITAL/index.html");
		hms.openContactPage();
		
		contactUs.setName(Utility.getExlData("contact", 1, 0));
		contactUs.setEmail(Utility.getExlData("contact", 1, 1));
		contactUs.setMobileNo(Utility.getExlData("contact", 1, 2));
		contactUs.setDescription(Utility.getExlData("contact", 1, 3));
		contactUs.clickSubmit();
	}
	
	

	// To verify alert is present or not
	@Test (invocationCount = 3, timeOut = 4)
	public void alertPresent() {
		testID = "001";
		System.out.println(ExpectedConditions.alertIsPresent());
		if(wait.until(ExpectedConditions.alertIsPresent()) != null) {
//			Assert.fail();
			Assert.assertTrue(true);
		}
		else {
			Assert.assertTrue(false);
		}
	}

	// To check that alert message is correct or not
	@Test (priority = 1)
	public void checkAlertMsg() {
		testID = "002";
		
		wait.until(ExpectedConditions.alertIsPresent());
		alrt = driver.switchTo().alert();
		if(alrt.getText().equals("Your information succesfully submitted")) {
			Assert.assertTrue(true);
		}
		else {
			Assert.assertTrue(false);
		}
	}
	

	@AfterMethod
	public void acceptAlert(ITestResult result) throws IOException {
		if(ITestResult.FAILURE == result.getStatus()) {
			Utility.captureScreenshot(driver, "", testID);
		}
		if(wait.until(ExpectedConditions.alertIsPresent()) != null) {
		alrt = driver.switchTo().alert();
		alrt.accept();
		}
//		alrt.sendKeys(Keys.ENTER);  // not allowed/ work
		driver.switchTo().defaultContent();
	}

	@AfterClass
	public void deleteObj() {
		hms = null;
		contactUs = null;
		testID = "";
	}
	
	@AfterTest
	public void closeBrowser() {
		driver.quit();
		driver = null;
		System.gc();
	}

}
