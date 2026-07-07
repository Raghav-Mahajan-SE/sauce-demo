package com.saucedemo.tests;

import com.saucedemo.base.BaseTest;
import com.saucedemo.config.ConfigReader;
import com.saucedemo.utils.JsonReader;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginPageTest extends BaseTest {

    @Test(description = "Login with valid credentials",priority = 0,enabled = false)
    public void testLoginWithValidCredentials(){
        System.out.println(JsonReader.get("validCredentials.username"));
        pages().getLoginPage().logIn(JsonReader.get("validCredentials.username"),JsonReader.get("validCredentials.password"));
    }
    @Test(description = "Login with invalid credentials",priority = 1)
    public void testLoginWithInValidCredentials(){
        pages().getLoginPage().logIn(JsonReader.get("inValidCredentials.username"),JsonReader.get("inValidCredentials.password"));
        String actual = pages().getLoginPage().errorMsg();
        Assert.assertEquals(actual, JsonReader.get("errorMsgs.invalid_pwd"));
    }

}
