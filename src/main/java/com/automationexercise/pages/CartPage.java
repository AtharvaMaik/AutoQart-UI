package com.automationexercise.pages;

import com.automationexercise.utils.WaitUtils;
import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.By;

public class CartPage extends BasePage {
    private final By cartRows = By.cssSelector("#cart_info_table tbody tr");
    private final By breadcrumb = By.cssSelector(".breadcrumb");
    private final By proceedToCheckout = By.xpath("//a[contains(.,'Proceed To Checkout')]");
    private final By registerLoginLink = By.xpath("//u[contains(.,'Register / Login')]");

    public boolean isLoaded() {
        return isDisplayed(breadcrumb) && isPresent(cartRows);
    }

    public int getCartRowCount() {
        return driver.findElements(cartRows).size();
    }

    public List<String> getProductNames() {
        return driver.findElements(By.cssSelector("#cart_info_table tbody tr .cart_description h4 a")).stream()
                .map(element -> element.getText().trim())
                .collect(Collectors.toList());
    }

    public String getQuantityForProduct(String productName) {
        return text(By.xpath("//a[normalize-space()='" + productName + "']/ancestor::tr//button"));
    }

    public void removeProduct(String productName) {
        By row = By.xpath("//a[normalize-space()='" + productName + "']/ancestor::tr");
        click(By.xpath("//a[normalize-space()='" + productName + "']/ancestor::tr//a[contains(@class,'cart_quantity_delete')]"));
        WaitUtils.invisible(row);
    }

    public boolean isProductPresent(String productName) {
        return !driver.findElements(By.xpath("//a[normalize-space()='" + productName + "']")).isEmpty();
    }

    public boolean isEmpty() {
        return driver.findElements(cartRows).isEmpty() || pageContains("Cart is empty");
    }

    public void proceedToCheckout() {
        click(proceedToCheckout);
    }

    public void clickRegisterLoginFromCheckoutModal() {
        click(registerLoginLink);
    }

    public boolean isCheckoutModalVisible() {
        return pageContains("Register / Login account to proceed");
    }

    public boolean pageContains(String value) {
        return driver.getPageSource().contains(value);
    }
}
