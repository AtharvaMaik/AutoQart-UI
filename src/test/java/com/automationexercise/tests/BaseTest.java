package com.automationexercise.tests;

import com.automationexercise.drivers.DriverFactory;
import com.automationexercise.factory.DriverManager;
import com.automationexercise.models.UserRegistrationData;
import com.automationexercise.pages.CartPage;
import com.automationexercise.pages.ContactUsPage;
import com.automationexercise.pages.HomePage;
import com.automationexercise.pages.LoginPage;
import com.automationexercise.pages.ProductsPage;
import com.automationexercise.pages.SignupPage;
import com.automationexercise.utils.ConfigReader;
import com.automationexercise.utils.RandomDataUtils;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public abstract class BaseTest {
    protected WebDriver driver;
    protected HomePage homePage;
    protected LoginPage loginPage;
    protected SignupPage signupPage;
    protected ProductsPage productsPage;
    protected CartPage cartPage;
    protected ContactUsPage contactUsPage;

    @BeforeMethod(alwaysRun = true)
    @Parameters({"browser"})
    public void setUp(@Optional("") String browser) {
        String resolvedBrowser = (browser == null || browser.isBlank()) ? ConfigReader.getBrowser() : browser;
        driver = DriverFactory.createDriver(resolvedBrowser);
        DriverManager.setDriver(driver);
        homePage = new HomePage();
        loginPage = new LoginPage();
        signupPage = new SignupPage();
        productsPage = new ProductsPage();
        cartPage = new CartPage();
        contactUsPage = new ContactUsPage();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        try {
            if (DriverManager.getDriver() != null) {
                DriverManager.getDriver().quit();
            }
        } finally {
            DriverManager.unload();
        }
    }

    protected UserRegistrationData buildUser() {
        UserRegistrationData user = new UserRegistrationData();
        String email = RandomDataUtils.uniqueEmail("sdet-fresher");
        user.setTitle("Mr");
        user.setName("Automation User");
        user.setEmail(email);
        user.setPassword(ConfigReader.getDefaultPassword());
        user.setFirstName("Automation");
        user.setLastName("Engineer");
        user.setCompany("QA Portfolio");
        user.setAddress1("123 Test Street");
        user.setAddress2("Suite 10");
        user.setCountry("India");
        user.setState("Karnataka");
        user.setCity("Bengaluru");
        user.setZipcode("560001");
        user.setMobileNumber("9999999999");
        return user;
    }

    protected UserRegistrationData createUserThroughUi() {
        UserRegistrationData user = buildUser();
        homePage.open().goToLogin();
        loginPage.startSignup(user.getName(), user.getEmail());
        signupPage.createAccount(user);
        signupPage.clickContinue();
        return user;
    }
}
