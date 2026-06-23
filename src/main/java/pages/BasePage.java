package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected WebDriverWait slowWait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.slowWait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    protected void waitForLoaderToDisappear() {
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.loader")));
        } catch (Exception ignored) {
        }
    }

    protected void waitForPopupsToDisappear() {
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.PopUp")));
        } catch (Exception ignored) {
        }
    }

    protected void scrollToElement(WebElement element) {
        Actions actions = new Actions(driver);
        actions.scrollToElement(element).perform();
    }
}
