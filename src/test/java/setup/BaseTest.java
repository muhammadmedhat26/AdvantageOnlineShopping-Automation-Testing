package setup;

import TestData.TestData;
import factory.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.*;

import static TestData.TestData.PASSWORD;
import static TestData.TestData.USERNAME;

public class BaseTest {

    protected WebDriver driver;
    protected HomePage homePage;
    protected CheckoutPage checkoutPage;



    protected void ensureRequiredProductsExistInCart() {

        homePage.clickSpeakersCategory();


        ProductPage speaker =
                homePage.clickProductByName(TestData.ProductData.SPEAKER_NAME_AS_LISTED);

        while (speaker.getQuantity() < 4) {
            speaker.increaseQuantity();
        }

        speaker.addToCart();



    }


    @BeforeMethod(dependsOnMethods = "loginUserBeforeTest", onlyForGroups = {"cartTests", "checkoutTests"})
    public void prepareCart() {
        ensureRequiredProductsExistInCart();
    }


    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        driver = DriverFactory.initializeDriver();
        driver.get("https://advantageonlineshopping.com/#/");
        homePage = new HomePage(driver);
    }

    @BeforeMethod(dependsOnMethods = "setUp", onlyForGroups = "requiresLogin")
    public void loginUserBeforeTest() {

        homePage.clickUserIcon();
        homePage.waitForLoginPopup();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(USERNAME, PASSWORD);
    }

    @BeforeMethod(dependsOnMethods = "loginUserBeforeTest", onlyForGroups = "checkoutTests")
    public void navigateToCheckoutPage() {
        CartPage cartPage = new CartPage(driver);
        cartPage.openCart();

        checkoutPage = cartPage.clickCheckout();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}