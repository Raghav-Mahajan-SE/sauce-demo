package com.saucedemo.tests;

import com.fasterxml.jackson.databind.JsonNode;
import com.saucedemo.base.BaseTest;
import com.saucedemo.config.ConfigReader;
import com.saucedemo.utils.JsonReader;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginPageTest extends BaseTest {

    @Test(description = "Login with valid credentials",priority = 0,enabled = true)
    public void testLoginWithValidCredentials(){
        pages().getLoginPage().logIn(JsonReader.get("validCredentials.username"),JsonReader.get("validCredentials.password"));
        Assert.assertEquals(pages().getProductPage().getHeading(),JsonReader.get("pageInformation.productPageHeading"));
    }
    @Test(description = "Login with invalid credentials",priority = 1)
    public void testLoginWithInValidCredentials(){
        pages().getLoginPage().logIn(JsonReader.get("inValidCredentials.username"),JsonReader.get("inValidCredentials.password"));
        String actual = pages().getLoginPage().errorMsg();
        Assert.assertEquals(actual, JsonReader.get("errorMsgs.invalid_pwd"));
    }

    @DataProvider(name = "invalidLogins")
    public Object[][] invalidLoginData(){
        JsonNode data = JsonReader.getNode("invalidLogins");
        Object[][] rows = new Object[data.size()][3];
        for(int i=0;i<data.size();i++){
            rows[i][0] = data.get(i).get("username").asText();
            rows[i][1] = data.get(i).get("password").asText();
            rows[i][2] = data.get(i).get("error").asText();
        }
        return rows;
    }

    @Test(description = "Login with multiple invalid credentials",dataProvider = "invalidLogins")
    public void testInvalidLogins(String username,String password,String error){
        pages().getLoginPage().logIn(username,password);
        Assert.assertEquals(pages().getLoginPage().errorMsg(),error);
    }

}
