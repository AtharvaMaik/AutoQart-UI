package com.automationexercise.tests;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.testng.annotations.Test;

public class HomeNavigationTests extends BaseTest {

    @Test(groups = {"regression"})
    public void shouldLoadHomePageSuccessfully() {
        assertThat(homePage.open().isLoaded()).isTrue();
    }

    @Test(groups = {"regression"})
    public void shouldDisplayFeatureItemsSection() {
        homePage.open();
        assertThat(homePage.hasFeaturesSection()).isTrue();
    }

    @Test(groups = {"regression"})
    public void shouldDisplayCategoriesSection() {
        homePage.open();
        assertThat(homePage.hasCategoriesSection()).isTrue();
    }

    @Test(groups = {"regression"})
    public void shouldDisplayBrandsSection() {
        homePage.open();
        assertThat(homePage.hasBrandsSection()).isTrue();
    }

    @Test(groups = {"regression"})
    public void shouldShowHomeNavigationLink() {
        homePage.open();
        assertThat(homePage.isNavigationLinkVisible("/")).isTrue();
    }

    @Test(groups = {"regression"})
    public void shouldShowProductsNavigationLink() {
        homePage.open();
        assertThat(homePage.isNavigationLinkVisible("/products")).isTrue();
    }

    @Test(groups = {"regression"})
    public void shouldShowCartNavigationLink() {
        homePage.open();
        assertThat(homePage.isNavigationLinkVisible("/view_cart")).isTrue();
    }

    @Test(groups = {"regression"})
    public void shouldShowLoginNavigationLink() {
        homePage.open();
        assertThat(homePage.isNavigationLinkVisible("/login")).isTrue();
    }

    @Test(groups = {"regression"})
    public void shouldShowContactNavigationLink() {
        homePage.open();
        assertThat(homePage.isNavigationLinkVisible("/contact_us")).isTrue();
    }

    @Test(groups = {"regression"})
    public void shouldShowTestCasesNavigationLink() {
        homePage.open();
        assertThat(homePage.isNavigationLinkVisible("/test_cases")).isTrue();
    }

    @Test(groups = {"regression"})
    public void shouldListTopLevelCategories() {
        homePage.open();
        List<String> categories = homePage.getVisibleCategories();
        assertThat(categories).anyMatch(value -> value.contains("Women"))
                .anyMatch(value -> value.contains("Men"))
                .anyMatch(value -> value.contains("Kids"));
    }

    @Test(groups = {"regression"})
    public void shouldListKnownBrands() {
        homePage.open();
        List<String> brands = homePage.getVisibleBrands();
        assertThat(brands).anyMatch(value -> value.contains("Polo"))
                .anyMatch(value -> value.contains("H&M"));
    }

    @Test(groups = {"regression"})
    public void shouldShowRecommendedItemsSectionWhenAvailable() {
        homePage.open();
        assertThat(homePage.pageContains("recommended items") || homePage.hasRecommendedItemsSection()).isTrue();
    }
}

