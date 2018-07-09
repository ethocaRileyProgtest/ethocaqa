package ethoca_test.riley_shoppingkart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.log4testng.Logger;

public class StorePage {

	protected static final Logger testLog = Logger
			.getLogger(StorePage.class);
	
	protected WebDriver driver;
	
	@FindBy(xpath="//a[@class='cart_icon' and @title='Checkout']")
	private WebElement checkOutLink;
	
	@FindBy(linkText="Product Category")
	private WebElement productCategoryMenu;

	public StorePage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public ProductListPage goToAccessories()
	{
		testLog.debug("Navigating to accessories page");
		Actions action = new Actions(driver);		
		action.moveToElement(productCategoryMenu).build().perform();
		productCategoryMenu.findElement(By.xpath("//a[contains(text(), 'Accessories')]")).click();
		return new ProductListPage(driver);
	}
	
	public CheckOutCartPage clickCheckOut()
	{
		testLog.debug("Navigating to checkout cart page");
		checkOutLink.click();
		waitUntilJSReady();
		return new CheckOutCartPage(driver);
	}
	
    //Wait Until JS Ready
    public void waitUntilJSReady() {
        WebDriverWait wait = new WebDriverWait(driver,15);
        JavascriptExecutor jsExec = (JavascriptExecutor) driver;
 
        //Wait for Javascript to load
        ExpectedCondition<Boolean> jsLoad = driver -> ((JavascriptExecutor) driver)
                .executeScript("return document.readyState").toString().equals("complete");
 
        //Get JS is Ready
        boolean jsReady =  (Boolean) jsExec.executeScript("return document.readyState").toString().equals("complete");
 
        //Wait Javascript until it is Ready!
        if(!jsReady) {
            testLog.debug("JS in NOT Ready!");
            //Wait for Javascript to load
            wait.until(jsLoad);
        } else {
            testLog.debug("JS is Ready!");
        }
    }
}
