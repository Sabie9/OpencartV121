package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.text.RandomStringGenerator;
import org.apache.logging.log4j.LogManager; //Log4j
import org.apache.logging.log4j.Logger;     //Log4j
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
	
	public static WebDriver driver;
	public Logger logger;
	public Properties p;
	
	@BeforeClass(groups= {"Regression","Sanity","Master","DDT"})
	@Parameters({"os","browser"})
	public void setup(String os, String browser) throws IOException {
		logger= LogManager.getLogger(this.getClass());
		
		logger.info("***Starting BaseClass setup***");

	    // Log OS and browser parameters
	    logger.info("OS: " + os);
	    logger.info("Browser: " + browser);
		try {
			switch(browser.toLowerCase()) {
				case "chrome" : logger.info("Initializing ChromeDriver...");driver= new ChromeDriver(); break;
				case "edge" : driver= new EdgeDriver(); break;
				case "firefox" : driver= new FirefoxDriver(); break;
				default: System.out.println("Invalid browser name...");return;
			}
		}catch(Exception e) {
			logger.error("Error initializing WebDriver: " + e.getMessage());
	        throw e;
		}
		
		FileReader file= new FileReader(".//src//test//resources//config.properties");
		p=new Properties();
		p.load(file);
		
		

		
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get(p.getProperty("appURL1"));
	}
	
	@AfterClass(groups= {"Regression","Sanity","Master","DDT"})
	public void teardown() {
		driver.quit();
	}
	
	RandomStringGenerator stringGenerator= new RandomStringGenerator.Builder().withinRange('a','z').withinRange('A','Z').get();
	RandomStringGenerator numberGenerator= new RandomStringGenerator.Builder().withinRange('0','9').get();

	
	public String randomString() {
		String generatedString= stringGenerator.generate(5);
		return generatedString;
	}
	
	public String randomNumber() {
		String generatedNumber= numberGenerator.generate(5);
		return generatedNumber;
	}
	
	public String randomAlphaNumeric() {
		String generatedString= stringGenerator.generate(5);
		String generatedNumber= numberGenerator.generate(5);
		return generatedString+"@"+generatedNumber;
	}

	public String captureScreen(String tname) {
		// TODO Auto-generated method stub
		String timeStamp= new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		
		TakesScreenshot takesScreenshot= (TakesScreenshot) driver;
		File sourceFile=takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath= System.getProperty("user.dir")+"\\screenshots\\"+tname+"_"+timeStamp+".png";
		File targetFile=new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
		
		return targetFilePath;
	}

}
