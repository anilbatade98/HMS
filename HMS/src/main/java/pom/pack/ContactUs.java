package pom.pack;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactUs {
	
	WebDriver driver;
	
	@FindBy(xpath = "//input[@name='fullname']")
	private WebElement txtName;
	
	@FindBy(xpath = "//input[@name='emailid']")
	private WebElement txtEmail;
	
	@FindBy(xpath = "//input[@name='mobileno']")
	private WebElement txtMobileNo;
	
	@FindBy(xpath = "//textarea[@name='description']")
	private WebElement txtDescription;
	
	@FindBy(xpath = "//input[@name='submit']")
	private WebElement btnSubmit;
	
	public ContactUs(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public String errorMsgName() {
		return txtName.getAttribute("validationMessage");
	}
	
	public void setName(String name) {
		txtName.sendKeys(name);
	}
	
	public void setEmail(String email) {
		txtEmail.sendKeys(email);
	}
	
	public void setMobileNo(String mobileNo) {
		txtMobileNo.sendKeys(mobileNo);
	}
	
	public void setDescription(String description) {
		txtDescription.sendKeys(description);
	}
	
	public void clickSubmit() {
		btnSubmit.click();
	}
	
	public void setContactDetails(String name, String email, String mobileNo, String description) {
		txtName.sendKeys(name);
		txtEmail.sendKeys(email);
		txtMobileNo.sendKeys(mobileNo);
		txtDescription.sendKeys(description);
		btnSubmit.click();
	}

}
