package com.automationexercise.utils;

import com.automationexercise.constants.FrameworkConstants;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class ConfigReader {
    private static final Properties PROPERTIES = new Properties();

    static {
        try (InputStream inputStream = new FileInputStream(FrameworkConstants.CONFIG_FILE_PATH)) {
            PROPERTIES.load(inputStream);
        } catch (IOException exception) {
            throw new IllegalStateException("Unable to load config file: " + FrameworkConstants.CONFIG_FILE_PATH, exception);
        }
    }

    private ConfigReader() {
    }

    public static String get(String key) {
        return System.getProperty(key, PROPERTIES.getProperty(key));
    }

    public static String getBaseUrl() {
        return get("base.url");
    }

    public static String getBrowser() {
        return get("browser");
    }

    public static boolean isHeadless() {
        return Boolean.parseBoolean(get("headless"));
    }

    public static long getImplicitWait() {
        return Long.parseLong(get("implicit.wait"));
    }

    public static long getExplicitWait() {
        return Long.parseLong(get("explicit.wait"));
    }

    public static long getPageLoadTimeout() {
        return Long.parseLong(get("page.load.timeout"));
    }

    public static long getScriptTimeout() {
        return Long.parseLong(get("script.timeout"));
    }

    public static String getContactUploadPath() {
        return get("contact.upload.path");
    }

    public static String getDefaultPassword() {
        return get("valid.user.password");
    }
}

