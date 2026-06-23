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
    private By editShippingNextButton = By.cssSelector("sec-sender[a-value='NEXT']");
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
    private By safePayUsername = By.name("safepay_username");
    private By safePayPassword = By.name("safepay_password");
    private By payNowButton = By.id("pay_now_btn_SAFEPAY");

    private By masterCardOption = By.xpath("//div[contains(@class,'imgRadioButton')][2]");
    private By cardNumberInput = By.name("card_number");
    private By cvvNumberInput = By.name("cvv_number");
    private By expirationMonthDropdown = By.xpath("//sec-view[@a-hint='MM']//select");
    private By expirationYearDropdown = By.xpath("//sec-view[@a-hint='YYYY']//select");
    private By cardHolderNameInput = By.name("cardholder_name");
    private By saveCardCheckbox = By.name("save_master_credit");
    private By payNowMasterCardButton = By.id("pay_now_btn_ManualPayment");
    private By editMasterCardLink =
            By.xpath("//label[normalize-space()='Edit']");


    private By backToShippingDetailsLink = By.xpath("//a[contains(normalize-space(),'Back to shipping details')]");
    private By saveSafePayCheckbox = By.name("save_safepay");

//==========================================
//tracking receipt
//==========================================
    private By orderNumberMessage =
            By.xpath("//*[contains(normalize-space(),'Your order number')]");

    private By trackingNumberMessage =
            By.xpath("//*[contains(normalize-space(),'Your tracking number')]");

    private By orderSummaryTitle =
            By.xpath("//*[contains(normalize-space(),'Order Summary')]");


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

    public boolean isEditShippingDetailsFormDisplayed() {

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        editShippingDetailsForm
                )
        ).isDisplayed();
    }
    public CheckoutPage clickEditShippingDetails() {
        wait.until(ExpectedConditions.elementToBeClickable(editShippingDetailsLink)).click();

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(editShippingDetailsForm)
        );

        return this;
    }

    public CheckoutPage clickNext() {

        wait.until(ExpectedConditions.elementToBeClickable(nextButton))
                .click();

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        safePayUsername
                )
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

        waitForLoaderToDisappear();

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(safePayOption)
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


// ======================================================//
//                  payment methods                      //
// ======================================================//


// ======================================================
// safepay payment actions
// ======================================================

    public CheckoutPage selectSafePay() {
        wait.until(ExpectedConditions.elementToBeClickable(safePayOption)).click();
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

    public CheckoutPage clickPayNowSafePay() {
        wait.until(ExpectedConditions.elementToBeClickable(payNowButton)).click();
        return this;
    }

// ======================================================
// master card payment actions
// ======================================================

    public CheckoutPage selectMasterCard() {
        wait.until(ExpectedConditions.elementToBeClickable(masterCardOption)).click();
        return this;
    }

    public CheckoutPage enterCardNumber(String cardNumber) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(cardNumberInput)).clear();
        driver.findElement(cardNumberInput).sendKeys(cardNumber);
        return this;
    }

    public CheckoutPage enterCvvNumber(String cvv) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(cvvNumberInput)).clear();
        driver.findElement(cvvNumberInput).sendKeys(cvv);
        return this;
    }

    public CheckoutPage selectExpirationMonth(String month) {
        Select select = new Select(
                wait.until(ExpectedConditions.visibilityOfElementLocated(expirationMonthDropdown))
        );
        select.selectByVisibleText(month);
        return this;
    }

    public CheckoutPage selectExpirationYear(String year) {
        Select select = new Select(
                wait.until(ExpectedConditions.visibilityOfElementLocated(expirationYearDropdown))
        );
        select.selectByVisibleText(year);
        return this;
    }

    public CheckoutPage enterCardHolderName(String name) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(cardHolderNameInput)).clear();
        driver.findElement(cardHolderNameInput).sendKeys(name);
        return this;
    }


    public CheckoutPage clickPayNowMasterCard() {
        wait.until(ExpectedConditions.elementToBeClickable(payNowMasterCardButton)).click();
        return this;
    }

    public CheckoutPage clickEditMasterCard() {
        wait.until(ExpectedConditions.elementToBeClickable(editMasterCardLink)).click();
        return this;

    }
    public CheckoutPage openMasterCardForm() {

        if (!driver.findElements(editMasterCardLink).isEmpty()) {

            wait.until(
                    ExpectedConditions.elementToBeClickable(editMasterCardLink)
            ).click();
        }

        return this;
    }

    public boolean isOrderConfirmationDisplayed() {

        wait.until(ExpectedConditions.visibilityOfElementLocated(orderNumberMessage));
        wait.until(ExpectedConditions.visibilityOfElementLocated(trackingNumberMessage));
        wait.until(ExpectedConditions.visibilityOfElementLocated(orderSummaryTitle));

        return true;
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

    public CheckoutPage enterMasterCardDetails(
            String cardNumber,
            String cvv,
            String month,
            String year,
            String cardHolderName) {


        selectMasterCard();
        openMasterCardForm();
        enterCardNumber(cardNumber);
        enterCvvNumber(cvv);
        selectExpirationMonth(month);
        selectExpirationYear(year);
        enterCardHolderName(cardHolderName);

        return this;
    }


    //================================================
    //getters
    //================================================
    public String getSafePayUsername() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(safePayUsername)
        ).getAttribute("value");
    }

    public String getSafePayPassword() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(safePayPassword)
        ).getAttribute("value");
    }

    public String getCardNumber() {
        return driver.findElement(cardNumberInput)
                .getAttribute("value");
    }

    public String getCvvNumber() {
        return driver.findElement(cvvNumberInput)
                .getAttribute("value");
    }

    public String getCardHolderName() {
        return driver.findElement(cardHolderNameInput)
                .getAttribute("value");
    }
}
