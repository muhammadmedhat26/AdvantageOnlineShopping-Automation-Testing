package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private By usernameInput = By.cssSelector("[name='username']");
    private By passwordInput = By.cssSelector("[name='password']");
    private By signInButton = By.cssSelector("[id='sign_in_btn']");


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
}