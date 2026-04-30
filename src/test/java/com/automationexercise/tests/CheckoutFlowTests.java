package com.automationexercise.tests;

import static org.assertj.core.api.Assertions.assertThat;

import com.automationexercise.models.UserRegistrationData;
import org.testng.annotations.Test;

public class CheckoutFlowTests extends BaseTest {

    @Test(groups = {"regression"})
    public void shouldProceedToCheckoutAfterLogin() {
        UserRegistrationData user = createUserThroughUi();
        homePage.goToProducts();
        productsPage.addProductToCart(1);
        productsPage.openCartFromModal();
        cartPage.proceedToCheckout();
        assertThat(driver.getPageSource()).contains("Address Details");
        assertThat(user.getEmail()).contains("@example.com");
    }

    @Test(groups = {"regression"})
    public void shouldDisplayOrderSummaryAtCheckout() {
        createUserThroughUi();
        homePage.goToProducts();
        productsPage.addProductToCart(2);
        productsPage.openCartFromModal();
        cartPage.proceedToCheckout();
        assertThat(driver.getPageSource()).contains("Review Your Order");
    }

    @Test(groups = {"regression"})
    public void shouldCarryCartProductIntoCheckoutPage() {
        createUserThroughUi();
        homePage.goToProducts();
        productsPage.addProductToCart(1);
        productsPage.openCartFromModal();
        cartPage.proceedToCheckout();
        assertThat(driver.getPageSource()).contains("Blue Top");
    }

    @Test(groups = {"regression"})
    public void shouldAllowRegisterLoginPathFromCheckoutPrompt() {
        homePage.open().goToProducts();
        productsPage.addProductToCart(2);
        productsPage.openCartFromModal();
        cartPage.proceedToCheckout();
        cartPage.clickRegisterLoginFromCheckoutModal();
        assertThat(loginPage.isLoaded()).isTrue();
    }

    @Test(groups = {"regression"})
    public void shouldPreserveCartAfterRegisteringFromCheckoutPrompt() {
        homePage.open().goToProducts();
        productsPage.addProductToCart(1);
        productsPage.openCartFromModal();
        cartPage.proceedToCheckout();
        cartPage.clickRegisterLoginFromCheckoutModal();
        UserRegistrationData user = buildUser();
        loginPage.startSignup(user.getName(), user.getEmail());
        signupPage.createAccount(user);
        signupPage.clickContinue();
        homePage.goToCart();
        assertThat(cartPage.getProductNames()).contains("Blue Top");
    }

    @Test(groups = {"regression"})
    public void shouldShowAddressDetailsForRegisteredUserCheckout() {
        createUserThroughUi();
        homePage.goToProducts();
        productsPage.addProductToCart(3);
        productsPage.openCartFromModal();
        cartPage.proceedToCheckout();
        assertThat(driver.getPageSource()).contains("Your delivery address");
    }
}

