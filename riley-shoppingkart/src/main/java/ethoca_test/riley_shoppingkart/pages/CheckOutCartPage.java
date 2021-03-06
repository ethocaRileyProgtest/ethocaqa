package ethoca_test.riley_shoppingkart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckOutCartPage extends StorePage {

	@FindBy(className = "checkout_cart")
	private WebElement cartItemsTable;

	@FindBy(xpath="//a[@class='step2' and ./span[contains(text(), 'Continue')]]")
	private WebElement continueButton;
	
	public CheckOutCartPage(WebDriver driver) {
		super(driver);
	}

	/**
	 * Lists the items in the cart
	 * 
	 * @return HashMap implementation keyed by product name, containing the
	 *         quantity of each product in the cart
	 */
	public Map<String, Integer> getCartItems() {
		testLog.debug("Retrieving cart items");
		Map<String, Integer> cartItems = new HashMap<>();
		List<WebElement> cartItemsLines = cartItemsTable.findElements(By
				.xpath("//tr[contains(@class, 'product_row')]"));
		for (WebElement elem : cartItemsLines) {
			cartItems
					.put(elem
							.findElement(
									By.xpath("//td[contains(@class, 'wpsc_product_name')]/a"))
							.getText(), Integer.parseInt(elem.findElement(
							By.xpath("//input[@name='quantity']")).getAttribute("value")));
		}
		return cartItems;
	}
	
	/**
	 * Click the continue button and go to the info page
	 * @return Cart info page object
	 */
	public CheckOutInfoPage clickContinue()
	{
		testLog.debug("Clicking Continue button");
		continueButton.click();
		return new CheckOutInfoPage(driver);
	}
}
