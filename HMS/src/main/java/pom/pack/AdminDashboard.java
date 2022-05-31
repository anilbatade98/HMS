package pom.pack;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AdminDashboard {
	
	WebDriver driver;
	WebDriverWait wait;
	
	@FindBy (xpath = "//span[@class='username']")
	WebElement admin;
	
	@FindBy (xpath = "(//ul[@class='dropdown-menu dropdown-dark']//a)[2]")
	WebElement btnLogout;
	
	@FindBy (xpath = "(//div[@class='item-inner'])[2]")
	WebElement btnDoctors;
	
	@FindBy (xpath = "//a[@href='add-doctor.php']")
	WebElement btnAddDoctor;
	
	@FindBy (xpath = "//a")
	List<WebElement> links;
	
	
	public AdminDashboard(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 5);
	}
	
	public void logout() {
		admin.click();
		btnLogout.click();
	}
	
	public void openAddDoctor() {
		btnDoctors.click();
		btnAddDoctor.click();
	}
	
	public void checkBrokenLinks() {
		for (WebElement liks : links) {
			if(liks.getText().equals("")){
				System.out.println(liks.getText()+" - link broken: " + liks.getAttribute("href"));
			}
			else {
				System.out.println(liks.getText()+" - link ok: " + liks.getAttribute("href"));
			}
			
		}
	}
	
	
}
