package com.automationexercise.pages;

import com.automationexercise.models.UserRegistrationData;
import org.openqa.selenium.By;

public class SignupPage extends BasePage {
    private final By accountInfoTitle = By.xpath("//b[contains(.,'Enter Account Information')]");
    private final By mrTitle = By.id("id_gender1");
    private final By mrsTitle = By.id("id_gender2");
    private final By password = By.cssSelector("[data-qa='password']");
    private final By days = By.cssSelector("[data-qa='days']");
    private final By months = By.cssSelector("[data-qa='months']");
    private final By years = By.cssSelector("[data-qa='years']");
    private final By newsletter = By.id("newsletter");
    private final By offers = By.id("optin");
    private final By firstName = By.cssSelector("[data-qa='first_name']");
    private final By lastName = By.cssSelector("[data-qa='last_name']");
    private final By company = By.cssSelector("[data-qa='company']");
    private final By address1 = By.cssSelector("[data-qa='address']");
    private final By address2 = By.cssSelector("[data-qa='address2']");
    private final By country = By.cssSelector("[data-qa='country']");
    private final By state = By.cssSelector("[data-qa='state']");
    private final By city = By.cssSelector("[data-qa='city']");
    private final By zipcode = By.cssSelector("[data-qa='zipcode']");
    private final By mobileNumber = By.cssSelector("[data-qa='mobile_number']");
    private final By createAccountButton = By.cssSelector("[data-qa='create-account']");
    private final By accountCreatedMessage = By.cssSelector("[data-qa='account-created']");
    private final By continueButton = By.cssSelector("[data-qa='continue-button']");

    public boolean isLoaded() {
        return isDisplayed(accountInfoTitle);
    }

    public void createAccount(UserRegistrationData userData) {
        if ("Mrs".equalsIgnoreCase(userData.getTitle())) {
            click(mrsTitle);
        } else {
            click(mrTitle);
        }
        type(password, userData.getPassword());
        selectByVisibleText(days, "10");
        selectByVisibleText(months, "May");
        selectByVisibleText(years, "1995");
        click(newsletter);
        click(offers);
        type(firstName, userData.getFirstName());
        type(lastName, userData.getLastName());
        type(company, userData.getCompany());
        type(address1, userData.getAddress1());
        type(address2, userData.getAddress2());
        selectByVisibleText(country, userData.getCountry());
        type(state, userData.getState());
        type(city, userData.getCity());
        type(zipcode, userData.getZipcode());
        type(mobileNumber, userData.getMobileNumber());
        click(createAccountButton);
    }

    public boolean isAccountCreatedVisible() {
        return isDisplayed(accountCreatedMessage);
    }

    public void clickContinue() {
        click(continueButton);
    }
}

