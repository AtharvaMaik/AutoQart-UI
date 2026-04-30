package com.automationexercise.pages;

import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.By;

public class ProductsPage extends BasePage {
    private final By allProductsTitle = By.xpath("//h2[contains(.,'All Products')]");
    private final By searchInput = By.id("search_product");
    private final By searchButton = By.id("submit_search");
    private final By searchedProductsTitle = By.xpath("//h2[contains(.,'Searched Products')]");
    private final By modalViewCart = By.xpath("//u[contains(.,'View Cart')]");
    private final By continueShopping = By.xpath("//*[contains(text(),'Continue Shopping')]");
    private final By productCards = By.cssSelector(".features_items .product-image-wrapper");
    private final By productNames = By.cssSelector(".features_items .productinfo p");

    public boolean isLoaded() {
        return isDisplayed(allProductsTitle);
    }

    public void search(String keyword) {
        type(searchInput, keyword);
        click(searchButton);
    }

    public boolean isSearchedProductsTitleVisible() {
        return isDisplayed(searchedProductsTitle);
    }

    public List<String> getVisibleProductNames() {
        return elements(productNames).stream()
                .map(element -> element.getText().trim())
                .collect(Collectors.toList());
    }

    public void addProductToCart(int productId) {
        By card = By.cssSelector(".productinfo a[data-product-id='" + productId + "']");
        scrollIntoView(card);
        hover(By.xpath("//a[@data-product-id='" + productId + "']/ancestor::div[contains(@class,'product-image-wrapper')]"));
        click(card);
    }

    public void addProductToCartByName(String productName) {
        By addButton = By.xpath("//p[normalize-space()='" + productName + "']/ancestor::div[contains(@class,'product-image-wrapper')]//a[contains(@class,'add-to-cart')][1]");
        scrollIntoView(addButton);
        hover(By.xpath("//p[normalize-space()='" + productName + "']/ancestor::div[contains(@class,'product-image-wrapper')]"));
        click(addButton);
    }

    public void continueShoppingFromModal() {
        click(continueShopping);
    }

    public void openCartFromModal() {
        click(modalViewCart);
    }

    public void openProductDetails(int productId) {
        click(By.cssSelector("a[href='/product_details/" + productId + "']"));
    }

    public boolean isProductDetailsVisible() {
        return pageContains("Availability:") || pageContains("Category:");
    }

    public void selectCategory(String parentCategory, String childCategory) {
        click(By.xpath("//a[contains(.,'" + parentCategory + "')]"));
        click(By.xpath("//a[contains(@href,'category_products') and contains(.,'" + childCategory + "')]"));
    }

    public void selectBrand(String brandName) {
        scrollIntoView(By.cssSelector(".brands_products"));
        click(By.xpath("//div[@class='brands_products']//a[contains(.,'" + brandName + "')]"));
    }

    public boolean pageContains(String value) {
        return driver.getPageSource().contains(value);
    }

    public int getProductCount() {
        return elements(productCards).size();
    }

    public boolean isSearchInputVisible() {
        return isDisplayed(searchInput);
    }
}

