package ethoca_test.riley_shoppingkart.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class TableWebElement
{
	
	private WebElement tableRoot;
	private Map<String, Integer> columns;
	
	public TableWebElement(WebElement root)
	{
		tableRoot = root;
		populateColumns();
	}
	
	private void populateColumns()
	{
		columns = new HashMap<>(); // wipe it
		int numColumns = tableRoot.findElements(By.xpath("//th")).size();
		for(int x = 1; x <= numColumns; x++)
		{
			String colname = tableRoot.findElement(By.xpath("//th["+x+"]")).getText();
			columns.put(colname, x);
		}
	}
	
	/**
	 * Gets the webelement representation of a single cell in a table
	 * @param column column name in the table header
	 * @param row row number in the table
	 * @return the WebElement representation of the cell
	 */
	public WebElement getCell(String column, int row)
	{
		return tableRoot.findElement(By.xpath("//tr["+row+"]/td[" + columns.get(column) + "]"));
	}
	
	/**
	 * Searches a table for a row based on cell text
	 * @param column column name
	 * @param cellValue text value of the cell
	 * @return row number where the text can be found, or -1 if none
	 */
	public int getRowByCellText(String column, String cellValue)
	{
		List<WebElement> cells = tableRoot.findElements(By.xpath("//tr/td[" + columns.get(column) + "]"));
		for(int x = 0; x < cells.size(); x++)
		{
			if(cells.get(x).getText().equals(cellValue))
			{
				return x;
			}			
		}
		return -1;
	}
	
	/**
	 * Gets the number of rows in a table
	 * @return number of rows in the table
	 */
	public int getTableLength()
	{
		return tableRoot.findElements(By.xpath("//tr")).size();
	}
	
	
}
