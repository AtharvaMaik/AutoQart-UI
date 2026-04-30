package com.automationexercise.utils;

import com.automationexercise.constants.FrameworkConstants;
import com.automationexercise.models.ContactMessageData;
import com.automationexercise.models.ProductData;
import com.automationexercise.models.ProductSearchData;
import com.fasterxml.jackson.core.type.TypeReference;
import java.util.List;
import org.testng.annotations.DataProvider;

public final class TestDataProviders {
    private TestDataProviders() {
    }

    @DataProvider(name = "searchData")
    public static Object[][] searchData() {
        List<ProductSearchData> data = JsonUtils.readList(
                FrameworkConstants.SEARCH_DATA_PATH, new TypeReference<List<ProductSearchData>>() { });
        return data.stream().map(item -> new Object[]{item}).toArray(Object[][]::new);
    }

    @DataProvider(name = "contactData")
    public static Object[][] contactData() {
        List<ContactMessageData> data = JsonUtils.readList(
                FrameworkConstants.CONTACT_DATA_PATH, new TypeReference<List<ContactMessageData>>() { });
        return data.stream().map(item -> new Object[]{item}).toArray(Object[][]::new);
    }

    @DataProvider(name = "productData")
    public static Object[][] productData() {
        List<ProductData> data = JsonUtils.readList(
                FrameworkConstants.PRODUCT_DATA_PATH, new TypeReference<List<ProductData>>() { });
        return data.stream().map(item -> new Object[]{item}).toArray(Object[][]::new);
    }

    @DataProvider(name = "invalidLogins")
    public static Object[][] invalidLogins() {
        return new Object[][]{
                {"invalid.user@example.com", "Password@123"},
                {"tester@", "Password@123"},
                {"", "Password@123"},
                {"invalid.user@example.com", ""}
        };
    }

    @DataProvider(name = "invalidSignupData")
    public static Object[][] invalidSignupData() {
        return new Object[][]{
                {"", RandomDataUtils.uniqueEmail("missing-name")},
                {"Automation User", ""},
                {"", ""},
                {"   ", RandomDataUtils.uniqueEmail("blank-name")}
        };
    }
}

