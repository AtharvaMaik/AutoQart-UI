package com.automationexercise.tests;

import static org.assertj.core.api.Assertions.assertThat;

import com.automationexercise.models.ProductData;
import com.automationexercise.models.ProductSearchData;
import com.automationexercise.utils.TestDataProviders;
import org.testng.annotations.Test;

public class ProductTests extends BaseTest {

    @Test(groups = {"smoke", "regression"})
    public void shouldLoadAllProductsPage() {
        homePage.open().goToProducts();
        assertThat(productsPage.isLoaded()).isTrue();
    }

    @Test(groups = {"regression"})
    public void shouldDisplaySearchFieldOnProductsPage() {
        homePage.open().goToProducts();
        assertThat(productsPage.isSearchInputVisible()).isTrue();
    }

    @Test(dataProvider = "searchData", dataProviderClass = TestDataProviders.class, groups = {"smoke", "regression"})
    public void shouldSearchProductsByKeyword(ProductSearchData data) {
        homePage.open().goToProducts();
        productsPage.search(data.getKeyword());
        assertThat(productsPage.isSearchedProductsTitleVisible()).isTrue();
        assertThat(productsPage.getVisibleProductNames()).anyMatch(name -> name.contains(data.getExpectedProduct()));
    }

    @Test(groups = {"regression"})
    public void shouldShowNoMatchingProductsForInvalidSearch() {
        homePage.open().goToProducts();
        productsPage.search("non-existent-product-xyz");
        assertThat(productsPage.getVisibleProductNames()).isEmpty();
    }

    @Test(dataProvider = "productData", dataProviderClass = TestDataProviders.class, groups = {"regression"})
    public void shouldOpenProductDetailsPage(ProductData data) {
        homePage.open().goToProducts();
        productsPage.openProductDetails(data.getProductId());
        assertThat(productsPage.isProductDetailsVisible()).isTrue();
        assertThat(productsPage.pageContains(data.getName())).isTrue();
    }

    @Test(groups = {"regression"})
    public void shouldFilterProductsByWomenDressCategory() {
        homePage.open().goToProducts();
        productsPage.selectCategory("Women", "Dress");
        assertThat(productsPage.pageContains("Women - Dress Products")).isTrue();
    }

    @Test(groups = {"regression"})
    public void shouldFilterProductsByMenTshirtsCategory() {
        homePage.open().goToProducts();
        productsPage.selectCategory("Men", "Tshirts");
        assertThat(productsPage.pageContains("Men - Tshirts Products")).isTrue();
    }

    @Test(groups = {"regression"})
    public void shouldFilterProductsByPoloBrand() {
        homePage.open().goToProducts();
        productsPage.selectBrand("Polo");
        assertThat(productsPage.pageContains("Brand - Polo Products")).isTrue();
    }

    @Test(groups = {"regression"})
    public void shouldFilterProductsByHMBrand() {
        homePage.open().goToProducts();
        productsPage.selectBrand("H&M");
        assertThat(productsPage.pageContains("Brand - H&M Products")).isTrue();
    }

    @Test(groups = {"regression"})
    public void shouldShowMultipleProductsOnListingPage() {
        homePage.open().goToProducts();
        assertThat(productsPage.getProductCount()).isGreaterThan(5);
    }

    @Test(groups = {"regression"})
    public void shouldContainKnownFeaturedProductName() {
        homePage.open().goToProducts();
        assertThat(productsPage.getVisibleProductNames()).contains("Blue Top");
    }

    @Test(groups = {"regression"})
    public void shouldAddProductFromProductsListing() {
        homePage.open().goToProducts();
        productsPage.addProductToCart(1);
        productsPage.openCartFromModal();
        assertThat(cartPage.getProductNames()).contains("Blue Top");
    }

    @Test(groups = {"regression"})
    public void shouldContinueShoppingAfterAddToCart() {
        homePage.open().goToProducts();
        productsPage.addProductToCart(2);
        productsPage.continueShoppingFromModal();
        assertThat(productsPage.isLoaded()).isTrue();
    }
}

