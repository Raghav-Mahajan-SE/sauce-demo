package com.saucedemo.tests;

import com.saucedemo.base.BaseTest;
import com.saucedemo.config.ConfigReader;
import org.testng.annotations.Test;

public class LoginPageTest extends BaseTest {

    @Test(description = "Login with valid credentials")
    public void testLogin(){
        pages().getLoginPage().logIn("standard_user","secret_sauce");
    }

}
