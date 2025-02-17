package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass{
	
	@Test(groups= {"Sanity","Master"})
	public void verify_login() {
		
		try {
			//HomePage steps
			logger.info("***Start of TC002_LoginTest execution***");
			HomePage hp= new HomePage(driver);
			hp.clickMyAccount();
			logger.info("Clicked on 'My Account'");
			hp.clickLogin();
			logger.info("Clicked on 'Login'");
			
			//LoginPage steps
			logger.info("Reached Login Page");
			LoginPage lp= new LoginPage(driver);
			lp.setEmail(p.getProperty("email"));
			logger.info("Entered email id");
			lp.setPassword(p.getProperty("password"));
			logger.info("Entered password");
			lp.clickLogin();
			logger.info("Clicked on 'Login' button");
			
			//MyAccountPage steps
			MyAccountPage mp= new MyAccountPage(driver);
			boolean pageStatus= mp.pageExists();
			
			Assert.assertEquals(pageStatus, true, "Login test Failed!");
		}catch(Exception e) {
			Assert.fail();
		}
		logger.info("***TC002_LoginTest Execution Ends***");
	}

}
