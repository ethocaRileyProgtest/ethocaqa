package ethoca_test.riley_shoppingkart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductListPage extends StorePage {

	@FindBy(id="default_products_page_container")
	private WebElement productsTable;
	
	public ProductListPage(WebDriver driver) {
		super(driver);		
	}

	/**
	 * Adds an item to the shopping cart by its name in the list
	 * 
	 * Uses ElementNotFoundException to handle error
	 * 
	 * @param itemName The Item in the Accessories List
	 */
	public void AddItemToCart(String itemName)
	{
		WebElement itemTableCell = productsTable.findElement(By.xpath("//div[@class='productcol' and .//a[contains(text(), '" + itemName + "')]]"));
		WebElement addToCartButton = itemTableCell.findElement(By.className("wpsc_buy_button"));
		addToCartButton.click();
	}
	
	/**
	 * Gets all items listed on the page
	 * @return
	 */
	public List<String> getAllProducts()
	{
		List<WebElement> productLinks = driver.findElements(By.className("wpsc_product_title"));
		List<String> productTitles = new ArrayList<>();
		for(WebElement e : productLinks)
		{
			productTitles.add(e.getText());
		}
		return productTitles;
	}
}
