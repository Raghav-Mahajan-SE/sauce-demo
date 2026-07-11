package com.saucedemo.listeners;

import com.saucedemo.config.ConfigReader;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

    private int count = 0;
    private final int maxRetry = Integer.parseInt(ConfigReader.get("retry.count"));

    @Override
    public boolean retry(ITestResult result) {
        if(count < maxRetry){
            count++;
            return true;
        }
        return false;
    }
}
