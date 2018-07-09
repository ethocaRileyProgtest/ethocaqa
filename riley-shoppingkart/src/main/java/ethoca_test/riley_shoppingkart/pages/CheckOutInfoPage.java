package ethoca_test.riley_shoppingkart.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckOutInfoPage extends StorePage {

	@FindBy(xpath="//tr[contains(@class, 'total_item')]")
	private WebElement totalItem;
	
	@FindBy(xpath="//tr[contains(@class, 'total_shipping')]")
	private WebElement totalShipping;
	
	@FindBy(xpath="//tr[contains(@class, 'total_tax')]")
	private WebElement totalTax;
	
	@FindBy(xpath="//tr[@class ='total_price']")
	private WebElement totalPrice;
	
	@FindBy(xpath = "//input[@value='Purchase']")
	private WebElement purchaseButton;

	@FindBy(id = "shippingSameBilling")
	private WebElement shippingAddressCheckBox;

	@FindBy(id = "wpsc_checkout_form_9")
	private WebElement emailField;

	@FindBy(id = "wpsc_checkout_form_2")
	private WebElement billingFirstNameField;

	@FindBy(id = "wpsc_checkout_form_3")
	private WebElement billingLastNameField;

	@FindBy(id = "wpsc_checkout_form_4")
	private WebElement billingAddressField;

	@FindBy(id = "wpsc_checkout_form_5")
	private WebElement billingCityField;

	@FindBy(id = "wpsc_checkout_form_6")
	private WebElement billingProvinceField;

	@FindBy(id = "wpsc_checkout_form_7_region")
	private WebElement billingProvinceSelectWE;

	private Select billingProvinceSelect = new Select(billingProvinceSelectWE);

	@FindBy(id = "wpsc_checkout_form_7")
	private WebElement billingCountrySelectWE;
	private Select billingCountrySelect = new Select(billingCountrySelectWE);

	@FindBy(id = "wpsc_checkout_form_8")
	private WebElement billingPostalCodeField;

	@FindBy(id = "wpsc_checkout_form_18")
	private WebElement billingPhoneField;

	@FindBy(id = "wpsc_checkout_form_11")
	private WebElement shippingFirstNameField;

	@FindBy(id = "wpsc_checkout_form_12")
	private WebElement shippingLastNameField;

	@FindBy(id = "wpsc_checkout_form_13")
	private WebElement shippingAddressField;

	@FindBy(id = "wpsc_checkout_form_14")
	private WebElement shippingCityField;

	@FindBy(id = "wpsc_checkout_form_15")
	private WebElement shippingProvinceField;

	@FindBy(id = "wpsc_checkout_form_16_region")
	private WebElement shippingProvinceSelectWE;

	private Select shippingProvinceSelect = new Select(shippingProvinceSelectWE);

	@FindBy(id = "wpsc_checkout_form_16")
	private WebElement shippingCountrySelectWE;
	private Select shippingCountrySelect = new Select(shippingCountrySelectWE);

	@FindBy(id = "wpsc_checkout_form_17")
	private WebElement shippingPostalCodeField;

	public CheckOutInfoPage(WebDriver driver) {
		super(driver);
		// just wait for the page to load explicitly, this is junk
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(billingCityField));
	}
	
	/**
	 * Retrieves total shipping cost
	 * @return the total shipping cost of the order
	 */
	public double getTotalShipping()
	{
		testLog.debug("Reading total shipping");
		return Double.parseDouble(totalShipping.getText().replaceAll("[^\\.0123456789]",""));
	}

	/**
	 * Retrieves total item cost
	 * @return the total item cost of the order
	 */
	public double getItemTotal()
	{
		testLog.debug("Reading item total");
		return Double.parseDouble(totalItem.getText().replaceAll("[^\\.0123456789]",""));
	}
	
	/**
	 * Retrieves total order cost
	 * @return the total cost of the order
	 */
	public double getTotalPrice()
	{
		testLog.debug("Reading total price");
		return Double.parseDouble(totalPrice.getText().replaceAll("[^\\.0123456789]",""));
	}
	
	/**
	 * Retrieves total tax cost
	 * @return the total tax cost of the order
	 */
	public double getTaxTotal()
	{
		testLog.debug("Reading tax total");
		return Double.parseDouble(totalTax.getText().replaceAll("[^\\.0123456789]",""));
	}
	
	/**
	 * Clicks the "purchase" button and navigates to the transaction results page
	 * @return transaction results page object
	 */
	public TransactionResultsPage clickPurchase() {
		testLog.debug("Clicking Purchase button");
		purchaseButton.click();
		waitUntilJSReady();
		return new TransactionResultsPage(driver);
	}

	public void checkShippingAddressCheckBox() {
		testLog.debug("Ensuring shipping address box is checked");
		if (shippingAddressCheckBox.isSelected())
			return;
		else
			shippingAddressCheckBox.click();
	}

	public void uncheckShippingAddressCheckBox() {
		testLog.debug("Ensuring shipping address box is unchecked");
		if (!shippingAddressCheckBox.isSelected())
			return;
		else
			shippingAddressCheckBox.click();
	}

	public void fillEmailField(String email) {
		testLog.debug("Filling email field");
		this.emailField.sendKeys(email);
	}

	public void fillBillingFirstNameField(String billingFirstName) {
		testLog.debug("Filling billing first name field");
		this.billingFirstNameField.sendKeys(billingFirstName);
	}

	public void fillBillingLastNameField(String billingLastName) {
		testLog.debug("Filling billing last name field");
		this.billingLastNameField.sendKeys(billingLastName);
	}

	public void fillBillingAddressField(String billingAddress) {
		testLog.debug("Filling billing address field");
		this.billingAddressField.sendKeys(billingAddress);
	}

	public void fillBillingCityField(String billingCity) {
		testLog.debug("Filling billing city field");
		this.billingCityField.sendKeys(billingCity);
	}

	public void fillBillingProvinceSelect(String billingProvinceSelect) {		
		if (this.billingProvinceField.isDisplayed()) {
			testLog.debug("Filling billing state field");
			this.billingProvinceField.sendKeys(billingProvinceSelect);
		} else {
			testLog.debug("Selecting billing state");
			this.billingProvinceSelect
					.selectByVisibleText(billingProvinceSelect);
		}
	}

	public void fillBillingCountrySelect(String billingCountrySelect) {
		testLog.debug("Selecting billing country");
		this.billingCountrySelect.selectByVisibleText(billingCountrySelect);
	}

	public void fillBillingPostalCodeField(String billingPostalCode) {
		testLog.debug("Filling billing postalcode field");
		this.billingPostalCodeField.sendKeys(billingPostalCode);
	}

	public void fillBillingPhoneField(String billingPhone) {
		testLog.debug("Filling phone field");
		this.billingPhoneField.sendKeys(billingPhone);
	}

	public void fillShippingFirstNameField(String shippingFirstName) {
		testLog.debug("Filling shipping first name field");
		this.shippingFirstNameField.sendKeys(shippingFirstName);
	}

	public void fillShippingLastNameField(String shippingLastName) {
		testLog.debug("Filling shipping last name field");
		this.shippingLastNameField.sendKeys(shippingLastName);
	}

	public void fillShippingAddressField(String shippingAddress) {
		testLog.debug("Filling shipping address field");
		this.shippingAddressField.sendKeys(shippingAddress);
	}

	public void fillShippingCityField(String shippingCity) {
		testLog.debug("Filling shipping city field");
		this.shippingCityField.sendKeys(shippingCity);
	}

	public void fillShippingProvinceSelect(String shippingProvinceSelect) {
		if (this.shippingProvinceField.isDisplayed()) {
			testLog.debug("Filling shipping state field");
			this.shippingProvinceField.sendKeys(shippingProvinceSelect);
		} else {
			testLog.debug("Selecting shipping state");
			this.shippingProvinceSelect
					.selectByVisibleText(shippingProvinceSelect);
		}
	}

	public void fillShippingCountrySelect(String shippingCountrySelect) {
		testLog.debug("Selecting shipping country");
		this.shippingCountrySelect.selectByVisibleText(shippingCountrySelect);
	}

	public void fillShippingPostalCodeField(String shippingPostalCode) {
		testLog.debug("Filling shipping postal code field");
		this.shippingPostalCodeField.sendKeys(shippingPostalCode);
	}

}
