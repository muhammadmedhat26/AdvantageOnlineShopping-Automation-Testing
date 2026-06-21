package testcases;

import TestData.TestData.ProductData;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.LoginPage;
import pages.ProductPage;
import setup.BaseTest;

import static TestData.TestData.PASSWORD;
import static TestData.TestData.USERNAME;

public class CartTest extends BaseTest {

    @Test
    public void userCanOpenCart() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(USERNAME, PASSWORD);

        CartPage cartPage = new CartPage(driver);
        cartPage.openCart();

        Assert.assertTrue(
                driver.getCurrentUrl().contains("shoppingCart")
        );
    }

    @Test
    public void userCanEditProductQuantityFromCart() {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(USERNAME, PASSWORD);

        CartPage cartPage = new CartPage(driver);
        cartPage.openCart();

        ProductPage productPage =
                cartPage.clickEditProduct(
                        TestData.TestData.ProductData.SPEAKER_NAME
                );

        Assert.assertTrue(productPage.isProductPageDisplayed());

        Assert.assertEquals(
                productPage.getProductName(),
                TestData.TestData.ProductData.SPEAKER_NAME
        );

        int beforeEdit = productPage.getQuantity();

        productPage.increaseQuantity();

        int afterEdit = productPage.getQuantity();

        Assert.assertEquals(afterEdit, beforeEdit + 1);

        productPage.addToCart();

        CartPage updatedCartPage = new CartPage(driver);
        updatedCartPage.openCart();

    }
}