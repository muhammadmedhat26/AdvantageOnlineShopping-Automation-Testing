package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(css = "[id='menuUser']")
    private WebElement userIcon;

    @FindBy(css = "[name='username']")
    private WebElement usernameInput;

    @FindBy(css = "[name='password']")
    private WebElement passwordInput;

    @FindBy(css ="[id='sign_in_btn']")
    private WebElement signInButton;


    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage openLoginPopup() {
        userIcon.click();
        return this;
    }

    public LoginPage enterUsername(String username) {
        usernameInput.sendKeys(username);
        return this;
    }

    public LoginPage enterPassword(String password) {
        passwordInput.sendKeys(password);
        return this;
    }

    public HomePage clickSignIn() {
        signInButton.click();
        return new HomePage(driver);
    }

    public HomePage login(String username, String password) {
        openLoginPopup();
        enterUsername(username);
        enterPassword(password);
        return clickSignIn();
    }
}