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

        homePage.clickUserIcon();
        homePage.waitForLoginPopup();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(USERNAME, PASSWORD);

        Assert.assertTrue(
                homePage.isUserLoggedIn(USERNAME)
        );
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


    //UI issue (the creat account button is outside the visible screen)
    @Test
    public void userCanNavigateToRegistrationPage() {
        homePage.clickUserIcon();
        homePage.waitForLoginPopup();
        homePage.clickCreateAccount();

        System.out.println(driver.getCurrentUrl());

        Assert.assertTrue(
                driver.getCurrentUrl().contains("register"),
                "User should be navigated to registration page"
        );
    }

    @Test
    public void forgotPasswordLinkIsNotWorking() {
        homePage.clickUserIcon();
        homePage.waitForLoginPopup();

        String currentUrl = driver.getCurrentUrl();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickForgotPassword();

        Assert.assertNotEquals(driver.getCurrentUrl(), currentUrl, "Forgot password link should navigate to a reset password page,");
    }
}