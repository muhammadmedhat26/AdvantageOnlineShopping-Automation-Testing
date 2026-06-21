package testcases;

import TestData.TestData;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;
import setup.BaseTest;

import static TestData.TestData.PASSWORD;
import static TestData.TestData.USERNAME;

public class LoginTest extends BaseTest {

    @Test
    public void userCanLoginSuccessfully() {
        LoginPage loginPage = new LoginPage(driver);

        homePage.clickUserIcon();
        homePage.waitForLoginPopup();
        loginPage.login(USERNAME, PASSWORD);

        Assert.assertTrue(homePage.isUserLoggedIn(USERNAME));
    }

    @Test(dataProvider = "invalidLoginData")
    public void userCannotLoginWithInvalidCredentials(String username, String password) {
        LoginPage loginPage = new LoginPage(driver);

        homePage.clickUserIcon();
        homePage.waitForLoginPopup();
        loginPage.enterUsername(username)
                .enterPassword(password)
                .clickSignInExpectingFailure();

        Assert.assertEquals(
                loginPage.getErrorMessage(),
                "Incorrect user name or password."
        );
    }

    @DataProvider
    public Object[][] invalidLoginData() {
        return new Object[][]{
                {USERNAME, TestData.INVALID_PASSWORD},
                {TestData.INVALID_USERNAME, PASSWORD},
                {TestData.INVALID_USERNAME, TestData.INVALID_PASSWORD}
        };
    }
}