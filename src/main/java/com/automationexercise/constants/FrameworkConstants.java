package com.automationexercise.constants;

import java.nio.file.Path;

public final class FrameworkConstants {
    public static final String CONFIG_FILE_PATH = "src/test/resources/config.properties";
    public static final String SEARCH_DATA_PATH = "src/test/resources/testdata/search-data.json";
    public static final String CONTACT_DATA_PATH = "src/test/resources/testdata/contact-messages.json";
    public static final String PRODUCT_DATA_PATH = "src/test/resources/testdata/products.json";
    public static final Path SCREENSHOT_DIR = Path.of("artifacts", "screenshots");

    private FrameworkConstants() {
    }
}

