package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.JavascriptExecutor;

public class CartPage extends BasePage {

    private By cartIcon = By.id("shoppingCartLink");
    private By productNames = By.cssSelector("label.roboto-regular.productName");
    private By checkoutButton = By.id("checkOutButton");

    public CartPage(WebDriver driver) {
        super(driver);
    }
    public CartPage openCart() {

        waitForPopupsToDisappear();
        waitForLoaderToDisappear();

        wait.until(
                ExpectedConditions.elementToBeClickable(cartIcon)
        ).click();

        wait.until(
                ExpectedConditions.urlContains("shoppingCart")
        );

        return this;
    }

    public int getProductsCount() {
        return driver.findElements(productNames).size();
    }

    public boolean isProductDisplayed(String productName) {
        By product = By.xpath(
                "//label[normalize-space()='" + productName + "']"
        );

        return !driver.findElements(product).isEmpty();
    }

    public CartPage removeProduct(String productName) {
        int beforeCount = getProductsCount();

        By removeButton = By.xpath(
                "//label[normalize-space()='" + productName + "']" +
                        "/ancestor::tr//a[contains(@class,'remove')]"
        );

        wait.until(ExpectedConditions.elementToBeClickable(removeButton)).click();

        wait.until(driver ->
                !isProductDisplayed(productName)
                        && getProductsCount() == beforeCount - 1
        );

        return this;
    }

    public ProductPage clickEditProduct(String productName) {
        By editButton = By.xpath(
                "//label[normalize-space()='" + productName + "']" +
                        "/ancestor::tr//a[contains(@class,'edit')]"
        );


        wait.until(ExpectedConditions.elementToBeClickable(editButton)).click();

        return new ProductPage(driver);
    }

    public CheckoutPage clickCheckout() {
        wait.until(ExpectedConditions.elementToBeClickable(checkoutButton)).click();
        return new CheckoutPage(driver);
    }
}