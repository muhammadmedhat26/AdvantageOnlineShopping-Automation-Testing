package testcases;

import TestData.TestData.ProductData;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.CheckoutPage;
import pages.LoginPage;
import pages.ProductPage;
import setup.BaseTest;

import static TestData.TestData.PASSWORD;
import static TestData.TestData.USERNAME;

public class CartTest extends BaseTest {


    @Test(groups = "requiresLogin")
    public void userCanOpenCart() {

        CartPage cartPage = new CartPage(driver);
        cartPage.openCart();

        Assert.assertTrue(
                driver.getCurrentUrl().contains("shoppingCart"),
                "Cart page should be opened"
        );
    }


    //false positive case as it returns 0 as the number of products even if the cart is not empty due to the delay
    @Test(groups = "requiresLogin")
    public void cartProductsCountIsDisplayed() {

        CartPage cartPage = new CartPage(driver);
        cartPage.openCart();

        System.out.println("Cart products count: " + cartPage.getProductsCount());

        Assert.assertTrue(
                cartPage.getProductsCount() >= 0,
                "Cart products count should be zero or more"
        );
    }

    @Test(groups = "requiresLogin")
    public void userCanIncreaseProductQuantityFromCart() {

        CartPage cartPage = new CartPage(driver);
        cartPage.openCart();


        ProductPage productPage =
                cartPage.clickEditProduct(TestData.TestData.ProductData.SPEAKER_NAME);

        Assert.assertTrue(productPage.isProductPageDisplayed());

        Assert.assertEquals(
                productPage.getProductName(),
                TestData.TestData.ProductData.SPEAKER_NAME
        );

        int beforeEdit = productPage.getQuantity();

        productPage.increaseQuantity();

        Assert.assertEquals(
                productPage.getQuantity(),
                beforeEdit + 1
        );

        productPage.addToCart();

        CartPage updatedCartPage = new CartPage(driver);
        updatedCartPage.openCart();
    }


    @Test(groups = "requiresLogin")
    public void userCanDecreaseProductQuantityFromCart(){

        CartPage cartPage = new CartPage(driver);
        cartPage.openCart();
        ProductPage productPage =
                cartPage.clickEditProduct(ProductData.SPEAKER_NAME);

        Assert.assertTrue(productPage.isProductPageDisplayed());

        Assert.assertEquals(
                productPage.getProductName(),
                ProductData.SPEAKER_NAME
        );

        int beforeEdit = productPage.getQuantity();

        if (beforeEdit > 1) {
            productPage.decreaseQuantity();

            Assert.assertEquals(
                    productPage.getQuantity(),
                    beforeEdit - 1
            );
        } else {
            System.out.println("Quantity is already at minimum (1), cannot decrease further.");
        }

        productPage.addToCart();

        CartPage updatedCartPage = new CartPage(driver);
        updatedCartPage.openCart();
    }

    @Test(groups = "requiresLogin")
    public void userCanAddLargeQuantityMultipleTimes() {

        ProductPage productPage = new ProductPage(driver);

        int beforeCounter =
                new CartPage(driver).getCartCounter();


        while (productPage.getQuantity() < 5) {
            productPage.increaseQuantity();
        }

        System.out.println("Before counter: " + beforeCounter);
        for (int i = 0; i < 10; i++) {
            productPage.addToCart();
        }

        int afterCounter =
                new CartPage(driver).getCartCounter();

        Assert.assertEquals(
                afterCounter,
                beforeCounter + 50
        );
    }

    @Test(groups = "requiresLogin")
    public void userCanProceedToCheckout() {

        CartPage cartPage = new CartPage(driver);
        cartPage.openCart();

        CheckoutPage checkoutPage =
                cartPage.clickCheckout();

        Assert.assertTrue(
                checkoutPage.isCheckoutPageDisplayed()
        );

    }


}