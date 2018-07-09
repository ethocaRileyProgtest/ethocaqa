package ethoca_test.riley_shoppingkart.tests;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.log4testng.Logger;

/**
 * Class to be extended by Selenium tests Handles webdriver manager and TestNG
 * parameters
 * 
 * @author Riley
 *
 */
public class SeleniumTemplate {

	protected static final Logger testLog = Logger
			.getLogger(SeleniumTemplate.class);

	protected WebDriver driver;

	private enum BrowserEnum {
		CHROME, FIREFOX, IE, EDGE;
	}

	/**
	 * Starts the Selenium driver, importing using WebDriverManager
	 * @param browser browser to be run.
	 */
	@Parameters({ "browser" })
	@BeforeClass
	public void startSeleniumBrowser(@Optional("CHROME") String browser) {
		testLog.debug("Preparing to launch browser [" + browser + "]");
		BrowserEnum startBrowser = BrowserEnum.valueOf(browser.toUpperCase());
		switch (startBrowser) {
		case IE: {
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
			break;
		}
		case FIREFOX: {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		}
		case EDGE: {
			WebDriverManager.chromedriver().setup();
			driver = new EdgeDriver();
			break;
		}
		default: {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		}
		}
	}

	/**
	 * Run any logging customizations
	 */
	@BeforeSuite
	public void setUpLogging() {
		testLog.debug("Logging initialized!");
	}

	/**
	 * Clean up, closing the browser
	 */
	@AfterClass
	public void tearDownDriver() {
		testLog.debug("Tests complete. Closing browser.");
		driver.close();
	}
	
	protected WebDriver getDriver()
	{
		return driver;
	}
}
