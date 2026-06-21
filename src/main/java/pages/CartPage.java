package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CartPage extends BasePage {

    private By catrIcon = By.id("menuCart");
    private By productName = By.cssSelector("label.roboto-regular.productName");
    private By checkoutButton = By.id("checkOutButton");
    private By removeButton = By.cssSelector("a.remove.red");


    public CartPage(WebDriver driver) {
        super(driver);
    }

    public CartPage openCart(){
        wait.until(ExpectedConditions.elementToBeClickable(catrIcon)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(productName));
        return this;
    }


    public String getProductName(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(productName))
                .getText().trim();
    }

    public boolean isProductDisplayedInCart(String expectedProductName) {
        return getProductName().equalsIgnoreCase(expectedProductName);
    }

    public CheckoutPage clickOnCheckoutButton(){
        wait.until(ExpectedConditions.elementToBeClickable(checkoutButton)).click();
        return new CheckoutPage(driver);
    }

    public CartPage removeProduct() {

        wait.until(
                ExpectedConditions.elementToBeClickable(removeButton)
        ).click();

        return this;
    }
}
