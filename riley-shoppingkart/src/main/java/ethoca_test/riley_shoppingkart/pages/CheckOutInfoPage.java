package ethoca_test.riley_shoppingkart.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckOutInfoPage extends StorePage {

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
	private WebElement billingProvinceSelectWE;
	
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
	private WebElement shippingProvinceSelectWE;

	@FindBy(id = "wpsc_checkout_form_16")
	private WebElement shippingCountrySelectWE;
	private Select shippingCountrySelect = new Select(shippingCountrySelectWE);

	@FindBy(id = "wpsc_checkout_form_17")
	private WebElement shippingPostalCodeField;

	public CheckOutInfoPage(WebDriver driver) {
		super(driver);
		// just wait for the page to load explicitly, this is junk
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(billingProvinceSelectWE));
	}

	public TransactionResultsPage clickPurchase() {
		purchaseButton.click();
		waitUntilJSReady();
		return new TransactionResultsPage(driver);
	}

	public void checkShippingAddressCheckBox() {
		if (shippingAddressCheckBox.isSelected())
			return;
		else
			shippingAddressCheckBox.click();
	}

	public void uncheckShippingAddressCheckBox() {
		if (!shippingAddressCheckBox.isSelected())
			return;
		else
			shippingAddressCheckBox.click();
	}

	public void fillEmailField(String email) {
		this.emailField.sendKeys(email);
	}

	public void fillBillingFirstNameField(String billingFirstName) {
		this.billingFirstNameField.sendKeys(billingFirstName);
	}

	public void fillBillingLastNameField(String billingLastName) {
		this.billingLastNameField.sendKeys(billingLastName);
	}

	public void fillBillingAddressField(String billingAddress) {
		this.billingAddressField.sendKeys(billingAddress);
	}

	public void fillBillingCityField(String billingCity) {
		this.billingCityField.sendKeys(billingCity);
	}

	public void fillBillingProvinceSelect(String billingProvinceSelect) {
		this.billingProvinceSelectWE.sendKeys(billingProvinceSelect);
	}

	public void fillBillingCountrySelect(String billingCountrySelect) {
		this.billingCountrySelect.selectByVisibleText(billingCountrySelect);
	}

	public void fillBillingPostalCodeField(String billingPostalCode) {
		this.billingPostalCodeField.sendKeys(billingPostalCode);
	}

	public void fillBillingPhoneField(String billingPhone) {
		this.billingPhoneField.sendKeys(billingPhone);
	}

	public void fillShippingFirstNameField(String shippingFirstName) {
		this.shippingFirstNameField.sendKeys(shippingFirstName);
	}

	public void fillShippingLastNameField(String shippingLastName) {
		this.shippingLastNameField.sendKeys(shippingLastName);
	}

	public void fillShippingAddressField(String shippingAddress) {
		this.shippingAddressField.sendKeys(shippingAddress);
	}

	public void fillShippingCityField(String shippingCity) {
		this.shippingCityField.sendKeys(shippingCity);
	}

	public void fillShippingProvinceSelect(String shippingProvinceSelect) {
		this.shippingProvinceSelectWE.sendKeys(shippingProvinceSelect);
	}

	public void fillShippingCountrySelect(String shippingCountrySelect) {
		this.shippingCountrySelect.selectByVisibleText(shippingCountrySelect);
	}

	public void fillShippingPostalCodeField(String shippingPostalCode) {
		this.shippingPostalCodeField.sendKeys(shippingPostalCode);
	}

}
