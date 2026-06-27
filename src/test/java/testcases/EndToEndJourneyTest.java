package testcases;

import TestData.TestData;
import TestData.TestData.ProductData;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.CheckoutPage;
import pages.ProductPage;
import pages.RegistrationPage;
import setup.BaseTest;

public class EndToEndJourneyTest extends BaseTest {

    @Test
    public void userCanRegisterBrowseAndCheckoutSuccessfully() {

        homePage.clickUserIcon();
        homePage.waitForLoginPopup();
        homePage.clickCreateAccount();

        int randomNumber = new java.util.Random().nextInt(900);
        String randomUsername = TestData.USERNAME + randomNumber;

        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.registerNewValidUser(randomUsername);

        Assert.assertTrue(
                homePage.isUserLoggedIn(randomUsername),
                "New user should be logged in after registration"
        );

        homePage.clickSpeakersCategory();
        ProductPage productPage = homePage.clickProductByName(ProductData.SPEAKER_NAME_AS_LISTED);

        productPage.addToCart();

        CartPage cartPage = new CartPage(driver);
        cartPage.openCart();
        CheckoutPage checkoutPage = cartPage.clickCheckout();

        Assert.assertTrue(
                checkoutPage.isCheckoutPageDisplayed(),
                "Checkout page should be displayed"
        );

        checkoutPage.clickNext();
        checkoutPage.enterSafePayUsername(TestData.SAFEPAY_USERNAME);
        checkoutPage.enterSafePayPassword(TestData.SAFEPAY_PASSWORD);
        checkoutPage.clickPayNowSafePay();

        Assert.assertTrue(checkoutPage.isOrderConfirmationDisplayed(), "Order confirmation should be displayed at the end of the journey");
        homePage.navigateToMyAccount();
        homePage.clickDeleteAccount();
        Assert.assertTrue(homePage.isUserLoggedOut(randomUsername), "Expected user to be logged out after account deletion");

    }
}