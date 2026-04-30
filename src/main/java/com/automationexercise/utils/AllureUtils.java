package com.automationexercise.utils;

import io.qameta.allure.Allure;
import java.nio.file.Files;
import java.nio.file.Path;

public final class AllureUtils {
    private AllureUtils() {
    }

    public static void attachText(String name, String value) {
        Allure.addAttachment(name, value);
    }

    public static void attachScreenshot(String testName) {
        Allure.addAttachment(testName + " screenshot", "image/png",
                new java.io.ByteArrayInputStream(ScreenshotUtils.captureAsBytes()), ".png");
    }

    public static void attachFile(String name, Path path) {
        try {
            Allure.addAttachment(name, Files.newInputStream(path));
        } catch (Exception exception) {
            attachText(name + " attachment error", exception.getMessage());
        }
    }
}

