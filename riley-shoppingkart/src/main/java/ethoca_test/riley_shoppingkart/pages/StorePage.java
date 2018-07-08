package ethoca_test.riley_shoppingkart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class StorePage {

	protected WebDriver driver;
	
	@FindBy(xpath="//a[@class='cart_icon' AND @title='Checkout']")
	private WebElement checkOutLink;
	
	@FindBy(linkText="Product Category")
	private WebElement productCategoryMenu;

	public StorePage(WebDriver driver)
	{
		this.driver = driver;
	}
	
	public ProductListPage goToAccessories()
	{
		Actions action = new Actions(driver);
		action.moveToElement(productCategoryMenu).build().perform(); // hover
		productCategoryMenu.findElement(By.linkText("accessories"));
		return new ProductListPage(driver);
	}
	
	public CheckOutCartPage clickCheckOut()
	{
		checkOutLink.click();
		return new CheckOutCartPage(driver);
	}
}
