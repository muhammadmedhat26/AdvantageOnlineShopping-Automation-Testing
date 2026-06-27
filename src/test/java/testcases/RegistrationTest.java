package testcases;

import TestData.TestData;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.RegistrationPage;
import setup.BaseTest;

public class RegistrationTest extends BaseTest {


    /* REG-01 : Verify valid registration*/
    @Test
    public void registrationSucceedsWithValidData() {
        homePage.clickUserIcon();
        homePage.waitForLoginPopup();
        homePage.clickCreateAccount();

        int randomNumber = new java.util.Random().nextInt(900);
        System.out.println(randomNumber);
        String randomUsername = TestData.USERNAME + randomNumber;

        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.registerNewValidUser(randomUsername);

        Assert.assertTrue(homePage.isUserLoggedIn(randomUsername), "Expected redirection to homepage/username shown top right");
        homePage.navigateToMyAccount();

        homePage.clickDeleteAccount();
        Assert.assertTrue(homePage.isUserLoggedOut(randomUsername), "Expected user to be logged out after account deletion");
    }


    /* REG-02: Verify registration with blank mandatory(*) field*/
    @Test
    public void registrationFailsWithBlankMandatoryFields() {
        homePage.clickUserIcon();
        homePage.waitForLoginPopup();
        homePage.clickCreateAccount();

        RegistrationPage registrationPage = new RegistrationPage(driver);

        registrationPage.registerWith("", TestData.EMAIL, TestData.PASSWORD, TestData.PASSWORD, true);

        Assert.assertTrue(registrationPage.isUsernameErrorDisplayed(), "Expected a required-field error on username, but none was shown");
        System.out.println("Username error: " + registrationPage.getUsernameRequiredError());

        Assert.assertFalse(registrationPage.isRegisterButtonEnabled(),
                "Expected Register button to remain disabled with a mandatory field blank");

//        Assert.assertTrue(registrationPage.isRegisterErrorShown(), "Expected Register button to show error underneath Registration Failed");
//
//        registrationPage.registerWith("TesterTT", "", TestData.PASSWORD, TestData.PASSWORD, true);
//        Assert.assertTrue(registrationPage.isEmailErrorDisplayed(), "Expected a required-field error on email, but none was shown");
//        System.out.println("Email error: " + registrationPage.getEmailError());
//
//        registrationPage.registerWith("TesterTT", TestData.EMAIL, "", TestData.PASSWORD, true);
//        Assert.assertTrue(registrationPage.isPasswordErrorDisplayed(), "Expected a required-field error on password, but none was shown");
//        System.out.println("Password error: " + registrationPage.getPasswordError());
//
//        registrationPage.registerWith("TesterTT", TestData.EMAIL, TestData.PASSWORD, "", true);
//        Assert.assertTrue(registrationPage.isConfirmPasswordErrorDisplayed(), "Expected a required-field error on confirm password, but none was shown");
//        System.out.println("Confirm Password error: " + registrationPage.getConfirmPasswordError());


    }

    /* REG-03: Verify registration with bad e-mail format* Expected error: "Your Email address isn't formatted correctly"*/
    @Test
    public void registrationFailsWithBadEmailFormat() {
        homePage.clickUserIcon();
        homePage.waitForLoginPopup();
        homePage.clickCreateAccount();

        RegistrationPage registrationPage = new RegistrationPage(driver);

        registrationPage.registerWith(TestData.USERNAME, "notanemail", TestData.PASSWORD, TestData.PASSWORD, true);

        Assert.assertEquals(registrationPage.getEmailFormatError(), "Your email address isn't formatted correctly");
    }


    /**
     * REG-09: Verify registration with an already-existing username** Expected error: "Username already exists" (verify exact wording in DevTools).
     */
    @Test
    public void registrationFailsWithExistingUsername() {
        homePage.clickUserIcon();
        homePage.waitForLoginPopup();
        homePage.clickCreateAccount();

        RegistrationPage registrationPage = new RegistrationPage(driver);

        registrationPage.registerWith(TestData.USERNAME, TestData.EMAIL, TestData.PASSWORD, TestData.PASSWORD, true);
        Assert.assertTrue(registrationPage.isUsernameExistingErrorDisplayed(), "Expected a 'username already exists' error, but none was shown");
    }

    /* REG-11: Verify "Already have an account?" link redirects to the sign-in form*/
    @Test
    public void alreadyHaveAccountLinkRedirectsToSignIn() {
        homePage.clickUserIcon();
        homePage.waitForLoginPopup();
        homePage.clickCreateAccount();

        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.clickAlreadyHaveAccount();

        Assert.assertTrue(registrationPage.isOnSignInForm(), "Expected to be redirected back to the sign-in form");
    }
}