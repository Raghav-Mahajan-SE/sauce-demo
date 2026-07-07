package com.saucedemo.manager;

import com.saucedemo.pages.LoginPage;
import org.openqa.selenium.WebDriver;

public class PageObjectManager {

    private final WebDriver driver;
    public PageObjectManager(WebDriver driver){
        this.driver = driver;
    }

    public LoginPage getLoginPage(){
        return new LoginPage(driver);
    }
}
