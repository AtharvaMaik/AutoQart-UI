package com.automationexercise.pages;

import com.automationexercise.utils.ConfigReader;
import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage {
    private final By header = By.id("header");
    private final By carousel = By.id("slider-carousel");
    private final By categoryPanel = By.id("accordian");
    private final By brandPanel = By.cssSelector(".brands_products");
    private final By featureItemsTitle = By.xpath("//h2[contains(.,'Features Items')]");
    private final By recommendedItemsTitle = By.xpath("//h2[contains(.,'recommended items') or contains(.,'Recommended Items')]");
    private final By subscriptionInput = By.id("susbscribe_email");
    private final By subscriptionButton = By.id("subscribe");
    private final By subscriptionSuccess = By.id("success-subscribe");
    private final By loggedInUser = By.xpath("//a[contains(.,'Logged in as')]");
    private final By deleteAccountLink = By.xpath("//a[contains(.,'Delete Account')]");

    public HomePage open() {
        driver.get(ConfigReader.getBaseUrl());
        return this;
    }

    public HomePage goHome() {
        click(By.cssSelector("a[href='/']"));
        return this;
    }

    public boolean isLoaded() {
        return isDisplayed(header) && isDisplayed(carousel);
    }

    public boolean hasFeaturesSection() {
        return isDisplayed(featureItemsTitle);
    }

    public boolean hasCategoriesSection() {
        return isDisplayed(categoryPanel);
    }

    public boolean hasBrandsSection() {
        return isDisplayed(brandPanel);
    }

    public HomePage goToProducts() {
        click(By.cssSelector("a[href='/products']"));
        return this;
    }

    public HomePage goToCart() {
        click(By.cssSelector("a[href='/view_cart']"));
        return this;
    }

    public HomePage goToLogin() {
        click(By.cssSelector("a[href='/login']"));
        return this;
    }

    public HomePage goToContactUs() {
        click(By.cssSelector("a[href='/contact_us']"));
        return this;
    }

    public HomePage goToTestCases() {
        click(By.cssSelector("a[href='/test_cases']"));
        return this;
    }

    public boolean isNavigationLinkVisible(String path) {
        return isDisplayed(By.cssSelector("a[href='" + path + "']"));
    }

    public List<String> getVisibleCategories() {
        return elements(By.cssSelector("#accordian .panel-heading a")).stream()
                .map(element -> element.getText().trim())
                .filter(text -> !text.isBlank())
                .collect(Collectors.toList());
    }

    public List<String> getVisibleBrands() {
        return elements(By.cssSelector(".brands_products .brands-name a")).stream()
                .map(element -> element.getText().trim())
                .collect(Collectors.toList());
    }

    public void subscribe(String email) {
        scrollIntoView(subscriptionInput);
        type(subscriptionInput, email);
        click(subscriptionButton);
    }

    public boolean isSubscriptionSuccessVisible() {
        return isDisplayed(subscriptionSuccess) || pageContains("You have been successfully subscribed");
    }

    public boolean isLoggedInBannerVisible() {
        dismissBlockingPopups();
        return isDisplayed(loggedInUser);
    }

    public String getLoggedInBannerText() {
        dismissBlockingPopups();
        return text(loggedInUser);
    }

    public void logout() {
        dismissBlockingPopups();
        click(By.xpath("//a[contains(.,'Logout')]"));
    }

    public void deleteAccountIfVisible() {
        if (isPresent(deleteAccountLink)) {
            click(deleteAccountLink);
            if (isDisplayed(By.xpath("//*[contains(text(),'ACCOUNT DELETED!')]"))) {
                click(By.cssSelector("[data-qa='continue-button']"));
            }
        }
    }

    public boolean hasRecommendedItemsSection() {
        return isDisplayed(recommendedItemsTitle);
    }

    public boolean pageContains(String text) {
        return driver.getPageSource().contains(text);
    }

    private void dismissBlockingPopups() {
        By modalClose = By.xpath(
                "//div[@role='dialog']//*[contains(normalize-space(),'Close') or contains(normalize-space(),'Done')]");
        List<WebElement> closeButtons = driver.findElements(modalClose);
        for (WebElement button : closeButtons) {
            if (button.isDisplayed()) {
                try {
                    ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
                    return;
                } catch (Exception ignored) {
                    // Try the next candidate in the dialog if the first visible element is not clickable.
                }
            }
        }
    }
}
