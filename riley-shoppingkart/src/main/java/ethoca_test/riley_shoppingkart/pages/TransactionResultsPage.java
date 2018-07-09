package ethoca_test.riley_shoppingkart.pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import ethoca_test.riley_shoppingkart.utils.TableWebElement;

public class TransactionResultsPage extends StorePage {

	private final String SHIPPING_STRING = "Total Shipping: $";
	private final String TOTAL_STRING = "Total: $";
			
	@FindBy(className="wpsc-purchase-log-transaction-results")
	private WebElement transactionResultsTableWE;
	
	TableWebElement transactionResultsTable;
	
	@FindBy(xpath="//div[@class='wpsc-transaction-results-wrap']/p[3]")
	private WebElement totals;	
	
	public TransactionResultsPage(WebDriver driver) {
		super(driver);
		transactionResultsTable = new TableWebElement(transactionResultsTableWE);
	}
	
	/**
	 * Gets the price for a given item
	 * @param itemname Item name
	 * @return price of item in table
	 */
	public double getPriceForItem(String itemname)
	{
		testLog.debug("Getting line price for [" + itemname + "]");
		int row = transactionResultsTable.getRowByCellText("Name", itemname);
		return Double.parseDouble(transactionResultsTable.getCell("Price", row).getText().replace("$", ""));
	}
	
	/**
	 * Gets the quantity of a given item in the table
	 * @param itemname Item name
	 * @return quantity of item in table
	 */
	public int getQtyForItem(String itemname)
	{
		testLog.debug("Getting line quantity for [" + itemname + "]");
		int row = transactionResultsTable.getRowByCellText("Name", itemname);
		return Integer.parseInt(transactionResultsTable.getCell("Quantity", row).getText());
	}
	/**
	 * Gets the total price of an item in the table
	 * @param itemname item name
	 * @return total price in table
	 */
	public double getTotalForItem(String itemname)
	{
		testLog.debug("Getting line total for [" + itemname + "]");
		int row = transactionResultsTable.getRowByCellText("Name", itemname);
		return Double.parseDouble(transactionResultsTable.getCell("Item Total", row).getText().replace("$", ""));
	}
	
	/**
	 * Gets the total shipping cost of all items
	 * @return total shipping
	 */
	public double getTotalShipping()
	{
		testLog.debug("Get shipping total");
		String text = totals.getText();
		int breakpoint = text.indexOf("\n");
		String totalShipping = text.substring(text.indexOf(SHIPPING_STRING) +  SHIPPING_STRING.length(), breakpoint); 
		return Double.parseDouble(totalShipping);
	}

	/**
	 * Gets the total cost of the order 
	 * @return total cost of order
	 */
	public double getTotal()
	{
		testLog.debug("Get order total");
		String text = totals.getText();
		String total = text.substring(text.indexOf(TOTAL_STRING) + TOTAL_STRING.length()); // rest of string
		return Double.parseDouble(total);
	}

	
}
