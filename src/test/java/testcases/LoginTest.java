package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import setup.BaseTest;

public class LoginTest extends BaseTest {

    @Test
    public void userCanLoginSuccessfully() throws InterruptedException {

        LoginPage loginPage = new LoginPage(driver);

        HomePage homePage =
                loginPage.login(
                        "SalmaMizar",
                        "123456Ss"
                );
        Thread.sleep(5000);

        Assert.assertTrue(
                homePage.isUserLoggedIn("SalmaMizar")
        );
    }
}