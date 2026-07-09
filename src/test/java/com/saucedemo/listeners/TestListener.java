package com.saucedemo.listeners;

import com.saucedemo.driver.DriverFactory;
import com.saucedemo.utils.ExtentReport;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import java.io.File;
import java.util.logging.Logger;

public class TestListener implements ITestListener {

    private static final Logger log = Logger.getLogger(TestListener.class.getName());

    @Override
    public void onTestFailure(ITestResult result) {
        log.info("Test failed: " + result.getName());
        log.info("Reason: " + result.getThrowable());
        WebDriver driver = DriverFactory.getDriver();
        File file  = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileHandler.copy(file,new File("screenshots/" + result.getName() + ".png"));
            ExtentReport.getTest().fail(result.getThrowable())
                    .addScreenCaptureFromPath("../screenshots/" + result.getName() + ".png");
        }catch(Exception e){
            log.info("screenshot not saved: " + e.getMessage());
            ExtentReport.getTest().fail(result.getThrowable());
        }
    }

    @Override
    public void onTestStart(ITestResult result) {
        log.info("Test started: " + result.getName());
        ExtentReport.createTest(result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        log.info("Test passed: " + result.getName());
        ExtentReport.getTest().pass("Test passed");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        log.info("Test skipped: " + result.getName());
        log.info("Reason: " + result.getThrowable());
        ExtentReport.getTest().skip(result.getThrowable());
    }

    @Override
    public void onStart(ITestContext context) {
        log.info("Suite started: " + context.getName());
        ExtentReport.initReport();
    }

    @Override
    public void onFinish(ITestContext context) {
        log.info("Suite finished: " + context.getName());
        log.info("Passed: " + context.getPassedTests().size()
                + " | Failed: " + context.getFailedTests().size()
                + " | Skipped: " + context.getSkippedTests().size());
        ExtentReport.flushReport();
    }

}
