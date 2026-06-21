package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

    private By userIcon = By.id("menuUser");
    private By usernameInput = By.name("username");
    private By passwordInput = By.name("password");
    private By signInButton = By.id("sign_in_btn");
    private By signInErrorMessage = By.id("signInResultMessage");
    private By loggedInUserLink = By.id("menuUserLink");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage openLoginPopup() {
        wait.until(ExpectedConditions.elementToBeClickable(userIcon)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameInput));
        return this;
    }

    public LoginPage enterUsername(String username) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameInput)).clear();
        driver.findElement(usernameInput).sendKeys(username);
        return this;
    }

    public LoginPage enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput)).clear();
        driver.findElement(passwordInput).sendKeys(password);
        return this;
    }

    public HomePage clickSignIn() {
        wait.until(ExpectedConditions.elementToBeClickable(signInButton)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(loggedInUserLink));
        return new HomePage(driver);
    }

    public HomePage login(String username, String password) {
        openLoginPopup();
        enterUsername(username);
        enterPassword(password);
        return clickSignIn();
    }

    public LoginPage clickSignInExpectingFailure() {
        wait.until(ExpectedConditions.elementToBeClickable(signInButton)).click();
        wait.until(ExpectedConditions.textToBePresentInElementLocated(
                signInErrorMessage,
                "Incorrect"
        ));
        return this;
    }

    public String getErrorMessage() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(signInErrorMessage)
        ).getText().trim();
    }
}