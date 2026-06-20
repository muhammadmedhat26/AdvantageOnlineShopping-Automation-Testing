package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import setup.BaseTest;

public class LoginTest extends BaseTest {

    @Test
    public void userCanLoginSuccessfully() throws InterruptedException {

        homePage.clickUserIcon();
        homePage.waitForLoginPopup();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("SalmaMizar", "123456Ss");
        Assert.assertTrue(homePage.isUserLoggedIn("SalmaMizar"),
                "Login failed - user not logged in");
    }
}