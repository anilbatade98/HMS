package pom.pack;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AdminLogin {
	
	WebDriver driver;
	WebDriverWait wait;
	
	@FindBy (xpath = "//input[@name='username']")
	WebElement txtUsername;
	
	@FindBy (xpath = "//input[@name='password']")
	WebElement txtPassword;
	
	@FindBy (xpath = "//button[@name='submit']")
	WebElement btnSubmit;
	
	public AdminLogin(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 5);
	}
	
	public void loginAdmin(String uName, String pass) {
		wait.until(ExpectedConditions.visibilityOf(txtUsername)).sendKeys(uName);
		wait.until(ExpectedConditions.visibilityOf(txtPassword)).sendKeys(pass);
		btnSubmit.click();
	}
	
}
