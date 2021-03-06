package ethoca_test.riley_shoppingkart;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;

import ethoca_test.riley_shoppingkart.pages.CheckOutCartPage;
import ethoca_test.riley_shoppingkart.pages.CheckOutInfoPage;
import ethoca_test.riley_shoppingkart.pages.ProductListPage;
import ethoca_test.riley_shoppingkart.pages.StorePage;
import ethoca_test.riley_shoppingkart.pages.TransactionResultsPage;
import ethoca_test.riley_shoppingkart.tests.SeleniumTemplate;

public class TestBuySingleItem extends SeleniumTemplate 
{
		
	Map<String, Object> addressTDO[] = new HashMap[2];
	
	/**
	 * This is just a demo/test implementation of a Test Data Object. Use JAXB or JSON marshaling for a real one please :)
	 * Note that the Phone field is not required, and only used in billing/contact address. Since "AddressTDO" is reusable, it is just optional.
	 */
	private void populateAddressTDO()
	{
		Map<String, Object> addr1 = new HashMap<String, Object>();
		Map<String, Object> addr2 = new HashMap<String, Object>();
		addr1.put("FirstName", "John");
		addr1.put("LastName", "Smith");
		addr1.put("Address", "201 TestCase Dr");
		addr1.put("City", "Toronto");
		addr1.put("State", "Ontario");
		addr1.put("Country", "Canada");
		addr1.put("Postal", "L1N2Q4");
		addr1.put("Phone", "666-7734");
		addressTDO[0] = addr1;
		addr2.put("FirstName", "John");
		addr2.put("LastName", "Johnson");
		addr1.put("Address", "221 Test Rd");
		addr2.put("City", "Brampton");
		addr2.put("State", "Ontario");
		addr2.put("Country", "Canada");
		addr2.put("Postal", "L5N2Z4");
		addr2.put("Phone", "123-4567");
		addressTDO[1] = addr2; 
	}
	
	@DataProvider(name="kartItemProvider")
	public Object[][] prepareTestData()
	{	
		populateAddressTDO(); // in a real implementation we would use JAXB marshaling from file here
		return new Object[][] {{"Magic Mouse", "john.smith@mailinator.com", addressTDO[0], true, null}, {"Magic Mouse", "qa@mailinator.com", addressTDO[1], false, addressTDO[0]}};				
	}
	
	ProductListPage accessories;
	
	/**
	 * Start driver, set up and navigate to the accessories page.
	 */
	@BeforeMethod
	public void startTest()
	{
		getDriver().get("http://store.demoqa.com");
		StorePage mainPage = new StorePage(driver);
		accessories = mainPage.goToAccessories();
	}
	
	@Test(dataProvider = "kartItemProvider")
	public void testBuySingleItem(String item, String email, HashMap<String, Object> billingAddress, boolean checkbox, @Optional HashMap<String, Object> shippingAddress)
	{
				
		// should get price for item before we buy for expected result
		double expectedPrice = accessories.getPriceForItem(item);
		accessories.AddItemToCart(item);
		CheckOutCartPage cart = accessories.clickCheckOut();

		int expectedQty = 1; // TODO: move to TDO when marshaled		
		
		testLog.debug("Verify cart items present");
		Map<String, Integer> items = cart.getCartItems();
		Assert.assertEquals(items.get(item), (Integer) expectedQty, "ERROR: Mismatched item quantity for item [" + item + "]");
		
		CheckOutInfoPage info = cart.clickContinue();
		testLog.debug("Verifying total with shipping");
		Assert.assertEquals(info.getItemTotal(), expectedQty * expectedPrice, "ERROR: Price does not match in item total");
		Assert.assertEquals(info.getItemTotal()+info.getTotalShipping(), info.getTotalPrice(), "ERROR: Shipping cost incorrectly added to total");
		
		info.fillEmailField(email);
		// casting here is dumb, but again, we're not using strongly typed fields
		// have to fill country first wow
		info.fillBillingCountrySelect((String) billingAddress.get("Country"));		
		info.fillBillingProvinceSelect((String) billingAddress.get("State"));
		info.fillBillingPostalCodeField((String) billingAddress.get("Postal"));
		info.fillBillingPhoneField((String) billingAddress.get("Phone"));
		info.fillBillingLastNameField((String) billingAddress.get("LastName"));
		info.fillBillingFirstNameField((String) billingAddress.get("FirstName"));
		info.fillBillingCityField((String) billingAddress.get("City"));
		info.fillBillingAddressField((String) billingAddress.get("Address"));
		
		if(checkbox)
		{
			info.checkShippingAddressCheckBox();
		}
		else
		{			
			info.uncheckShippingAddressCheckBox();		
			info.fillShippingCountrySelect((String) shippingAddress.get("Country"));
			info.fillShippingProvinceSelect((String) shippingAddress.get("State"));
			info.fillShippingPostalCodeField((String) shippingAddress.get("Postal"));
			info.fillShippingLastNameField((String) shippingAddress.get("LastName"));
			info.fillShippingFirstNameField((String) shippingAddress.get("FirstName"));
			info.fillShippingCityField((String) shippingAddress.get("City"));
			info.fillShippingAddressField((String) shippingAddress.get("Address"));
		}
		
		TransactionResultsPage result = info.clickPurchase();
		
		double itemPrice = result.getPriceForItem(item);
		double totalForItem = result.getTotalForItem(item);		
		double total = result.getTotal();
		double shipping = result.getTotalShipping();
		int qty = result.getQtyForItem(item);		
		testLog.debug("Verifying total with quantity");
		Assert.assertEquals(itemPrice * qty, totalForItem, "ERROR: Item Total Incorrect");
		testLog.debug("Verifying total with shipping");
		Assert.assertEquals(itemPrice +shipping, total, "ERROR: Shipping cost incorrectly added to total");
	}
}
