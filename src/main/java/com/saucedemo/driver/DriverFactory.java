package com.saucedemo.driver;
import com.saucedemo.config.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import java.time.Duration;

public class DriverFactory {

    public static ThreadLocal<WebDriver> driverThread = new ThreadLocal<>();

    public static void initDriver(){
        String browser = ConfigReader.get("browser").toLowerCase();
        boolean headless = Boolean.parseBoolean(ConfigReader.get("headless"));
        WebDriver driver = null;
        switch (browser){
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                if(headless) {
                    chromeOptions.addArguments("--headless=new");
                }
                chromeOptions.addArguments("--start-maximized");
                driver = new ChromeDriver(chromeOptions);
                break;

            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                if(headless) {
                    firefoxOptions.addArguments("--headless=new");
                }
                firefoxOptions.addArguments("--start-maximized");
                driver = new FirefoxDriver(firefoxOptions);

        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(ConfigReader.get("implicit.wait"))));
        driverThread.set(driver);
    }
    public static WebDriver getDriver(){
        return driverThread.get();
    }

    public void quitBrowser(){
        if(driverThread.get()!=null){
            driverThread.get().quit();
            driverThread.remove();
        }
    }
}
