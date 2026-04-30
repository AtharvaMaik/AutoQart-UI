package com.automationexercise.utils;

import com.automationexercise.constants.FrameworkConstants;
import com.automationexercise.factory.DriverManager;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public final class ScreenshotUtils {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss_SSS");

    private ScreenshotUtils() {
    }

    public static byte[] captureAsBytes() {
        return ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
    }

    public static Path captureToFile(String testName) {
        try {
            Files.createDirectories(FrameworkConstants.SCREENSHOT_DIR);
            Path screenshotPath = FrameworkConstants.SCREENSHOT_DIR.resolve(
                    testName.replaceAll("[^a-zA-Z0-9-_]", "_") + "_" + LocalDateTime.now().format(FORMATTER) + ".png");
            Files.write(screenshotPath, captureAsBytes());
            return screenshotPath;
        } catch (IOException exception) {
            throw new IllegalStateException("Unable to capture screenshot for " + testName, exception);
        }
    }
}

