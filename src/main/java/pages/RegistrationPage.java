package pages;

import TestData.TestData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegistrationPage extends BasePage {

    private By usernameField = By.name("usernameRegisterPage");
    private By emailField = By.name("emailRegisterPage");
    private By passwordField = By.name("passwordRegisterPage");
    private By confirmPasswordField = By.name("confirm_passwordRegisterPage");
    private By agreeCheckbox = By.name("i_agree");
    private By registerButton = By.id("register_btn");

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    public void registerNewUser() {
        System.out.println("Registering new user");
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField));
        driver.findElement(usernameField).sendKeys(TestData.USERNAME);
        System.out.println("Entered username: " + TestData.USERNAME);

        driver.findElement(emailField).sendKeys(TestData.EMAIL);
        System.out.println("Entered email: " + TestData.EMAIL);

        driver.findElement(passwordField).sendKeys(TestData.PASSWORD);
        System.out.println("Entered password");

        driver.findElement(confirmPasswordField).sendKeys(TestData.PASSWORD);
        System.out.println("Confirmed password");

        driver.findElement(agreeCheckbox).click();
        System.out.println("Checked agreement checkbox");

        driver.findElement(registerButton).click();
        System.out.println("Clicked register button");
    }
}