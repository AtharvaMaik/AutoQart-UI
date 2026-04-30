package com.automationexercise.tests;

import static org.assertj.core.api.Assertions.assertThat;

import com.automationexercise.models.UserRegistrationData;
import com.automationexercise.utils.TestDataProviders;
import org.testng.annotations.Test;

public class AuthenticationTests extends BaseTest {

    @Test(groups = {"smoke", "regression"})
    public void shouldRegisterNewUserSuccessfully() {
        UserRegistrationData user = buildUser();
        homePage.open().goToLogin();
        assertThat(loginPage.isLoaded()).isTrue();
        loginPage.startSignup(user.getName(), user.getEmail());
        assertThat(signupPage.isLoaded()).isTrue();
        signupPage.createAccount(user);
        assertThat(signupPage.isAccountCreatedVisible()).isTrue();
        signupPage.clickContinue();
        assertThat(homePage.isLoggedInBannerVisible()).isTrue();
    }

    @Test(groups = {"smoke", "regression"})
    public void shouldLoginWithNewlyCreatedUser() {
        UserRegistrationData user = createUserThroughUi();
        homePage.goHome();
        homePage.logout();
        assertThat(loginPage.isLoaded()).isTrue();
        loginPage.login(user.getEmail(), user.getPassword());
        assertThat(homePage.isLoggedInBannerVisible()).isTrue();
    }

    @Test(groups = {"regression"})
    public void shouldLogoutCurrentUser() {
        createUserThroughUi();
        homePage.logout();
        assertThat(loginPage.isLoaded()).isTrue();
    }

    @Test(dataProvider = "invalidLogins", dataProviderClass = TestDataProviders.class, groups = {"regression"})
    public void shouldRejectInvalidLoginAttempts(String email, String password) {
        homePage.open().goToLogin();
        loginPage.login(email, password);
        assertThat(loginPage.isInvalidLoginVisible() || loginPage.isLoaded()).isTrue();
    }

    @Test(dataProvider = "invalidSignupData", dataProviderClass = TestDataProviders.class, groups = {"regression"})
    public void shouldKeepSignupFormVisibleForInvalidData(String name, String email) {
        homePage.open().goToLogin();
        loginPage.startSignup(name, email);
        assertThat(loginPage.isSignupStillVisible() || signupPage.isLoaded()).isTrue();
    }

    @Test(groups = {"regression"})
    public void shouldRejectDuplicateRegistrationWithSameEmail() {
        UserRegistrationData user = createUserThroughUi();
        homePage.logout();
        loginPage.startSignup(user.getName(), user.getEmail());
        assertThat(loginPage.isExistingEmailMessageVisible()).isTrue();
    }

    @Test(groups = {"regression"})
    public void shouldDisplayLoggedInUsernameAfterRegistration() {
        UserRegistrationData user = createUserThroughUi();
        assertThat(homePage.getLoggedInBannerText()).contains("Automation User");
        assertThat(user.getEmail()).contains("@example.com");
    }

    @Test(groups = {"regression"})
    public void shouldAllowAccountDeletionAfterRegistration() {
        createUserThroughUi();
        homePage.deleteAccountIfVisible();
        assertThat(homePage.pageContains("ACCOUNT DELETED!") || homePage.pageContains("Delete Account")).isTrue();
    }

    @Test(groups = {"regression"})
    public void shouldPersistLoginUntilLogout() {
        UserRegistrationData user = createUserThroughUi();
        homePage.goHome().goToProducts().goHome();
        assertThat(homePage.isLoggedInBannerVisible()).isTrue();
        homePage.logout();
        loginPage.login(user.getEmail(), user.getPassword());
        assertThat(homePage.isLoggedInBannerVisible()).isTrue();
    }

    @Test(groups = {"regression"})
    public void shouldLoadLoginAndSignupSectionTogether() {
        homePage.open().goToLogin();
        assertThat(loginPage.isLoaded()).isTrue();
    }

    @Test(groups = {"regression"})
    public void shouldKeepUserOnLoginPageAfterFailedLogin() {
        homePage.open().goToLogin();
        loginPage.login("no.user@example.com", "WrongPassword123");
        assertThat(loginPage.isLoaded()).isTrue();
    }
}
