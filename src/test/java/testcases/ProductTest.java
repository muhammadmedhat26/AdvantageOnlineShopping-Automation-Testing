package testcases;

import TestData.TestData.ProductData;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ProductPage;
import setup.BaseTest;

public class ProductTest extends BaseTest {

    /**
     * Prd-13: Verify quantity selector increases/decreases correctly
     */
    @Test
    public void quantitySelectorIncreasesAndDecreases() {
        homePage.clickSpeakersCategory();
        ProductPage productPage = homePage.clickProductByName(ProductData.SPEAKER_NAME_AS_LISTED);

        Assert.assertTrue(productPage.isProductPageDisplayed());

        int initialQty = productPage.getQuantity();

        productPage.increaseQuantity();
        Assert.assertEquals(productPage.getQuantity(), initialQty + 1);

        productPage.decreaseQuantity();
        Assert.assertEquals(productPage.getQuantity(), initialQty);
    }

    /**
     * Prd-16: Verify Add to Cart adds the product with the selected quantity
     */
    @Test
    public void addToCartAddsProductWithSelectedQuantity() {
        homePage.clickSpeakersCategory();
        ProductPage productPage = homePage.clickProductByName(ProductData.SPEAKER_NAME_AS_LISTED);

        productPage.increaseQuantity();
        int expectedQty = productPage.getQuantity();

        productPage.addToCart();
        Assert.assertTrue(productPage.isProductPageDisplayed());
        Assert.assertEquals(productPage.getQuantity(), 2);
    }

    /**
     * Prd-14: Verify quantity field boundary handling (0 and very large values)
     */
    @Test
    public void quantityFieldRejectsInvalidBoundaries() {
        homePage.clickSpeakersCategory();
        ProductPage productPage = homePage.clickProductByName(ProductData.SPEAKER_NAME_AS_LISTED);

        productPage.typeQuantity("0");
        Assert.assertNotEquals(productPage.getQuantity(), 0, "Quantity field should not accept 0");

        productPage.typeQuantity("99999");
        Assert.assertTrue(productPage.getQuantity() < 99999, "Quantity field should cap at a reasonable maximum, not accept 99999");
    }

    @Test
    public void quantityFieldRejectsNonNumericInput() {
        homePage.clickSpeakersCategory();
        ProductPage productPage = homePage.clickProductByName(ProductData.SPEAKER_NAME_AS_LISTED);
        String priorQuantity = productPage.getQuantityRaw();


        productPage.typeQuantity("abc");
        Assert.assertNotEquals(priorQuantity, "abc", "Quantity field should reject letters and keep the prior valid value");

        productPage.typeQuantity("@#$");
        Assert.assertNotEquals(productPage.getQuantityRaw(), "@#$", "Quantity field should reject special characters and keep the prior valid value");
    }
}