package pom.pack;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HospitalManagementSystem {
	
	@FindBy(xpath = "(//a[text()='Click Here'])[1]")
	private WebElement loginPatients;
	
	@FindBy(xpath = "(//a[text()='Click Here'])[2]")
	private WebElement loginDoctor;
	
	@FindBy(xpath = "(//a[text()='Click Here'])[3]")
	private WebElement loginAdmin;
	
	@FindBy(xpath = "(//a[text()='contact'])[1]")
	private WebElement btnContact;
	
	public HospitalManagementSystem(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public void openPatientLogin() {
		loginPatients.click();
	}
	
	public void openDoctorLogin() {
		loginDoctor.click();
	}
	
	public void openAdminLogin() {
		loginAdmin.click();
	}
	
	public void openContactPage() {
		btnContact.click();
	}
	
}
