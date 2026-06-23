package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductPage extends BasePage {

    private By productName = By.cssSelector("#Description h1");
    private By addToCartButton = By.name("save_to_cart");
    private By quantityInput = By.name("quantity");
    private By plusButton = By.cssSelector("div.plus");
    private By minusButton = By.cssSelector("div.minus");

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public boolean isProductPageDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(productName)).isDisplayed();
    }

    public String getProductName() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(productName)).getText().trim();
    }

    public int getQuantity() {
        String quantityValue = wait.until(ExpectedConditions.visibilityOfElementLocated(quantityInput)).getAttribute("value");
        System.out.println("Current quantity value: " + quantityValue);
        try {
            return Integer.parseInt(quantityValue);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public String getQuantityRaw() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(quantityInput)).getAttribute("value");
    }

    public ProductPage increaseQuantity() {
        int before = getQuantity();

        wait.until(ExpectedConditions.elementToBeClickable(plusButton)).click();
        System.out.println("Clicked plus button to increase quantity +1");
        wait.until(driver -> getQuantity() == before + 1);

        return this;
    }

    public ProductPage decreaseQuantity() {
        int before = getQuantity();

        if (before > 1) {
            wait.until(ExpectedConditions.elementToBeClickable(minusButton)).click();
            wait.until(driver -> getQuantity() == before - 1);
        }

        return this;
    }

    public ProductPage addToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton)).click();
        waitForLoaderToDisappear();
        waitForPopupsToDisappear();
        return this;
    }


    public ProductPage typeQuantity(String value) {
        var field = wait.until(ExpectedConditions.visibilityOfElementLocated(quantityInput));
        System.out.println("Typing quantity value: " + value);
        field.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        field.sendKeys(Keys.DELETE);
        field.sendKeys(value);
        return this;
    }


}