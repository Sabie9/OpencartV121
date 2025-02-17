package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage{
	WebDriver driver;
	
	public MyAccountPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//span[text()='My Account']")
	WebElement lnkMyAccount;
	
	@FindBy(xpath="//a[text()='Logout']")
	WebElement lnkLogout;
	
	@FindBy(xpath="//h2[text()='My Account']")
	WebElement headerMyAccount;
	
	public void clickMyAccount() {
		lnkMyAccount.click();
	}
	
	public void clickLogout() {
		lnkLogout.click();
	}
	
	public boolean pageExists() {
		try {
			return headerMyAccount.isDisplayed();
		} catch(Exception e) {
			return false;
		}
	}
}
