package com.tutorial.qa.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tutorial.qa.testcases.Utilities;
import com.tutorial.qa.testcases.utils.ExtentReporter;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import org.openqa.selenium.io.FileHandler;

public class Listeners  implements ITestListener {

    ExtentReports extentReports;
    ExtentTest extentTest;

    @Override
    public void onStart(ITestContext context) {

        extentReports = ExtentReporter.generate_Extent_Report();
    }

    @Override
    public void onTestStart(ITestResult result) {

        extentTest = extentReports.createTest(result.getName());
        extentTest.log(Status.INFO,result.getName() + " start executing.");
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        extentTest.log(Status.PASS, result.getName() + " start executing.");
    }

    @Override
    public void onTestFailure(ITestResult result) {

        WebDriver driver = null;

        try {
            driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
        } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }

        String destinationScreenshotPath = Utilities.capture_Screenshot(driver, result.getName());

        try {
            extentTest.addScreenCaptureFromPath(destinationScreenshotPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        extentTest.log(Status.INFO, result.getThrowable());
        extentTest.log(Status.FAIL, result.getName() + " got failed");

        }

    @Override
    public void onTestSkipped(ITestResult result) {

        extentTest.log(Status.INFO, result.getThrowable());
        extentTest.log(Status.SKIP, result.getName() + " got skipped");

    }

    @Override
    public void onFinish(ITestContext context) {

        extentReports.flush();
        String pathOfExtentReport = System.getProperty("user.dir") + "//ExtentReport//extentReports.html";
        File extentReport = new File(pathOfExtentReport);
        try {
            Desktop.getDesktop().browse(extentReport.toURI());
        } catch (IOException e) {            e.printStackTrace();
        }
    }
}
