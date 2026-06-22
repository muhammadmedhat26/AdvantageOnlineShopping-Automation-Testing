package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckoutPage extends BasePage {

    private By orderPaymentTitle =
            By.xpath("//h3[contains(.,'ORDER PAYMENT')]");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public boolean isCheckoutPageDisplayed() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(orderPaymentTitle)
        ).isDisplayed();
    }
}