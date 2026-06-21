package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

    private By usernameInput = By.name("username");
    private By passwordInput = By.name("password");
    private By signInButton = By.id("sign_in_btn");
    private By signInErrorMessage = By.id("signInResultMessage");
    private By loggedInUserLink = By.id("menuUserLink");

    public LoginPage(WebDriver driver) {
        super(driver);
    }


    public LoginPage enterUsername(String username) {
        System.out.println("Entering username: " + username);
        driver.findElement(usernameInput).sendKeys(username);
        return this;
    }

    public LoginPage enterPassword(String password) {
        System.out.println("Entering password: " + password);
        driver.findElement(passwordInput).sendKeys(password);
        return this;
    }

    public void clickSignIn() {
        System.out.println("Clicking sign in button");
        driver.findElement(signInButton).click();
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickSignIn();
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