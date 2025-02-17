package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass{

	@Test(groups={"Regression","Master"})
	public void verify_account_registration() {
		
		logger.info("***Starting TC001_AccountRegistrationTest execution***");
		HomePage hp= new HomePage(driver);

		hp.clickMyAccount();
		logger.info("Clicked on 'My Account'");
		
		hp.clickRegister();
		logger.info("Clicked on 'Register'");
		
		AccountRegistrationPage regPage= new AccountRegistrationPage(driver);

		regPage.setFirstname(randomString().toUpperCase());
		regPage.setLastname(randomString().toUpperCase());
		regPage.setEmail(randomString()+"@gmail.com");
		regPage.setTelephone(randomNumber());

		String password =randomAlphaNumeric();
		regPage.setPassword(password);
		regPage.setConfirmPassword(password);
		
		try {
			Thread.sleep(5000);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		regPage.setPrivacyPolicy();
		regPage.clickContinue();
		
		try {
			Thread.sleep(5000);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		logger.info("***TC001_AccountRegistrationTest Execution Ends***");

		String confmsg= regPage.getConfirmationMsg();
		Assert.assertEquals(confmsg, "Congratulations! Your new account has been successfully created!");
	}
}
