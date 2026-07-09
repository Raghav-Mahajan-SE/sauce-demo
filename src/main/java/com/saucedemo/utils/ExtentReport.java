package com.saucedemo.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.saucedemo.config.ConfigReader;

public class ExtentReport {

    private static ExtentReports extent;
    private static final ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    public static void initReport(){
        if(extent == null){
            ExtentSparkReporter spark = new ExtentSparkReporter("reports/ExtentReport.html");
            extent = new ExtentReports();
            extent.attachReporter(spark);
            extent.setSystemInfo("Browser", ConfigReader.get("browser"));
            extent.setSystemInfo("Base URL", ConfigReader.get("base_url"));
            extent.setSystemInfo("OS", System.getProperty("os.name"));
        }
    }

    public static void createTest(String name){
        test.set(extent.createTest(name));
    }

    public static ExtentTest getTest(){
        return test.get();
    }

    public static void flushReport(){
        extent.flush();
    }
}
