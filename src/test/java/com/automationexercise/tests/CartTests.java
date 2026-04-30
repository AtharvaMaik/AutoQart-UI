package com.automationexercise.tests;

import static org.assertj.core.api.Assertions.assertThat;

import org.testng.annotations.Test;

public class CartTests extends BaseTest {

    @Test(groups = {"smoke", "regression"})
    public void shouldAddSingleProductToCart() {
        homePage.open().goToProducts();
        productsPage.addProductToCart(1);
        productsPage.openCartFromModal();
        assertThat(cartPage.getCartRowCount()).isGreaterThanOrEqualTo(1);
        assertThat(cartPage.getProductNames()).contains("Blue Top");
    }

    @Test(groups = {"smoke", "regression"})
    public void shouldRemoveProductFromCart() {
        homePage.open().goToProducts();
        productsPage.addProductToCart(1);
        productsPage.openCartFromModal();
        cartPage.removeProduct("Blue Top");
        assertThat(cartPage.isProductPresent("Blue Top")).isFalse();
    }

    @Test(groups = {"regression"})
    public void shouldAddMultipleProductsToCart() {
        homePage.open().goToProducts();
        productsPage.addProductToCart(1);
        productsPage.continueShoppingFromModal();
        productsPage.addProductToCart(2);
        productsPage.openCartFromModal();
        assertThat(cartPage.getProductNames()).contains("Blue Top", "Men Tshirt");
    }

    @Test(groups = {"regression"})
    public void shouldShowCorrectQuantityForSingleProduct() {
        homePage.open().goToProducts();
        productsPage.addProductToCart(1);
        productsPage.openCartFromModal();
        assertThat(cartPage.getQuantityForProduct("Blue Top")).isEqualTo("1");
    }

    @Test(groups = {"regression"})
    public void shouldRetainProductsWhenNavigatingBackToProductsPage() {
        homePage.open().goToProducts();
        productsPage.addProductToCart(1);
        productsPage.openCartFromModal();
        homePage.goToProducts().goToCart();
        assertThat(cartPage.getProductNames()).contains("Blue Top");
    }

    @Test(groups = {"regression"})
    public void shouldAllowContinueShoppingBetweenAdds() {
        homePage.open().goToProducts();
        productsPage.addProductToCart(1);
        productsPage.continueShoppingFromModal();
        productsPage.addProductToCart(3);
        productsPage.openCartFromModal();
        assertThat(cartPage.getCartRowCount()).isGreaterThanOrEqualTo(2);
    }

    @Test(groups = {"regression"})
    public void shouldOpenCartFromHeaderNavigation() {
        homePage.open().goToCart();
        assertThat(driver.getCurrentUrl()).contains("view_cart");
    }

    @Test(groups = {"regression"})
    public void shouldKeepCartItemsAfterReturningHome() {
        homePage.open().goToProducts();
        productsPage.addProductToCart(2);
        productsPage.openCartFromModal();
        homePage.goHome().goToCart();
        assertThat(cartPage.getProductNames()).contains("Men Tshirt");
    }

    @Test(groups = {"regression"})
    public void shouldShowCheckoutModalForAnonymousUser() {
        homePage.open().goToProducts();
        productsPage.addProductToCart(1);
        productsPage.openCartFromModal();
        cartPage.proceedToCheckout();
        assertThat(cartPage.isCheckoutModalVisible()).isTrue();
    }

    @Test(groups = {"regression"})
    public void shouldOpenRegisterLoginFromCheckoutModal() {
        homePage.open().goToProducts();
        productsPage.addProductToCart(1);
        productsPage.openCartFromModal();
        cartPage.proceedToCheckout();
        cartPage.clickRegisterLoginFromCheckoutModal();
        assertThat(driver.getCurrentUrl()).contains("/login");
    }
}

