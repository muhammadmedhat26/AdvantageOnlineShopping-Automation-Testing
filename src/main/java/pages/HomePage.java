package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage extends BasePage {

    private By userIcon = By.id("menuUser");
    private By createAccountLink = By.xpath("//a[contains(text(),'CREATE NEW ACCOUNT')]");
    private By speakersCategory = By.id("speakersTxt");
    private By cartIcon = By.id("menuCart");
    private By loginButton = By.id("sign_in_btn");
    private By myAccountLabel = By.xpath("(//label[contains(text(),'My account')])[2]");
    private By deleteAccountButton = By.xpath("//div[contains(text(),'Delete Account')]");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void clickUserIcon() {
        System.out.println("Clicking user icon");
        wait.until(ExpectedConditions.visibilityOfElementLocated(userIcon));
        driver.findElement(userIcon).click();
    }

    public void waitForLoginPopup() {
        System.out.println("Waiting for login popup to appear");
        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(d -> driver.findElement(loginButton).isDisplayed() && driver.findElement(createAccountLink).isDisplayed());
        //waitForPopupsToDisappear();
    }

    public void clickCreateAccount() {
        System.out.println("Clicking create account link");
        waitForLoaderToDisappear();
        wait.until(
                ExpectedConditions.elementToBeClickable(createAccountLink)
        ).click();
    }

    public void clickSpeakersCategory() {
        System.out.println("Clicking speakers category");
        waitForLoaderToDisappear();
        driver.findElement(speakersCategory).click();
    }

    public void clickCartIcon() {
        System.out.println("Clicking cart icon");
        waitForLoaderToDisappear();
        driver.findElement(cartIcon).click();
    }

    public void navigateToMyAccount() {
        System.out.println("Clicking My Account");
        waitForLoaderToDisappear();
        driver.findElement(userIcon).click();
        driver.findElement(myAccountLabel).click();
    }

    public void clickDeleteAccount() {
        waitForLoaderToDisappear();
        driver.findElement(deleteAccountButton).click();
    }

    public boolean isUserLoggedIn(String username) {
        waitForLoaderToDisappear();
        try {
            WebElement userElement = new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//*[@id='menuUserLink']/span[contains(.,'" + username + "')]")
                    ));
            return userElement.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}