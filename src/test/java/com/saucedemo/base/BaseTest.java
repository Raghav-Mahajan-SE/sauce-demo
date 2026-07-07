package com.saucedemo.base;
import com.saucedemo.config.ConfigReader;
import com.saucedemo.driver.DriverFactory;
import com.saucedemo.manager.PageObjectManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    private final ThreadLocal<PageObjectManager> pomThread = new ThreadLocal<>();

    @BeforeMethod(alwaysRun = true)
    public void setupBrowser(){
        DriverFactory.initDriver();
        DriverFactory.getDriver().get(ConfigReader.get("base_url"));
        pomThread.set(new PageObjectManager(DriverFactory.getDriver()));
    }
    public PageObjectManager pages() {
        return pomThread.get();
    }
    @AfterMethod
    public void quitBrowser(){
        DriverFactory.quitDriver();
        pomThread.remove();
    }


}
