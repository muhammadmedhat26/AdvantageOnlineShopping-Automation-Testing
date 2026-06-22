package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class CheckoutPage extends BasePage {
    // ======================================================
    //edit shipping details form  locators
    // ======================================================
    private By firstNameInput = By.name("first_name");
    private By lastNameInput = By.xpath("//sec-view[@a-hint='Last Name']//input");
    private By phoneNumberInput = By.xpath("//sec-view[@a-hint='Phone Number']//input");

    private By countryDropdown = By.name("countryListbox");
    private By cityInput = By.xpath("//sec-view[@a-hint='City']//input");
    private By addressInput = By.xpath("//sec-view[@a-hint='Address']//input");
    private By postalCodeInput = By.xpath("//sec-view[@a-hint='Postal Code']//input");
    private By stateRegionInput = By.xpath("//sec-view[@a-hint='State / Province / Region']//input");

    private By saveChangesCheckbox = By.name("agree_agreement");
    private By editShippingNextButton = By.xpath("//sec-sender[@a-value='NEXT']");
    private By editShippingBackButton = By.xpath("//a[normalize-space()='BACK']");

    private By editShippingDetailsForm = By.id("userDetailsEditMode");

    // ======================================================
    // shipping details page locators
    // ======================================================
    private By orderPaymentTitle = By.xpath("//h3[contains(.,'ORDER PAYMENT')]");
    private By shippingDetailsStep = By.id("userSection");
    private By paymentMethodSection = By.id("paymentMethod");

    private By nextButton = By.id("next_btn");
    private By editShippingDetailsLink = By.xpath("//a[contains(text(),'Edit shipping details')]");

    // ======================================================
    //payment method form locators
    // ======================================================
    private By safePayOption = By.xpath("//div[contains(@class,'imgRadioButton')][1]");
    private By masterCardOption = By.xpath("//div[contains(@class,'imgRadioButton')][2]");
    private By safePayUsername = By.name("safepay_username");
    private By safePayPassword = By.name("safepay_password");
    private By payNowButton = By.id("pay_now_btn_SAFEPAY");
    private By backToShippingDetailsLink = By.xpath("//a[contains(normalize-space(),'Back to shipping details')]");
    private By saveSafePayCheckbox = By.name("save_safepay");


    public CheckoutPage(WebDriver driver) {
        super(driver);
    }


// ======================================================
// page validations
// ======================================================

    public boolean isCheckoutPageDisplayed() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(orderPaymentTitle)
        ).isDisplayed();
    }

    public boolean isShippingDetailsStepDisplayed() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(shippingDetailsStep)
        ).isDisplayed();
    }

    public boolean isPaymentMethodSectionDisplayed() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(paymentMethodSection)
        ).isDisplayed();
    }


// ======================================================
// shipping details page actions
// ======================================================

    public CheckoutPage clickEditShippingDetails() {
        wait.until(ExpectedConditions.elementToBeClickable(editShippingDetailsLink)).click();

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(editShippingDetailsForm)
        );

        return this;
    }

    public CheckoutPage clickNext() {
        wait.until(ExpectedConditions.elementToBeClickable(nextButton)).click();

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(paymentMethodSection)
        );

        return this;
    }


// ======================================================
// edit shipping details form actions
// ======================================================

    public CheckoutPage enterFirstName(String firstName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameInput)).clear();
        driver.findElement(firstNameInput).sendKeys(firstName);
        return this;
    }

    public CheckoutPage enterLastName(String lastName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(lastNameInput)).clear();
        driver.findElement(lastNameInput).sendKeys(lastName);
        return this;
    }

    public CheckoutPage enterPhoneNumber(String phoneNumber) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(phoneNumberInput)).clear();
        driver.findElement(phoneNumberInput).sendKeys(phoneNumber);
        return this;
    }

    public CheckoutPage enterCity(String city) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(cityInput)).clear();
        driver.findElement(cityInput).sendKeys(city);
        return this;
    }

    public CheckoutPage enterAddress(String address) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(addressInput)).clear();
        driver.findElement(addressInput).sendKeys(address);
        return this;
    }

    public CheckoutPage enterPostalCode(String postalCode) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(postalCodeInput)).clear();
        driver.findElement(postalCodeInput).sendKeys(postalCode);
        return this;
    }

    public CheckoutPage enterStateRegion(String stateRegion) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(stateRegionInput)).clear();
        driver.findElement(stateRegionInput).sendKeys(stateRegion);
        return this;
    }
    public CheckoutPage selectCountry(String country) {

        Select select = new Select(
                wait.until(ExpectedConditions.visibilityOfElementLocated(countryDropdown))
        );

        select.selectByVisibleText(country);

        return this;
    }

    public CheckoutPage checkSaveChanges() {
        wait.until(ExpectedConditions.elementToBeClickable(saveChangesCheckbox)).click();
        return this;
    }

    public CheckoutPage clickEditShippingNext() {
        wait.until(ExpectedConditions.elementToBeClickable(editShippingNextButton)).click();

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(paymentMethodSection)
        );

        return this;
    }

    public CheckoutPage clickEditShippingBack() {
        wait.until(ExpectedConditions.elementToBeClickable(editShippingBackButton)).click();

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(shippingDetailsStep)
        );

        return this;
    }


// ======================================================
// payment method actions
// ======================================================

    public CheckoutPage selectSafePay() {
        wait.until(ExpectedConditions.elementToBeClickable(safePayOption)).click();
        return this;
    }

    public CheckoutPage selectMasterCard() {
        wait.until(ExpectedConditions.elementToBeClickable(masterCardOption)).click();
        return this;
    }

    public CheckoutPage enterSafePayUsername(String username) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(safePayUsername)).clear();
        driver.findElement(safePayUsername).sendKeys(username);
        return this;
    }

    public CheckoutPage enterSafePayPassword(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(safePayPassword)).clear();
        driver.findElement(safePayPassword).sendKeys(password);
        return this;
    }

    public CheckoutPage checkSaveSafePay() {
        wait.until(ExpectedConditions.elementToBeClickable(saveSafePayCheckbox)).click();
        return this;
    }

    public CheckoutPage clickBackToShippingDetails() {
        wait.until(ExpectedConditions.elementToBeClickable(backToShippingDetailsLink)).click();

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(shippingDetailsStep)
        );

        return this;
    }

    public CheckoutPage clickPayNow() {
        wait.until(ExpectedConditions.elementToBeClickable(payNowButton)).click();
        return this;
    }



 // ======================================================
//  methods that needs multiple data fields to be filled
// ======================================================

    public CheckoutPage updateShippingDetails(String country ,String city, String address, String postalCode, String stateRegion) {

        selectCountry(country);
        enterCity(city);
        enterAddress(address);
        enterPostalCode(postalCode);
        enterStateRegion(stateRegion);

        return this;
    }

    public CheckoutPage payWithSafePay(
            String username,
            String password) {

        selectSafePay();
        enterSafePayUsername(username);
        enterSafePayPassword(password);

        return this;
    }
}
