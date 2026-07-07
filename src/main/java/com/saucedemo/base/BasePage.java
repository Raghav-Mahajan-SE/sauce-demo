package com.saucedemo.base;
import com.saucedemo.config.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class BasePage {
    private final WebDriverWait wait;
    private final WebDriver driver;
    private Select select;
    WebElement element;
    public BasePage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(Long.parseLong(ConfigReader.get("explicit.wait"))));
    }
    public void click(By locator){
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }
    public String getText(By locator){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
    }
    public void type(By locator,String value){
        element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.clear();
        element.sendKeys(value);
    }
    public void selectByVisibleText(By locator, String text) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        select = new Select(driver.findElement(locator));
                select.selectByVisibleText(text);
    }
    public void selectByValue(By locator, String value) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        select = new  Select(driver.findElement(locator));
                select.selectByValue(value);
    }
    public void selectByIndex(By locator, int index) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        select = new Select(driver.findElement(locator));
                select.selectByIndex(index);
    }
    public void switchToFrameByElement(By locator){
        element= wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        driver.switchTo().frame(element);
    }
    public void switchToFrameByValue(String value){
        driver.switchTo().frame(value);
    }
    public void switchToFrameByIndex(int index){
        driver.switchTo().frame(index);
    }
    public void switchToParentFrame(){
        driver.switchTo().parentFrame();
    }
    public void switchToDefault(){
        driver.switchTo().defaultContent();
    }
    public void acceptAlert() {
        wait.until(ExpectedConditions.alertIsPresent()).accept();
    }
    public void dismissAlert() {
        wait.until(ExpectedConditions.alertIsPresent()).dismiss();
    }
    public String getAlertText(){
        return wait.until(ExpectedConditions.alertIsPresent()).getText();
    }
    public void sendTextToAlert(String value){
        wait.until(ExpectedConditions.alertIsPresent()).sendKeys(value);
    }
    public WebElement getElement(By locator){wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    return driver.findElement(locator);}
    public List<WebElement> getElements(By locator){wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return driver.findElements(locator);}


}
