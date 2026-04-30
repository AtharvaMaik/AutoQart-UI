package com.automationexercise.tests;

import static org.assertj.core.api.Assertions.assertThat;

import com.automationexercise.models.ContactMessageData;
import com.automationexercise.utils.RandomDataUtils;
import com.automationexercise.utils.TestDataProviders;
import org.testng.annotations.Test;

public class ContactAndSubscriptionTests extends BaseTest {

    @Test(groups = {"smoke", "regression"})
    public void shouldLoadContactUsPage() {
        homePage.open().goToContactUs();
        assertThat(contactUsPage.isLoaded()).isTrue();
    }

    @Test(dataProvider = "contactData", dataProviderClass = TestDataProviders.class, groups = {"smoke", "regression"})
    public void shouldSubmitContactFormSuccessfully(ContactMessageData contactMessageData) {
        homePage.open().goToContactUs();
        contactUsPage.submitContactForm(contactMessageData);
        assertThat(contactUsPage.isSuccessVisible()).isTrue();
    }

    @Test(groups = {"regression"})
    public void shouldReturnHomeFromContactPage() {
        homePage.open().goToContactUs();
        contactUsPage.clickHomeButton();
        assertThat(homePage.isLoaded()).isTrue();
    }

    @Test(groups = {"regression"})
    public void shouldSubscribeFromHomePageFooter() {
        homePage.open();
        homePage.subscribe(RandomDataUtils.uniqueEmail("subscribe-home"));
        assertThat(homePage.isSubscriptionSuccessVisible()).isTrue();
    }

    @Test(groups = {"regression"})
    public void shouldSubscribeFromProductsPageFooter() {
        homePage.open().goToProducts();
        homePage.subscribe(RandomDataUtils.uniqueEmail("subscribe-products"));
        assertThat(homePage.isSubscriptionSuccessVisible()).isTrue();
    }

    @Test(groups = {"regression"})
    public void shouldDisplaySubscriptionFieldOnHomePage() {
        homePage.open();
        assertThat(homePage.pageContains("Subscription")).isTrue();
    }

    @Test(groups = {"regression"})
    public void shouldDisplayContactPageNoteForTestingPurpose() {
        homePage.open().goToContactUs();
        assertThat(driver.getPageSource()).contains("Below contact form is for testing purpose");
    }

    @Test(groups = {"regression"})
    public void shouldAcceptFileUploadDuringContactSubmission() {
        homePage.open().goToContactUs();
        assertThat(driver.getPageSource()).contains("type=\"file\"");
    }
}
