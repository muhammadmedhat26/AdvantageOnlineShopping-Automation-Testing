package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.CheckoutPage;
import setup.BaseTest;
import TestData.TestData;


public class CheckoutTest extends BaseTest {

    @Test(groups = {"requiresLogin", "checkoutTests"})
    public void userCanNavigateToEditShippingDetails() {

        checkoutPage.clickEditShippingDetails();

        Assert.assertTrue(
                checkoutPage.isEditShippingDetailsFormDisplayed(),
                "Edit Shipping Details form should be displayed"
        );
    }


    @Test(groups = {"requiresLogin", "checkoutTests"})
    public void userCanEditShippingDetails() {

        checkoutPage.clickEditShippingDetails();

        checkoutPage.updateShippingDetails(
                TestData.COUNTRY,
                TestData.CITY,
                TestData.ADDRESS,
                TestData.POSTAL_CODE,
                TestData.STATE_REGION
        );

        checkoutPage.clickEditShippingNext();//must click 3 times (bug)

        Assert.assertTrue(
                checkoutPage.isPaymentMethodSectionDisplayed(),
                "Payment Method section should be displayed"
        );
    }

    @Test(groups = {"requiresLogin", "checkoutTests"})
    public void userCanNavigateBackToShippingDetails() {

        checkoutPage.clickEditShippingDetails();
        Assert.assertTrue(
                checkoutPage.isEditShippingDetailsFormDisplayed(),
                "Edit Shipping Details form should be displayed"
        );
        checkoutPage.clickEditShippingBack();
        System.out.println("Reached back");

        Assert.assertTrue(
                checkoutPage.isShippingDetailsStepDisplayed(),
                "User should be navigated back to shipping details"
        );
    }

    @Test(groups = {"requiresLogin", "checkoutTests"})
    public void userCanNavigateToPaymentMethod() {

        checkoutPage.clickNext();

        Assert.assertTrue(
                checkoutPage.isPaymentMethodSectionDisplayed(),
                "Payment Method section should be displayed"
        );
    }

    @Test(groups = {"requiresLogin", "checkoutTests"})
    public void userCanEnterSafePayCredentials() {

        checkoutPage.clickNext();

        checkoutPage.enterSafePayUsername(
                TestData.SAFEPAY_USERNAME
        );

        checkoutPage.enterSafePayPassword(
                TestData.SAFEPAY_PASSWORD
        );

        Assert.assertEquals(
                checkoutPage.getSafePayUsername(),
                "safePayUser"
        );

        Assert.assertEquals(
                checkoutPage.getSafePayPassword(),
                "SafePay123"
        );

    }

    @Test(groups = {"requiresLogin", "checkoutTests"})
    public void userCanEnterMasterCardDetails() {
        checkoutPage.clickNext();
        System.out.println("Reached Payment Method section");
        checkoutPage.selectMasterCard();
        System.out.println("MasterCard selected");

        checkoutPage.enterMasterCardDetails(
                TestData.CARD_NUMBER,
                TestData.CVV,
                TestData.EXPIRATION_MONTH,
                TestData.EXPIRATION_YEAR,
                TestData.CARD_HOLDER_NAME
        );
        System.out.println("MasterCard details entered");
        Assert.assertEquals(
                checkoutPage.getCardNumber(),
                TestData.CARD_NUMBER
        );

        Assert.assertEquals(
                checkoutPage.getCvvNumber(),
                TestData.CVV
        );

        Assert.assertEquals(
                checkoutPage.getCardHolderName(),
                TestData.CARD_HOLDER_NAME
        );


    }

    @Test(groups = {"requiresLogin", "checkoutTests"})
    public void userCanPlaceOrderUsingSafePay() {

        checkoutPage.clickNext();

        checkoutPage.enterSafePayUsername(TestData.SAFEPAY_USERNAME);
        checkoutPage.enterSafePayPassword(TestData.SAFEPAY_PASSWORD);

        checkoutPage.clickPayNowSafePay();

        Assert.assertTrue(
                checkoutPage.isOrderConfirmationDisplayed(),
                "Order confirmation should be displayed"
        );

    }
        @Test(groups = {"requiresLogin", "checkoutTests"})
        public void userCanPlaceOrderUsingMasterCard () {

            checkoutPage.clickNext();

            checkoutPage.selectMasterCard();
            checkoutPage.enterMasterCardDetails(
                    TestData.CARD_NUMBER,
                    TestData.CVV,
                    TestData.EXPIRATION_MONTH,
                    TestData.EXPIRATION_YEAR,
                    TestData.CARD_HOLDER_NAME
            );

            checkoutPage.clickPayNowMasterCard();

            Assert.assertTrue(
                    checkoutPage.isOrderConfirmationDisplayed(),
                    "Order confirmation should be displayed"
            );
        }

    }

