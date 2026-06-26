package testcases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.ProductPage;
import setup.BaseTest;

public class HomeTest extends BaseTest {


    @Test
    public void userCanNavigateToTabletsCategory() {
        homePage.clickTabletsCategory();

        Assert.assertTrue(driver.getCurrentUrl().contains("Tablets"), "User should be navigated to the tablets category page");
    }

    @Test
    public void headerIconsAreFunctional() {

        homePage.clickSearchIcon();
        Assert.assertTrue(driver.findElement(By.id("autoComplete")).isDisplayed(), "Search input should appear after clicking search icon");

        homePage.clickUserIcon();

        homePage.waitForLoginPopup();

        homePage.quitLoginPopup();
        homePage.clickCartIcon();
        CartPage cartPage = new CartPage(driver);
        cartPage.openCart();
        Assert.assertTrue(driver.getCurrentUrl().contains("shoppingCart"), "Cart page should open after clicking cart icon");
    }

    @Test
    public void popularItemLinksToCorrectProduct() {
        String clickedItemName = "HP ELITEBOOK FOLIO";


        ProductPage productPage = homePage.clickEliteBookDetails();

        Assert.assertEquals(productPage.getProductName(), clickedItemName, "Popular item link should navigate to its own matching product page");
    }
}
