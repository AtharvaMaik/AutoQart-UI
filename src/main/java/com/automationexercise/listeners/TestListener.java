package com.automationexercise.listeners;

import com.automationexercise.factory.DriverManager;
import com.automationexercise.utils.AllureUtils;
import com.automationexercise.utils.ConfigReader;
import com.automationexercise.utils.ScreenshotUtils;
import io.qameta.allure.Allure;
import java.nio.file.Path;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {
    @Override
    public void onStart(ITestContext context) {
        Allure.addAttachment("Execution environment", ConfigReader.get("environment"));
        Allure.addAttachment("Default browser", ConfigReader.getBrowser());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        AllureUtils.attachText(result.getName() + " status", "PASSED");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        if (DriverManager.getDriver() != null) {
            AllureUtils.attachScreenshot(result.getName());
            Path screenshot = ScreenshotUtils.captureToFile(result.getName());
            AllureUtils.attachFile(result.getName() + " file", screenshot);
            AllureUtils.attachText("Current URL", DriverManager.getDriver().getCurrentUrl());
        }
        if (result.getThrowable() != null) {
            AllureUtils.attachText("Failure reason", result.getThrowable().getMessage());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        AllureUtils.attachText(result.getName() + " status", "SKIPPED");
    }
}

