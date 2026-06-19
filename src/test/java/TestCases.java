

import TestData.TestData;
import pages.*;
import setup.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCases extends BaseTest {

    @Test
    public void successfulRegistration() {
        System.out.println("Starting test: successfulRegistration");

        homePage.clickUserIcon();

        homePage.waitForLoginPopup();

        homePage.clickCreateAccount();

        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.registerNewUser();

        Assert.assertTrue(homePage.isUserLoggedIn(TestData.USERNAME),
                "Registration failed - user not logged in");

        System.out.println("Test successfulRegistration completed");
    }
}
