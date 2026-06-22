package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.JavascriptExecutor;

public class CartPage extends BasePage {

    private By cartIcon = By.id("menuCart");
    private By productNames = By.cssSelector("label.roboto-regular.productName");
    private By checkoutButton = By.id("checkOutButton");
    private By cartCounter = By.cssSelector("span.cart.ng-binding");

    public int getCartCounter() {

        String count = wait.until(
                ExpectedConditions.visibilityOfElementLocated(cartCounter)
        ).getText().trim();

        return Integer.parseInt(count);
    }

    public CartPage(WebDriver driver) {

        super(driver);
    }

    public CartPage openCart() {

        waitForPopupsToDisappear();
        waitForLoaderToDisappear();

        wait.until(
                ExpectedConditions.elementToBeClickable(cartIcon)
        ).click();
        waitForLoaderToDisappear();

        wait.until(
                ExpectedConditions.urlContains("shoppingCart")
        );
        waitForLoaderToDisappear();
        waitForPopupsToDisappear();
        System.out.println(
                "Products count = " +
                        driver.findElements(productNames).size()
        );

        return this;
    }
    public CartPage waitForProductToLoad(String productName) {
        By product = By.xpath("//label[normalize-space()='" + productName + "']");

        wait.until(ExpectedConditions.visibilityOfElementLocated(product));

        return this;
    }

    public int getProductsCount() {

        waitForLoaderToDisappear();

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