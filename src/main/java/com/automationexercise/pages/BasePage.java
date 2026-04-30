package com.automationexercise.pages;

import com.automationexercise.factory.DriverManager;
import com.automationexercise.utils.WaitUtils;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public abstract class BasePage {
    protected final WebDriver driver;

    protected BasePage() {
        this.driver = DriverManager.getDriver();
    }

    protected void click(By locator) {
        try {
            WaitUtils.clickable(locator).click();
        } catch (Exception exception) {
            jsClick(locator);
        }
    }

    protected void type(By locator, String value) {
        WebElement element = WaitUtils.visible(locator);
        element.clear();
        if (value != null && !value.isBlank()) {
            element.sendKeys(value);
        }
    }

    protected void append(By locator, String value) {
        WaitUtils.visible(locator).sendKeys(value);
    }

    protected String text(By locator) {
        return WaitUtils.visible(locator).getText().trim();
    }

    protected boolean isDisplayed(By locator) {
        try {
            return WaitUtils.visible(locator).isDisplayed();
        } catch (TimeoutException exception) {
            return false;
        }
    }

    protected List<WebElement> elements(By locator) {
        return WaitUtils.allVisible(locator);
    }

    protected void selectByVisibleText(By locator, String visibleText) {
        new Select(WaitUtils.visible(locator)).selectByVisibleText(visibleText);
    }

    protected void jsClick(By locator) {
        WebElement element = WaitUtils.visible(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    protected void hover(By locator) {
        WebElement element = WaitUtils.visible(locator);
        org.openqa.selenium.interactions.Actions actions = new org.openqa.selenium.interactions.Actions(driver);
        actions.moveToElement(element).pause(Duration.ofMillis(300)).perform();
    }

    protected void scrollIntoView(By locator) {
        WebElement element = WaitUtils.visible(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", element);
    }

    protected void searchEnter(By locator, String value) {
        WebElement element = WaitUtils.visible(locator);
        element.clear();
        element.sendKeys(value, Keys.ENTER);
    }

    protected boolean isPresent(By locator) {
        return !driver.findElements(locator).isEmpty();
    }

    protected void waitForUrlContains(String path) {
        WaitUtils.until(ExpectedConditions.urlContains(path));
    }
}

