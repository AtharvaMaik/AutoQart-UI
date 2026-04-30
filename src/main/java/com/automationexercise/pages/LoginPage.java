package com.automationexercise.pages;

import org.openqa.selenium.By;

public class LoginPage extends BasePage {
    private final By loginEmail = By.cssSelector("[data-qa='login-email']");
    private final By loginPassword = By.cssSelector("[data-qa='login-password']");
    private final By loginButton = By.cssSelector("[data-qa='login-button']");
    private final By signupName = By.cssSelector("[data-qa='signup-name']");
    private final By signupEmail = By.cssSelector("[data-qa='signup-email']");
    private final By signupButton = By.cssSelector("[data-qa='signup-button']");
    private final By invalidLoginMessage = By.xpath("//*[contains(text(),'incorrect') or contains(text(),'incorrect!')]");
    private final By existingEmailMessage = By.xpath("//*[contains(text(),'Email Address already exist')]");
    private final By loginTitle = By.xpath("//h2[contains(.,'Login to your account')]");
    private final By signupTitle = By.xpath("//h2[contains(.,'New User Signup')]");

    public boolean isLoaded() {
        return isDisplayed(loginTitle) && isDisplayed(signupTitle);
    }

    public void login(String email, String password) {
        type(loginEmail, email);
        type(loginPassword, password);
        click(loginButton);
    }

    public void startSignup(String name, String email) {
        type(signupName, name);
        type(signupEmail, email);
        click(signupButton);
    }

    public boolean isInvalidLoginVisible() {
        return isDisplayed(invalidLoginMessage);
    }

    public boolean isExistingEmailMessageVisible() {
        return isDisplayed(existingEmailMessage);
    }

    public boolean isSignupStillVisible() {
        return isDisplayed(signupButton);
    }
}

