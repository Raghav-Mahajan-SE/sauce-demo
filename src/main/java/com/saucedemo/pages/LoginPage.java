package com.saucedemo.pages;

import com.saucedemo.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private final By username = By.id("user-name");
    private final By userPwd = By.id("password");
    private final By loginBtn = By.id("login-button");
    private final By invalidLoginMsg = By.cssSelector("[data-test='error']");

    public LoginPage(WebDriver driver){
        super(driver);
    }

    public void logIn (String name,String password){
        type(username,name);
        type(userPwd,password);
        click(loginBtn);
    }
    public String errorMsg(){
        return getText(invalidLoginMsg);
    }




}
