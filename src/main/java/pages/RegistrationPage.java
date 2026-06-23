package pages;

import TestData.TestData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegistrationPage extends BasePage {

    private By usernameField = By.name("usernameRegisterPage");
    private By emailField = By.name("emailRegisterPage");
    private By passwordField = By.name("passwordRegisterPage");
    private By confirmPasswordField = By.name("confirm_passwordRegisterPage");
    private By agreeCheckbox = By.name("i_agree");
    private By registerButton = By.id("register_btn");


    private By usernameRequiredError = By.xpath("(//label[contains(text(),'Username field is required')])[2]");
    private By usernameExistsError = By.xpath("//label[contains(text(),'User name already exists')]");
    private By emailError = By.xpath("//label[contains(text(),'Email field is required')]");
    private By emailFormatError = By.xpath("//label[contains(text(),\"Your email address isn't formatted correctly\")]");
    private By passwordError = By.xpath("//label[contains(text(),'Password field is required')]");
    private By confirmPasswordError = By.xpath("//label[contains(text(),'Confirm password field is required')]");
//    private By registerError = By.cssSelector("label.center.block.smollMargin[data-ng-show='!registerSuccess']");

    private By alreadyHaveAccountLink = By.xpath("//a[contains(text(),'ALREADY HAVE')]");
    private By signInUsernameField = By.name("username");


    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    public void registerNewValidUser(String randomUsername) {
        registerWith(randomUsername, TestData.EMAIL, TestData.PASSWORD, TestData.PASSWORD, true);
    }

    public RegistrationPage registerWith(String username, String email, String password,
                                         String confirmPassword, boolean agreeToTerms) {
        wait.until(ExpectedConditions.elementToBeClickable(usernameField));

//        driver.findElement(usernameField).clear();
//        driver.findElement(emailField).clear();
//        driver.findElement(passwordField).clear();
//        driver.findElement(confirmPasswordField).clear();
//
//        Actions actions = new Actions(driver);
//        actions.scrollToElement(driver.findElement(usernameField)).perform();
        driver.findElement(usernameField).click();

        if (username != null && !username.isEmpty()) {
            driver.findElement(usernameField).sendKeys(username);
            System.out.println("Entered username: " + username);
        }

        if (email != null && !email.isEmpty()) {
            driver.findElement(emailField).sendKeys(email);
            System.out.println("Entered email: " + email);
        }

        if (password != null && !password.isEmpty()) {
            driver.findElement(passwordField).sendKeys(password);
            System.out.println("Entered password");
        }

        if (confirmPassword != null && !confirmPassword.isEmpty()) {
            driver.findElement(confirmPasswordField).sendKeys(confirmPassword);
            System.out.println("Confirmed password");
        }

        if (agreeToTerms && !isCheckboxChecked()) {
            WebElement checkbox = driver.findElement(agreeCheckbox);
            Actions actions = new Actions(driver);
            actions.scrollToElement(driver.findElement(agreeCheckbox)).scrollByAmount(0, 100).perform();

            wait.until(ExpectedConditions.elementToBeClickable(agreeCheckbox));
            actions.moveToElement(checkbox).click().perform();
            System.out.println("Checked agreement checkbox ");
        }

        try {
//            wait.until(ExpectedConditions.elementToBeClickable(registerButton));
            clickRegister();
        } catch (Exception e) {
            System.out.println("Register button not clickable: " + e.getMessage());
        }


        return this;
    }

    public RegistrationPage clickRegister() {
        driver.findElement(registerButton).click();
        System.out.println("Clicked register button");
        return this;
    }

    public boolean isRegisterButtonEnabled() {
        return driver.findElement(registerButton).isEnabled();
    }

//    public boolean isRegisterErrorShown() {
//        wait.until(ExpectedConditions.visibilityOfElementLocated(registerError));
//        System.out.println("Register error is displayed: " + driver.findElement(registerError).getText());
//        return driver.findElement(registerError).isDisplayed();
//    }

    // ── Error message getters ──────────────────────────────────────────────

    public String getUsernameRequiredError() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(usernameRequiredError))
                .getText().trim();
    }

    public String getEmailError() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(emailError))
                .getText().trim();
    }

    public String getEmailFormatError() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(emailFormatError))
                .getText().trim();
    }

    public String getPasswordError() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(passwordError))
                .getText().trim();
    }

    public String getConfirmPasswordError() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(confirmPasswordError))
                .getText().trim();
    }

    public boolean isUsernameErrorDisplayed() {
        return !driver.findElements(usernameRequiredError).isEmpty()
                && driver.findElement(usernameRequiredError).isDisplayed();
    }

    public boolean isUsernameExistingErrorDisplayed() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameExistsError));
        return !driver.findElements(usernameExistsError).isEmpty()
                && driver.findElement(usernameExistsError).isDisplayed();
    }

    public boolean isEmailErrorDisplayed() {
        return !driver.findElements(emailError).isEmpty()
                && driver.findElement(emailError).isDisplayed();
    }

    public boolean isPasswordErrorDisplayed() {
        return !driver.findElements(passwordError).isEmpty()
                && driver.findElement(passwordError).isDisplayed();
    }

    public boolean isConfirmPasswordErrorDisplayed() {
        return !driver.findElements(confirmPasswordError).isEmpty()
                && driver.findElement(confirmPasswordError).isDisplayed();
    }

    public boolean isCheckboxChecked() {
        return driver.findElement(agreeCheckbox).isSelected();
    }

    public void clickAlreadyHaveAccount() {
        wait.until(ExpectedConditions.elementToBeClickable(alreadyHaveAccountLink)).click();
        System.out.println("Clicked 'Already have an account?' link");
    }

    /*** Confirms the redirect landed back on the sign-in form by checking* that the sign-in username field is visible.*/
    public boolean isOnSignInForm() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(signInUsernameField))
                    .isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}