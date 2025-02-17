package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

/* Data is Valid - login successful - test passed - logout
 Data is valid- login failed- test failed
 
 Data is invalid - login successful- test failed- logout
 Data is invalid - login failed- test passed */

public class TC003_LoginDDT extends BaseClass{
	
	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class, groups="DDT") //importing data provider method defined in a separate class
	public void verify_loginDDT(String email, String password, String exp) {
		
		try {
			//HomePage steps
			logger.info("***Start of TC003_LoginDDT execution***");
			HomePage hp= new HomePage(driver);
			hp.clickMyAccount();
			logger.info("Clicked on 'My Account'");
			hp.clickLogin();
			logger.info("Clicked on 'Login'");
			
			//LoginPage steps
			logger.info("Reached Login Page");
			LoginPage lp= new LoginPage(driver);
			
			lp.setEmail(email);
			logger.info("Entered email id");
			
			lp.setPassword(password);
			logger.info("Entered password");
			lp.clickLogin();
			logger.info("Clicked on 'Login' button");
			
			//MyAccountPage steps
			MyAccountPage mp= new MyAccountPage(driver);
			boolean pageStatus= mp.pageExists();
			
			if(exp.equalsIgnoreCase("Valid")) {
				if(pageStatus==true) { //Data is Valid - login successful - test passed - logout
					mp.clickMyAccount();
					mp.clickLogout();
					Assert.assertTrue(true);
				}
				else {
					Assert.fail(); //Data is valid- login failed- test failed
				}
			}
			
			if(exp.equalsIgnoreCase("Invalid")){
				if(pageStatus==false) { //Data is invalid - login failed- test passed
					Assert.assertTrue(true);
				}
				else {
					mp.clickMyAccount(); //Data is invalid - login successful- test failed- logout
					mp.clickLogout();
					Assert.fail();
				}
			}
		}catch(Exception e) {
			Assert.fail();
		}
		logger.info("***TC003_LoginDDT Execution Ends***");
	}
}
