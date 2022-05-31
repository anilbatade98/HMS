package tests;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

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

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import pom.pack.HospitalManagementSystem;
import settingbrowsers.BaseClass;
import utilities.Utility;

// To validate that without entering any value or any field click on submit then its showing error message(validation message) or not

public class TC_Contact_ErrorMsg extends BaseClass {

	WebDriver driver;
	HospitalManagementSystem hms;
	String testID; 
	static ExtentTest test;
	static ExtentHtmlReporter reporter;
	
	@Parameters ("browser")
	@BeforeTest
	public void openBrowser(String browser) {
//		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());//time stamp
		String repName="Test-Report-"+".html";
		reporter = new ExtentHtmlReporter(repName);
		ExtentReports extend = new ExtentReports();
		extend.attachReporter(reporter);
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
	public void openHomePage() {
		driver.get("http://localhost/HOSPITAL/index.html");
	}


	// To check that contact page is open or not
	@Test
	public void contactPage() {
		testID = "007";
		hms.openContactPage();
//		Assert.fail();
		SoftAssert softasrt = new SoftAssert();
		softasrt.assertEquals(driver.getTitle(), "HMS | Contact us");
		softasrt.assertEquals(driver.getCurrentUrl(), "http://localhost/HOSPITAL/contact.php");
		softasrt.assertAll();
	}
	
	@AfterMethod
	public void scr(ITestResult result) throws IOException {
		if(ITestResult.FAILURE == result.getStatus()) {
			Utility.captureScreenshot(driver, "Contact", testID);
		}
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
