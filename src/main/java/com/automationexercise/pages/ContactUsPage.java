package com.automationexercise.pages;

import com.automationexercise.models.ContactMessageData;
import com.automationexercise.utils.ConfigReader;
import java.nio.file.Path;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;

public class ContactUsPage extends BasePage {
    private final By contactTitle = By.xpath("//h2[contains(.,'Contact Us')]");
    private final By name = By.cssSelector("[data-qa='name']");
    private final By email = By.cssSelector("[data-qa='email']");
    private final By subject = By.cssSelector("[data-qa='subject']");
    private final By message = By.cssSelector("[data-qa='message']");
    private final By upload = By.cssSelector("input[type='file']");
    private final By submit = By.cssSelector("[data-qa='submit-button']");
    private final By successMessage = By.cssSelector(".status.alert.alert-success");
    private final By homeButton = By.cssSelector(".btn.btn-success");

    public boolean isLoaded() {
        return isDisplayed(contactTitle);
    }

    public void submitContactForm(ContactMessageData contactMessageData) {
        type(name, contactMessageData.getName());
        type(email, contactMessageData.getEmail());
        type(subject, contactMessageData.getSubject());
        type(message, contactMessageData.getMessage());
        append(upload, Path.of(ConfigReader.getContactUploadPath()).toAbsolutePath().toString());
        click(submit);
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    public boolean isSuccessVisible() {
        return isDisplayed(successMessage);
    }

    public void clickHomeButton() {
        click(homeButton);
    }
}

